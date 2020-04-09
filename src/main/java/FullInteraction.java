import encrypt.Encrypt;
import entities.FileEntity;
import entities.ResponseScore;
import entities.SystemFileUItils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AceleraDevService;
import services.config.RetrofitConfigInstance;
import services.config.ServiceParams;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FullInteraction {
    private static final String fileName = "answer.json";
    private static AceleraDevService service;


    public static void main(String[] args) {
        service = RetrofitConfigInstance.getAceleraDevServiceInstance();
        new FullInteraction().resolveTask();
    }
    private void resolveTask(){
        getFileContectFromApi();
    }

    public void getFileContectFromApi() {
        Call<FileEntity> fileEntityCallback = service.getFileEntity(ServiceParams.TOKEN);
        fileEntityCallback.enqueue(getFileContectFromApiCallBack());
    }

    private Callback<FileEntity> getFileContectFromApiCallBack() {
        return new Callback<FileEntity>() {
            @Override
            public void onResponse(Call<FileEntity> call, Response<FileEntity> response) {
                try {
                    FileEntity fileEntity = response.body();
                    SystemFileUItils.writeFile(fileName, fileEntity, FileEntity.class);

                    fileEntity.setDecipherText(Encrypt.CaesarDecrypt(fileEntity.getCipherText(),fileEntity.getRole()));
                    SystemFileUItils.writeFile(fileName, fileEntity, FileEntity.class);

                    fileEntity.setEncriptesSH1Resume(Encrypt.SHA1Encrypt(fileEntity.getDecipherText()));
                    SystemFileUItils.writeFile(fileName, fileEntity, FileEntity.class);

                    sentFileContectFromApi();
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FileEntity> call, Throwable throwable) {
                System.out.println("Um erro ocorreu ao recuperar o conteúdo do arquivo do servidor!");
            }
        };
    }

    public void sentFileContectFromApi() {
        File file = new File(fileName);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("answer", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));

        Call<ResponseScore> fileEntityCallback = service.sendFileToApi(ServiceParams.TOKEN,filePart);
        fileEntityCallback.enqueue(sendFileContentToApiCallback());
    }

    private Callback<ResponseScore> sendFileContentToApiCallback() {
        return new Callback<ResponseScore>() {
            @Override
            public void onResponse(Call<ResponseScore> call, Response<ResponseScore> response) {
                ResponseScore responseScore = response.body();
                System.out.println("Your Score are : " + responseScore.getScore());
            }

            @Override
            public void onFailure(Call<ResponseScore> call, Throwable throwable) {
                System.out.println("Um erro ocorreu ao enviar o conteúdo do arquivo do servidor!");
            }
        };
    }
}

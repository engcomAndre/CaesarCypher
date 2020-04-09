package services;

import entities.FileEntity;
import entities.ResponseScore;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface AceleraDevService {
    @GET("generate-data")
    Call<FileEntity> getFileEntity(@Query("token") String token);

    @Multipart
    @POST("submit-solution")
    Call<ResponseScore> sendFileToApi(@Query("token") String token, @Part MultipartBody.Part filePart);
}

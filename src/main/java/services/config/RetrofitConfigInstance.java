package services.config;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.AceleraDevService;

public class RetrofitConfigInstance {

    private static Retrofit retrofit;
    private static AceleraDevService aceleraDevService;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ServiceParams.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static AceleraDevService getAceleraDevServiceInstance() {
        if (aceleraDevService == null) {
            aceleraDevService = getRetrofitInstance().create(AceleraDevService.class);
        }
        return aceleraDevService;
    }

}

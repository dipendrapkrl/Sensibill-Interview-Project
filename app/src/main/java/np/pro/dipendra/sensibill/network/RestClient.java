package np.pro.dipendra.sensibill.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.*;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by dipendra on 8/14/15.
 */
 class RestClient {
    private ApiService mApiService;
    private static final String BASE_URL = "https://getsensibill.com/api";
    private static final String DATE_FORMAT = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'";
    private static RestClient restClient;

    private RestClient() {

        OkHttpClient client = new OkHttpClient();

        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(client))
                .build();


        mApiService = restAdapter.create(ApiService.class);
    }

    public static RestClient getInstance() {
        if (restClient != null) {
            return restClient;
        } else {
            restClient = new RestClient();
            return restClient;
        }
    }


    ApiService getApiService() {
        return mApiService;
    }


}

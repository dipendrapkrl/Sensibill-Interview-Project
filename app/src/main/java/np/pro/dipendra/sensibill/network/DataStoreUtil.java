package np.pro.dipendra.sensibill.network;

import np.pro.dipendra.sensibill.api.response.ReceiptsResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dipendra on 8/18/15.
 * To be used as a helper class to make network requests
 */
public class DataStoreUtil {

    static ApiService apiService;

    /**
     * Must be at least invoked once in an application before using other methods. Better place would be in Application class
     */
    public static void init() {

        if (apiService == null) {
            apiService = RestClient.getInstance().getApiService();
        }

    }

    /**
     *
     * @param dataStoreCallback callback to be invoked after data is retrieved
     */
    public static void getReceipts(final DataStoreCallback dataStoreCallback) {

        apiService.getReceipts(new Callback<ReceiptsResponse>() {
            @Override
            public void success(ReceiptsResponse receiptsResponse, Response response) {
                if (receiptsResponse != null && response.getStatus() == 200) {
                    dataStoreCallback.onSuccess(receiptsResponse);
                } else {
                    DataStoreError dataStoreError = new DataStoreError();

                    dataStoreCallback.onFailure(dataStoreError);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                DataStoreError dataStoreError = new DataStoreError();
                dataStoreError.message = error.getMessage();
                dataStoreCallback.onFailure(dataStoreError);

            }
        });
    }


}

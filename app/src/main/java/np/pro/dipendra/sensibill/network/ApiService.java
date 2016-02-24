package np.pro.dipendra.sensibill.network;

import np.pro.dipendra.sensibill.api.response.ReceiptsResponse;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by dipendra on 8/14/15.
 */
 interface ApiService {


    @GET("/tests/receipts")
    void getReceipts(Callback<ReceiptsResponse> callback);


}

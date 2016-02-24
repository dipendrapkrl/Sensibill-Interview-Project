package np.pro.dipendra.sensibill.network;

/**
 * Created by dipendra on 8/18/15.
 */
public interface DataStoreCallback<V> {
    void onSuccess(V v);

    void onFailure(DataStoreError error);
}

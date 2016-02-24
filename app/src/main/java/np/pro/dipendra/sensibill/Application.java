package np.pro.dipendra.sensibill;

import np.pro.dipendra.sensibill.network.DataStoreUtil;

/**
 * Created by dipendra on 2/24/16.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataStoreUtil.init();
    }
}

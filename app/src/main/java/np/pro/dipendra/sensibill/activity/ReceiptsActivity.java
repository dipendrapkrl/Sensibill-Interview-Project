package np.pro.dipendra.sensibill.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import np.pro.dipendra.sensibill.R;
import np.pro.dipendra.sensibill.adapter.ReceiptsAdapter;
import np.pro.dipendra.sensibill.api.model.Receipt;
import np.pro.dipendra.sensibill.api.response.ReceiptsResponse;
import np.pro.dipendra.sensibill.network.DataStoreCallback;
import np.pro.dipendra.sensibill.network.DataStoreError;
import np.pro.dipendra.sensibill.network.DataStoreUtil;
import np.pro.dipendra.sensibill.util.MiscUtil;

public class ReceiptsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private RelativeLayout mErrorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts);

        initializeViews();
        downloadReceipts();

    }

    private void downloadReceipts() {
        if (!MiscUtil.isNetworkAvailable(this)) {
            setupViewsVisibility(false);
            return;
        }
        DataStoreUtil.getReceipts(new DataStoreCallback<ReceiptsResponse>() {
            @Override
            public void onSuccess(ReceiptsResponse receiptsResponse) {
                setupViewsVisibility(receiptsResponse.receipts != null);
                loadReceipts(receiptsResponse.receipts);

            }

            @Override
            public void onFailure(DataStoreError error) {
                setupViewsVisibility(false);

            }
        });
    }

    private void initializeViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mErrorLayout = (RelativeLayout) findViewById(R.id.errorLayout);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
    }

    /**
     * populates recycler view adapter with @param receiptList
     *
     * @param receiptList list of @Receipt .
     */
    private void loadReceipts(List<Receipt> receiptList) {
        if (receiptList != null) {
            sortReceiptsByName(receiptList);
            ReceiptsAdapter receiptsAdapter = new ReceiptsAdapter(this, receiptList);
            //Adding ScaleIn animation to adapter
            mRecyclerView.setAdapter(new ScaleInAnimationAdapter(receiptsAdapter));
        }

    }

    /**
     * adjusts visibility of the views
     *
     * @param dataDownloaded true if data was downloaded, false otherwise
     */
    private void setupViewsVisibility(boolean dataDownloaded) {
        mProgressBar.setVisibility(View.GONE);
        if (!dataDownloaded) {
            mErrorLayout.setVisibility(View.VISIBLE);

        } else {
            mErrorLayout.setVisibility(View.GONE);
        }
    }


    /**
     * sorts the list of @Receipt in alphabetical order of name
     *
     * @param receiptList
     */
    private void sortReceiptsByName(List<Receipt> receiptList) {
        Collections.sort(receiptList, new Comparator<Receipt>() {
            @Override
            public int compare(Receipt lhs, Receipt rhs) {
                return lhs.receiptName.compareTo(rhs.receiptName);
            }
        });
    }

    /**
     * Callback function invoked when Retry button is clicked from the layout
     *
     * @param view clicked view(button in our case)
     */
    public void onRetryClicked(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        downloadReceipts();
    }


}



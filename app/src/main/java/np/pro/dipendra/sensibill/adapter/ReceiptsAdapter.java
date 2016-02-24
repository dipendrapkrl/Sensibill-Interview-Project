package np.pro.dipendra.sensibill.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import np.pro.dipendra.sensibill.R;
import np.pro.dipendra.sensibill.api.model.Receipt;

/**
 * Created by dipendra on 2/23/16.
 */
public class ReceiptsAdapter extends RecyclerView.Adapter<ReceiptsAdapter.ReceiptViewHolder> {


    private List<Receipt> receiptList;

    private Context mContext;

    public ReceiptsAdapter(Context context, List<Receipt> receiptList) {
        this.receiptList = receiptList;
        this.mContext = context;
    }

    @Override
    public ReceiptViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.receipt_row, null);
        ReceiptViewHolder receiptViewHolder = new ReceiptViewHolder(v);

        return receiptViewHolder;
    }

    @Override
    public void onBindViewHolder(ReceiptViewHolder receiptViewHolder, int i) {
        Receipt receiptItem = receiptList.get(i);

        receiptViewHolder.tvName.setText(receiptItem.display.name);
        receiptViewHolder.tvAmount.setText(receiptItem.display.amount);
        receiptViewHolder.tvDate.setText(receiptItem.display.date);
    }

    @Override
    public int getItemCount() {
        return (null != receiptList ? receiptList.size() : 0);
    }


    public static class ReceiptViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvName;
        protected TextView tvAmount;
        protected TextView tvDate;
        protected CardView cardView;

        public ReceiptViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.card_view);
            this.tvName = (TextView) cardView.findViewById(R.id.tvName);
            this.tvAmount = (TextView) cardView.findViewById(R.id.tvAmount);
            this.tvDate = (TextView) cardView.findViewById(R.id.tvDate);
        }

    }
}

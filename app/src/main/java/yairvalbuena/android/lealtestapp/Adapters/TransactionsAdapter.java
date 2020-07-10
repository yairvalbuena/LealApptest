package yairvalbuena.android.lealtestapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import yairvalbuena.android.lealtestapp.Model.Transaction;
import yairvalbuena.android.lealtestapp.R;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionAdapterHolder> {

    private List<Transaction> transactions;
    private Context context;
    private OnTransactionListener onTransactionListener;

    public TransactionsAdapter (List<Transaction> transactions,Context pContext, OnTransactionListener onTransactionListener)
    {
        this.transactions = transactions;
        this.context = pContext;
        this.onTransactionListener = onTransactionListener;
    }


    @NonNull
    @Override
    public TransactionAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context)
                .inflate(R.layout.transaction_row, parent, false);
        return new TransactionAdapterHolder(itemView, onTransactionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapterHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.createdDate.setText(transaction.createdDate.split("T")[0]);
        holder.nameBranch.setText(transaction.branch.name);
        holder.nameComercio.setText(transaction.commerce.name);
        if(position>19)
        {
            holder.check.setVisibility(View.GONE);
        }
        else
        {
            holder.check.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setData(List<Transaction> transactions)
    {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    public class TransactionAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView createdDate;
        private TextView nameComercio;
        private TextView nameBranch;
        private ToggleButton check;
        OnTransactionListener onTransactionListener;
        public TransactionAdapterHolder(@NonNull View itemView, OnTransactionListener onTransactionListener) {
            super(itemView);
            createdDate = itemView.findViewById(R.id.Date);
            nameComercio = itemView.findViewById(R.id.Comercio);
            nameBranch = itemView.findViewById(R.id.Branch);
            check = itemView.findViewById(R.id.checkT);
            this.onTransactionListener = onTransactionListener ;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTransactionListener.OnTransactionClick(getAdapterPosition());
            check.setChecked(false);
        }
    }

    public interface OnTransactionListener {
        void OnTransactionClick(int position);
    }

}

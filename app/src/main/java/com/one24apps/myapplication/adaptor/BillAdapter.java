package com.one24apps.myapplication.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one24apps.myapplication.R;
import com.one24apps.myapplication.model.Bills;

import java.util.ArrayList;
import java.util.List;

// RecyclerView: for display list/Grid items.
//  -> It create the list for screen height, if you scrooldown then old record of OFF screen will be recycled and gets new record according to Index   ===>(By onBindViewHolder()).
public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillsHolder> {
    private List<Bills> billsList = new ArrayList<>();
    @NonNull
    @Override // object converting from View to Activity(XML to Java)
    public BillsHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
//        Inflater reads XML tags and create Java obj.
        View itemView = LayoutInflater.from(parent.getContext()) // Inflate: process of adding view(.xml) to Activity on runtime.
                .inflate(R.layout.bills_list, parent, false);

        return new BillsHolder(itemView);
    }

    @Override
    public void onBindViewHolder (@NonNull BillsHolder holder, int position) {
        Bills bill = billsList.get(position);

        holder.amount_text.setText((int) bill.getAmount());
        holder.payee_text.setText(bill.getPayee());


    }

    @Override
    public int getItemCount () {
        return billsList.size();
    }

    public void setBillsList(List<Bills> billsList){
        this.billsList = billsList;
        notifyDataSetChanged();
    }

    class BillsHolder extends RecyclerView.ViewHolder {

        private TextView payee_text;
        private TextView billType_text;
        private TextView amount_text;

        BillsHolder(View itemView) {
            super(itemView);

            payee_text = itemView.findViewById(R.id.bill_payee_name);
            billType_text = itemView.findViewById(R.id.bill_type);
            amount_text = itemView.findViewById(R.id.bill_amount);

        }
    }
}

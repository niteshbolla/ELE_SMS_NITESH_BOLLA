package com.example.itcinfotech.ele_sms_nitesh_bolla.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itcinfotech.ele_sms_nitesh_bolla.Database.Message;
import com.example.itcinfotech.ele_sms_nitesh_bolla.R;

import java.util.List;

/**
 * Created by Nitesh Bolla on 1/31/2018.
 */

public class CreditCardDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Message> messageList;

    /*
    constructor to accept the list from the activity
     */
    public CreditCardDetailAdapter(Context context, List<Message> list) {
        this.context = context;
        this.messageList=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.transactiondetails_adapter, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            final ItemViewHolder item_holder = (ItemViewHolder) holder;
            item_holder.srNo.setText(String.valueOf(position+1));
            item_holder.bankName.setText(messageList.get(position).getBankName());
            item_holder.amount.setText(messageList.get(position).getTransactionAmount());
            item_holder.creditCardNumber.setText(messageList.get(position).getCardNumber());
            item_holder.transactionTime.setText(messageList.get(position).getTransactionTime());
            item_holder.messageReceivedTime.setText(messageList.get(position).getMessage_recieve_time());
        }
    }

    @Override
    public int getItemCount() {
       return messageList.size();

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView srNo,bankName,amount,creditCardNumber,transactionTime,messageReceivedTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            srNo=(TextView)itemView.findViewById(R.id.sr_value);
            bankName=(TextView)itemView.findViewById(R.id.bank_name_value);
            creditCardNumber=(TextView)itemView.findViewById(R.id.card_number_value);
            amount=(TextView)itemView.findViewById(R.id.amount_value);
            transactionTime=(TextView)itemView.findViewById(R.id.date_value);
            messageReceivedTime=(TextView)itemView.findViewById(R.id.received_time_value);
        }


    }


}


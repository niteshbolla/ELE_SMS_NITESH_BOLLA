package com.example.itcinfotech.ele_sms_nitesh_bolla.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itcinfotech.ele_sms_nitesh_bolla.Adapters.CreditCardDetailAdapter;
import com.example.itcinfotech.ele_sms_nitesh_bolla.Database.DataBaseHelper;
import com.example.itcinfotech.ele_sms_nitesh_bolla.Database.Message;
import com.example.itcinfotech.ele_sms_nitesh_bolla.Database.Tables;
import com.example.itcinfotech.ele_sms_nitesh_bolla.R;
import com.example.itcinfotech.ele_sms_nitesh_bolla.Util.Constants;

import java.util.List;

/**
 * Created by Nitesh Bolla on 1/31/2018.
 */

public class CreditCardDetailListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CreditCardDetailAdapter creditCardDetailAdapter;
    private TextView no_content_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card_list_activity);
        initview();
        getDetailsofCreditCardTransaction();
    }
    private void initview() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        no_content_txt=(TextView)findViewById(R.id.no_content);
    }

    private void getDetailsofCreditCardTransaction()
    {


        List<Message>messages= Tables.getMessageForCreditCards(DataBaseHelper.getInstance(this));
        Log.d("list_size",String.valueOf( messages.size()));
        List<Message> list2= Tables.getTransactionDetails(messages);
        Log.e("list2 size",String.valueOf(list2.size()));
        if(list2.size()!=0)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            creditCardDetailAdapter=new CreditCardDetailAdapter(this,list2);
            recyclerView.setAdapter(creditCardDetailAdapter);

            Toast.makeText(CreditCardDetailListActivity.this, Constants.CREDIT_CARD_MESSAGE_TEXT, Toast.LENGTH_SHORT).show();
        }
        else
        {

            recyclerView.setVisibility(View.GONE);
            no_content_txt.setVisibility(View.VISIBLE);
        }
    }
}

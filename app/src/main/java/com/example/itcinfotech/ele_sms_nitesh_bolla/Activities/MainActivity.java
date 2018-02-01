package com.example.itcinfotech.ele_sms_nitesh_bolla.Activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.itcinfotech.ele_sms_nitesh_bolla.Database.DataBaseHelper;
import com.example.itcinfotech.ele_sms_nitesh_bolla.Database.Message;
import com.example.itcinfotech.ele_sms_nitesh_bolla.Database.Tables;
import com.example.itcinfotech.ele_sms_nitesh_bolla.R;
import com.example.itcinfotech.ele_sms_nitesh_bolla.Util.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Message> messages = new ArrayList<>();
    private Button btnSaveMsgToDb, btnRetrieveCreditCardDetails;
    private long y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

        int version = Build.VERSION.SDK_INT;
        if (version >= android.os.Build.VERSION_CODES.M)
            checkPermission();
        else
            setListener();

    }

    private void checkPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_SMS},
                1);
    }


    private void setListener() {
        btnSaveMsgToDb.setOnClickListener(this);
        btnRetrieveCreditCardDetails.setOnClickListener(this);
    }

    private void initview() {
        btnSaveMsgToDb = (Button) findViewById(R.id.button1);
        btnRetrieveCreditCardDetails = (Button) findViewById(R.id.button2);
    }


    /*
    method to get all the SMS from the inbox and store it in the db
     */
    public List<Message> getAllSmsFromProvider() {
        List<Message> lstSms = new ArrayList<>();
        ContentResolver cr = this.getContentResolver();

        Cursor c = cr.query(Telephony.Sms.Inbox.CONTENT_URI, // Official CONTENT_URI from docs
                new String[]{}, // Select body text
                null,
                null,
                Telephony.Sms.Inbox.DEFAULT_SORT_ORDER); // Default sort order

        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                Message message = new Message();
                message.setMessage_id(Integer.parseInt(c.getString(c.getColumnIndexOrThrow("_id"))));
                message.setMessage_from(c.getString(c.getColumnIndexOrThrow("address")));
                message.setMessage_body(c.getString(c.getColumnIndexOrThrow("body")));
                if (c.getString(c.getColumnIndexOrThrow("body")) != null) {
                    String date = getActualDate(c.getString(c.getColumnIndexOrThrow("date")));
                    message.setMessage_recieve_time(date);
                }
                c.moveToNext();
                lstSms.add(message);
            }
        } else {
            throw new RuntimeException("You have no SMS in Inbox");
        }
        c.close();

        return lstSms;
    }


    /*
    Method to fetch the date from milliseconds i.e milliseconds are provided as date for SMS received time
     */
    private String getActualDate(String milliseconds) {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss:aa");

        long milliSeconds = Long.parseLong(milliseconds);
        Log.i("date in milliseconds ", String.valueOf(milliSeconds));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        Log.i("Actual date ", (formatter.format(calendar.getTime())));

        return formatter.format(calendar.getTime());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:




                messages = getAllSmsFromProvider(); //retrieving the SMS from the inbox
                if (messages != null) {
                    y = Tables.insertMessageDetails(messages, DataBaseHelper.getInstance(this)); //inserting the messages into the Database
                    if (y != 0) {

                        Toast.makeText(MainActivity.this, Constants.PROGRESS_MESSAGE_TEXT, Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.button2:
                Intent intent = new Intent(MainActivity.this, CreditCardDetailListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setListener();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, Constants.PERMISSION_DENIAL_MSG, Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}








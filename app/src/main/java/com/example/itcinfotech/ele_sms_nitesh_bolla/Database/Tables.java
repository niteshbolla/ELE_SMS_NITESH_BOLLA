package com.example.itcinfotech.ele_sms_nitesh_bolla.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.itcinfotech.ele_sms_nitesh_bolla.Util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nitesh Bolla on 1/30/2018.
 */

public class Tables {

    public static String TABLE_MESSAGE = "message";

    // create table syntax for message table
    public static String create_table_message = "CREATE TABLE " + TABLE_MESSAGE + "\n" +
            "(\n" +
            "  message_id INTEGER NOT NULL,\n" +
            "  message_from  VARCHAR,\n" +
            "  message_body VARCHAR,\n" +
            "  message_recieve_time DATETIME,\n" +
            "  message_type VARCHAR,\n" +
            "  PRIMARY KEY (message_id)\n" +
            ");\n";


    //inserting the values inside the table messages
    public static long insertMessageDetails(List<Message> responseData, DataBaseHelper instance) {
        SQLiteDatabase db = instance.getWritableDatabase();
        long y = 0;
        for (int i = 0; i < responseData.size(); i++) {
            try {
                ContentValues cv = new ContentValues();
                cv.put("message_id", responseData.get(i).getMessage_id());
                cv.put("message_from", responseData.get(i).getMessage_from());
                cv.put("message_body", responseData.get(i).getMessage_body());
                cv.put("message_recieve_time", responseData.get(i).getMessage_recieve_time());
                cv.put("message_type", responseData.get(i).getMessage_type());
                Cursor cursor = db.rawQuery("select message_id from " + Tables.TABLE_MESSAGE + " where message_id = ?", new String[]{String.valueOf(responseData.get(i).getMessage_id())});
                try {
                    if (cursor.getCount() > 0) {
                        y = db.update(Tables.TABLE_MESSAGE, cv, "message_id = ?", new String[]{String.valueOf(responseData.get(i).getMessage_id())});
                    } else {
                        y = db.insert(Tables.TABLE_MESSAGE, null, cv);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return y;
    }


    //selecting the messsages in ascending format and retrieving it
    public static List<Message> getMessageForCreditCards(DataBaseHelper dataBaseHelper) {
        List<Message> messageArrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from message ORDER BY datetime(message_recieve_time) ASC", new String[]{});
        try {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Message message = new Message();
                    message.setMessage_id(cursor.getInt(0));
                    message.setMessage_from(cursor.getString(1));
                    message.setMessage_body(cursor.getString(2));
                    message.setMessage_recieve_time(cursor.getString(3));
                    //message.setMessage_type(cursor.getString(4));
                    messageArrayList.add(message);

                }
            }

        } finally {
            cursor.close();
            return messageArrayList;

        }

    }


    //this method takes each messages and finds if the message is of credit card and accordingly perform operations

    public static ArrayList<Message> getTransactionDetails(List<Message> body_val) {
        ArrayList<Message> resSms = new ArrayList<>();
        for (int i = 0; i < body_val.size(); i++) {
            Message smsDto = body_val.get(i);
            Pattern regExAmount = Pattern.compile(Constants.REGEX_FOR_AMOUNT);

            Pattern regExDate = Pattern.compile(Constants.REGEX_FOR_DATE);
            Pattern regExCard = Pattern.compile(Constants.REGEX_FOR_CARD);

            // Find instance of pattern matches
            Matcher amountMatcher = regExAmount.matcher(smsDto.getMessage_body());
            Matcher dateMatcher = regExDate.matcher(smsDto.getMessage_body());
            Matcher cardMatcher = regExCard.matcher(smsDto.getMessage_body());


            if (amountMatcher.find()) {
                try {
                    Log.e("amount_value= ", "" + amountMatcher.group(0));
                    String amount = (amountMatcher.group(0).replaceAll("inr", ""));
                    amount = amount.replaceAll("rs", "");
                    amount = amount.replaceAll("inr", "");
                    amount = amount.replaceAll(" ", "");
                    amount = amount.replaceAll(",", "");



                        //to check if the message is of credit card by checking the word spent which is usually there in credit card messages

                      if (smsDto.getMessage_body().contains("spent")|smsDto.getMessage_body().contains("was spent")) {
                        smsDto.setMessage_type("1");
                        smsDto.setTransactionAmount(amount);
                        if (cardMatcher.find()) {
                            smsDto.setCardNumber(cardMatcher.group());
                            smsDto.setBankName(smsDto.getMessage_from().split("-")[1]);
                        }
                        if (dateMatcher.find()) {
                            Log.e("value= ", "" + dateMatcher.group());
                            smsDto.setTransactionTime(dateMatcher.group());
                        }
                        resSms.add(smsDto);
                    }

                    Log.e("matchedValue= ", "" + amount);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("No_matchedValue ", "No_matchedValue ");
            }
        }
        return resSms;
    }




}

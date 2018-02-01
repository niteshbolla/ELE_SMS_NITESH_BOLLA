package com.example.itcinfotech.ele_sms_nitesh_bolla.Database;

/**
 * Created by Nitesh Bolla on 1/30/2018.
 */

public class Message {

    /*
    model variables to sotre the message details like id ,from,body,receive_time ect
     */
    public int message_id;
    public String message_from;
    public String message_body;
    public String message_recieve_time;
    public String message_type;

    /*
    model variables to hold the credit card details like amount,card,number,time,bank_name
     */
    public String transactionAmount;
    public String cardNumber;
    public String transactionTime;
    public String bankName;


    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_from() {
        return message_from;
    }

    public void setMessage_from(String message_from) {
        this.message_from = message_from;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public String getMessage_recieve_time() {
        return message_recieve_time;
    }

    public void setMessage_recieve_time(String message_recieve_time) {
        this.message_recieve_time = message_recieve_time;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}

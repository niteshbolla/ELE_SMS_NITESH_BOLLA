package com.example.itcinfotech.ele_sms_nitesh_bolla.Util;


/*
All constants should be defined in this class which can be used throughout the application
 */
public class Constants {

    /*--
    Amount regex accepts currency suffix like
    RS,INR,MRP
     */
    public static final String REGEX_FOR_AMOUNT = "(?i)(?:(?:RS|INR|MRP)\\.?\\s?)(\\d+(:?\\,\\d+)?(\\,\\d+)?(\\.\\d{1,2})?)";

    /**
     * Regex Supports date formats like
     * dd-mmm-yy:HH:MM:SS
     * yyyy-mm-dd:HH:MM:SS
     * dd-mmm-yy
     */
    public static final String REGEX_FOR_DATE = "(" +
            "([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
            "|" +
            "([12]\\d{3})" +
            ")" +
            "[\\s|-]" +
            "(" +
            "(" +
            "(\\b(J|j)(A|a)(N|n)\\b|\\b(F|f)(E|e)(B|b)\\b|\\b(M|m)(A|a)(R|r)\\b|\\b(A|a)(P|p)(R|r)\\b|\\b(M|m)(A|a)(Y|y)\\b|\\b(J|j)(U|u)(N|n)\\b|\\b(J|j)(U|u)(L|l)\\b|\\b(A|a)(U|u)(G|g)\\b|\\b(S|s)(E|e)(P|p)\\b|\\b(O|o)(C|c)(T|t)\\b|\\b(N|n)(O|o)(V|v)\\b|\\b(D|d)(E|e)(C|c)\\b)" +
            ")" +
            "|" +
            "([1-9]|0[1-9]|1[0|1|2])" +
            ")" +
            "[\\s|-]" +
            "(" +
            "\\d{2}" +
            "|" +
            "([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
            ")" +
            "(" +
            "([\\:]" +
            "(" +
            "([1-9]|0[0-9]|1[0-9]|2[0-3])" +
            "[\\:]" +
            "([0-5][0-9])" +
            "[\\:]" +
            "([0-5][0-9])" +
            ")" +
            ")" +
            "?)";

    /*
    Regex For Credit Card Number
    formats
    1234XXXXXXXX5678 or 1234xxxxxxxx5678
    XXXX5678 or xxxx5678
    XX5678 or xx5678
     */
    public static final String REGEX_FOR_CARD = "(((\\d{4}(x|X{8}))|(x|X{4})|(x|X{2}))?)\\d{4}";

    public static final String PROGRESS_MESSAGE_TEXT="Messages are Stored into database";
    public static final String CREDIT_CARD_MESSAGE_TEXT="Following messages are for credit card";
    public static final String PERMISSION_DENIAL_MSG="Permission denied to read your SMS";


}


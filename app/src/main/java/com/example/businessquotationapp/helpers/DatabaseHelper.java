package com.example.businessquotationapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.businessquotationapp.data.Quotation;

public class DatabaseHelper {
    private static final String FILE_KEY = "db";

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    private static ModelBank<Quotation> quotationBank;

    public static void initialize(Context context) {
        sharedPref = context.getSharedPreferences(FILE_KEY, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        quotationBank = new ModelBank<>(sharedPref, editor, "quotations", Quotation.class);
    }

    public static ModelBank<Quotation> getQuotationBank() {
        return quotationBank;
    }
}

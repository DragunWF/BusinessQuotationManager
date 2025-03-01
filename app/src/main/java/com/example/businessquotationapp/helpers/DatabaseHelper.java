package com.example.businessquotationapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.businessquotationapp.data.Quotation;
import com.example.businessquotationapp.services.QuotationService;

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

    public static void addDummyData(){
        Quotation data1 = new Quotation("Isaac", "09123", "Mouse", "Accepted", "03/01/2025",
                300, QuotationService.generateQuotationNumber(),3);
        Quotation data2 = new Quotation("Marc", "09321321","Keyboard", "Declined","03/01/2025",
                1200, QuotationService.generateQuotationNumber(),4);
        Quotation data3 = new Quotation("Jed", "03521312","Fan", "Declined","03/05/2025",
                800, QuotationService.generateQuotationNumber(),9);
        Quotation data4 = new Quotation("Cosme", "023123123","Printer", "Accepted","02/04/2025",
                3000, QuotationService.generateQuotationNumber(),5);
        Quotation data5 = new Quotation("Ryan", "234221312","Chair", "Declined","03/09/2025",
                600, QuotationService.generateQuotationNumber(),20);

        QuotationService.addQuotation(data1);
        QuotationService.addQuotation(data2);
        QuotationService.addQuotation(data3);
        QuotationService.addQuotation(data4);
        QuotationService.addQuotation(data5);
    }
}

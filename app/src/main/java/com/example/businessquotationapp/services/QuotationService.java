package com.example.businessquotationapp.services;

import com.example.businessquotationapp.data.Quotation;
import com.example.businessquotationapp.helpers.DatabaseHelper;
import com.example.businessquotationapp.helpers.ModelBank;

public class QuotationService {
    public static int generateQuotationNumber() {
        int max = 99999;
        int min = 10000;
        return (int) (Math.random() * (max - min) + min);
    }

    public static void addQuotation(Quotation quotation) {
        ModelBank<Quotation> bank = DatabaseHelper.getQuotationBank();
        bank.add(quotation);
    }

    public static void editQuotation(Quotation quotation) {
        ModelBank<Quotation> bank = DatabaseHelper.getQuotationBank();
        bank.update(quotation);
    }

    public static void deleteQuotation(int quotationId) {
        ModelBank<Quotation> bank = DatabaseHelper.getQuotationBank();
        bank.delete(quotationId);
    }
}

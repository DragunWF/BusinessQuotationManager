package com.example.businessquotationapp.services;

import com.example.businessquotationapp.data.Quotation;
import com.example.businessquotationapp.helpers.DatabaseHelper;
import com.example.businessquotationapp.helpers.ModelBank;

public class QuotationService {
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

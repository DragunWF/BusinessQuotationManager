package com.example.businessquotationapp.helpers;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void longToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void toast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

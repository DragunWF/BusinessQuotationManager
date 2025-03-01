package com.example.businessquotationapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.businessquotationapp.helpers.Utils;

public class Metrics extends AppCompatActivity {

    private Button backButton;
    private TextView conversionRateTxt, pendingAmountTxt, acceptedAmountTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_metrics);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try{
            conversionRateTxt = findViewById(R.id.conversionRateText);
            pendingAmountTxt = findViewById(R.id.pendingTotalText);
            acceptedAmountTxt = findViewById(R.id.acceptedTotalText);

            backButton = findViewById(R.id.returnBtn);

            setButton();
        }
        catch (Exception e){
            e.printStackTrace();
            Utils.longToast(e.getMessage(), this);
        }
    }
    public void setButton(){
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(Metrics.this, MainActivity.class));
        });
    }
}
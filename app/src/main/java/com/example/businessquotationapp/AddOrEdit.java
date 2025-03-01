package com.example.businessquotationapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.businessquotationapp.data.Quotation;
import com.example.businessquotationapp.helpers.DatabaseHelper;
import com.example.businessquotationapp.helpers.Utils;
import com.example.businessquotationapp.services.QuotationService;

public class AddOrEdit extends AppCompatActivity {
    private TextView quotationNumberText, totalAmountText;
    private Button saveBtn, backBtn;
    private EditText nameText, phoneNumberText, itemNameText, quantityText, dateText, priceText;
    private Spinner statusSpinner;

    private int quotationNumber;
    private int viewedQuotationId; // -1 for no quotation viewed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_or_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            DatabaseHelper.initialize(this);

            quotationNumberText = findViewById(R.id.quotationNumberText);
            totalAmountText = findViewById(R.id.totalAmountText);

            saveBtn = findViewById(R.id.saveBtn);
            backBtn = findViewById(R.id.backBtn);

            nameText = findViewById(R.id.customerNameText);
            phoneNumberText = findViewById(R.id.phoneNumberText);
            itemNameText = findViewById(R.id.itemText);
            quantityText = findViewById(R.id.quantityText);
            dateText = findViewById(R.id.dateText);
            priceText = findViewById(R.id.priceText);

            statusSpinner = findViewById(R.id.statusSpinner);

            viewedQuotationId = getIntent().getIntExtra("quotationId", -1);
            if (viewedQuotationId != -1) {
                autoFillFields();
            } else {
                quotationNumber = QuotationService.generateQuotationNumber();
                quotationNumberText.setText("Quotation Number: " + quotationNumber);
                totalAmountText.setText("Total Amount: 0.0 PHP");
            }

            setButtons();
            setSpinner();
            setTextWatchers();
        } catch (Exception err) {
            err.printStackTrace();
            Utils.longToast(err.getMessage(), this);
        }
    }

    private void autoFillFields() {
        Quotation quotation = DatabaseHelper.getQuotationBank().get(viewedQuotationId);

        quotationNumberText.setText("Quotation Number: " + quotation.getQuotationNumber());
        totalAmountText.setText("Total Amount: " + quotation.getTotalAmount() + " PHP");

        nameText.setText(quotation.getCustomerName());
        phoneNumberText.setText(quotation.getPhone());
        itemNameText.setText(quotation.getItem());
        priceText.setText(String.valueOf(quotation.getPrice()));
        quantityText.setText(String.valueOf(quotation.getQuantity()));
        dateText.setText(quotation.getDate());
    }

    private void setButtons() {
        backBtn.setOnClickListener(v -> {
            finish();
        });
        saveBtn.setOnClickListener(v -> {
            final int CHAR_LIMIT = 500;

            String customerName = Utils.getText(nameText);
            String phoneNumber = Utils.getText(phoneNumberText);
            String item = Utils.getText(itemNameText);
            String price = Utils.getText(priceText);
            String quantity = Utils.getText(quantityText);
            String date = Utils.getText(dateText);
            String status = statusSpinner.getSelectedItem().toString();

            String[] fields = { customerName, phoneNumber, item, price, quantity, date };
            for (String field : fields) {
                if (field.isEmpty()) {
                    Utils.longToast("Please do not leave any of the fields empty!", AddOrEdit.this);
                    return;
                }
                if (field.length() > CHAR_LIMIT) {
                    Utils.longToast("None of the input fields should exceed " + CHAR_LIMIT + " characters!", AddOrEdit.this);
                    return;
                }
            }

            double formattedPrice = Double.parseDouble(price);
            int formattedQuantity = Integer.parseInt(quantity);

            boolean isEditForm = viewedQuotationId != -1;

            if (isEditForm) {
                Quotation quotation = DatabaseHelper.getQuotationBank().get(viewedQuotationId);
                quotation.setCustomerName(customerName);
                quotation.setPhone(phoneNumber);
                quotation.setItem(item);
                quotation.setPrice(formattedPrice);
                quotation.setQuantity(formattedQuantity);
                quotation.setDate(date);
                quotation.setStatus(status);
                QuotationService.editQuotation(quotation);
            } else {
                Quotation quotation = new Quotation(customerName, phoneNumber, item, status,
                        date, formattedPrice, quotationNumber, formattedQuantity);
                QuotationService.addQuotation(quotation);
            }

            Utils.longToast("Quotation saved successfully!", AddOrEdit.this);
            finish();
        });
    }

    private void setSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.status_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        statusSpinner.setAdapter(adapter);
    }

    private void setTextWatchers() {
        quantityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                totalAmountText.setText("Total Amount: " + getTotalAmount() + " PHP");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        priceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                totalAmountText.setText("Total Amount: " + getTotalAmount() + " PHP");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private double getTotalAmount() {
        String quantityStr = Utils.getText(quantityText);
        String priceStr = Utils.getText(priceText);
        int quantity = quantityStr.isEmpty() ? 0 : Integer.parseInt(quantityStr);
        double price = priceStr.isEmpty() ? 0 : Double.parseDouble(priceStr);
        return quantity * price;
    }
}
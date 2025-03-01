package com.example.businessquotationapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.businessquotationapp.helpers.Utils;

public class MainActivity extends AppCompatActivity {

    private Button manageBtn, addBtn;
    private Spinner categorySpinner;
    private SearchView searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            manageBtn = findViewById(R.id.manageBtn);
            addBtn = findViewById(R.id.addBtn);
            categorySpinner = findViewById(R.id.categorySpn);
            searchBar = findViewById(R.id.searchtxt);

            setSpinner();
            setButtons();
        }
        catch (Exception e){
            e.printStackTrace();
            Utils.longToast(e.getMessage(), this);
        }
    }

    public void setButtons(){
        manageBtn.setOnClickListener(v -> {

        });
        addBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddOrEdit.class));
        });
    }

    public void setSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.status_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        categorySpinner.setAdapter(adapter);
    }
}
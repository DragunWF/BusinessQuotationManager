package com.example.businessquotationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.businessquotationapp.adapters.QuotationAdapter;
import com.example.businessquotationapp.data.Quotation;
import com.example.businessquotationapp.helpers.DatabaseHelper;
import com.example.businessquotationapp.helpers.Utils;
import com.example.businessquotationapp.services.QuotationService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button manageBtn, addBtn;
    private Spinner categorySpinner;
    private SearchView searchBar;

    private RecyclerView quotationRecycler;
    private QuotationAdapter quotationAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onResume() {
        super.onResume();
        quotationAdapter.updateLocalDataSet();
        DatabaseHelper.getQuotationBank().log();
    }

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
            DatabaseHelper.initialize(this);

            manageBtn = findViewById(R.id.manageBtn);
            addBtn = findViewById(R.id.addBtn);
            categorySpinner = findViewById(R.id.categorySpn);
            searchBar = findViewById(R.id.searchtxt);
            quotationRecycler = findViewById(R.id.quotationRecycler);

            setSpinner();
            setButtons();
            setRecycler();
            setSearch();
            DatabaseHelper.addDummyData();
        }
        catch (Exception e){
            e.printStackTrace();
            Utils.longToast(e.getMessage(), this);
        }
    }

    public void setButtons(){
        manageBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Metrics.class));
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

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    quotationAdapter.updateLocalDataSet(DatabaseHelper.getQuotationBank().getAll());
                } else {
                    String selected = adapter.getItem(position).toString();
                    List<Quotation> results = new ArrayList<>();
                    for (Quotation quotation : DatabaseHelper.getQuotationBank().getAll()) {
                        if (quotation.getStatus().equals(selected)) {
                            results.add(quotation);
                        }
                    }
                    quotationAdapter.updateLocalDataSet(results);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setRecycler() {
        quotationRecycler.setHasFixedSize(false);

        quotationAdapter = new QuotationAdapter(DatabaseHelper.getQuotationBank().getAll(), this);
        quotationRecycler.setAdapter(quotationAdapter);

        layoutManager = new LinearLayoutManager(this);
        quotationRecycler.setLayoutManager(layoutManager);
    }

    private void setSearch() {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                update(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                update(newText);
                return false;
            }

            public void update(String query) {
                List<Quotation> quotations = DatabaseHelper.getQuotationBank().getAll();
                List<Quotation> results = new ArrayList<>();

                query = query.toLowerCase();
                for (Quotation quotation : quotations) {
                    String customerName = quotation.getCustomerName().toLowerCase();
                    if (customerName.contains(query)) {
                        results.add(quotation);
                    }
                }

                quotationAdapter.updateLocalDataSet(results);
            }
        });
    }
}
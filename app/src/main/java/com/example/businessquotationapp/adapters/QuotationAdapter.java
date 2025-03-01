package com.example.businessquotationapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.businessquotationapp.AddOrEdit;
import com.example.businessquotationapp.R;
import com.example.businessquotationapp.data.Quotation;
import com.example.businessquotationapp.helpers.DatabaseHelper;
import com.example.businessquotationapp.helpers.Utils;
import com.example.businessquotationapp.services.QuotationService;

import java.util.List;

public class QuotationAdapter extends RecyclerView.Adapter<QuotationAdapter.ViewHolder> {

    private List<Quotation> localDataSet;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView customerText, quotationNumber, dateText, totalText, statusText;
        private final Button editBtn, duplicateBtn;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            customerText = view.findViewById(R.id.customerNameText);
            quotationNumber = view.findViewById(R.id.quotationNumText);
            dateText = view.findViewById(R.id.dateText);
            totalText = view.findViewById(R.id.totalTxt);
            statusText = view.findViewById(R.id.statusText);

            editBtn = view.findViewById(R.id.editBtn);
            duplicateBtn = view.findViewById(R.id.duplicateBtn);
        }

        public TextView getCustomerText() {
            return customerText;
        }

        public TextView getQuotationNumber() {
            return quotationNumber;
        }

        public TextView getDateText() {
            return dateText;
        }

        public TextView getTotalText() {
            return totalText;
        }

        public TextView getStatusText() {
            return statusText;
        }

        public Button getEditBtn() {
            return editBtn;
        }

        public Button getDuplicateBtn() {
            return duplicateBtn;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public QuotationAdapter(List<Quotation> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_quotation, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // viewHolder.getTextView().setText(localDataSet[position]);

        Quotation quotation = localDataSet.get(position);
        viewHolder.getCustomerText().setText(quotation.getCustomerName());
        viewHolder.getQuotationNumber().setText(String.valueOf(quotation.getQuotationNumber()));
        viewHolder.getDateText().setText(quotation.getDate());
        viewHolder.getTotalText().setText(quotation.getTotalAmount() + " PHP");
        viewHolder.getStatusText().setText(quotation.getStatus());

        viewHolder.getEditBtn().setOnClickListener(v -> {
            Intent intent = new Intent(context, AddOrEdit.class);
            intent.putExtra("quotationId", quotation.getId());
            context.startActivity(intent);
        });
        viewHolder.getDuplicateBtn().setOnClickListener(v -> {
            QuotationService.addQuotation(quotation);
            Utils.longToast("Quotation has been successfully duplicated!", context);
            updateLocalDataSet();
        });
    }

    public void updateLocalDataSet() {
        List<Quotation> quotations = DatabaseHelper.getQuotationBank().getAll();
        updateLocalDataSet(quotations);
    }

    public void updateLocalDataSet(List<Quotation> quotations) {
        localDataSet.clear();
        for (Quotation quotation : quotations) {
            localDataSet.add(quotation);
        }
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

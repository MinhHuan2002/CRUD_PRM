package com.example.crud_sqlite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductRVAdapter extends
        RecyclerView.Adapter<ProductRVAdapter.ViewHolder> {
    // variable for our array list and context
    private ArrayList<ProductModal> productModalArrayList;
    private Context context;

    // constructor
    public ProductRVAdapter(ArrayList<ProductModal> productModalArrayList, Context context) {
        this.productModalArrayList = productModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ProductModal product = productModalArrayList.get(position);
        holder.productIdTV.setText(String.valueOf(product.getId()));
        holder.productNameTV.setText(product.getProductName());
        holder.productDescriptionTV.setText(product.getProductDescription());
        holder.productPriceTV.setText(product.getProductPrice());
        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateProduct.class);
                // below we are passing all our values.
                i.putExtra("name", product.getProductName());
                i.putExtra("description", product.getProductDescription());
                i.putExtra("price", product.getProductPrice());
                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return productModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private TextView productIdTV, productNameTV, productDescriptionTV, productPriceTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            productIdTV = itemView.findViewById(R.id.productId_tv);
            productNameTV = itemView.findViewById(R.id.productName_tv);
            productDescriptionTV = itemView.findViewById(R.id.productDescription_tv);
            productPriceTV = itemView.findViewById(R.id.productPrice_tv);
        }
    }
}

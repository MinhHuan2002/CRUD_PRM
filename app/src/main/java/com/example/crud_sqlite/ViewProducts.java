package com.example.crud_sqlite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewProducts extends AppCompatActivity {
    //Creating variables for our array list, dbhandler, adapter and recycler view
    private ArrayList<ProductModal> productModalArrayList;
    private DBHandler dbHandler;
    private ProductRVAdapter productRVAdapter;
    private RecyclerView productsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        //Initializing our all variables.
        productModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewProducts.this);
        //Getting our product array list from db handler class
        productModalArrayList = dbHandler.showProducts();
        //Passing array list to our adapter class
        productRVAdapter = new ProductRVAdapter(productModalArrayList, ViewProducts.this);
        //Creating recycler view
        productsRV = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewProducts.this, RecyclerView.VERTICAL, false);
        productsRV.setLayoutManager(linearLayoutManager);
        productsRV.setAdapter(productRVAdapter);//Setting our adapter to recycler view
    }
}
package com.example.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Creating variables for our edittext, button and dbhandler
    private EditText productNameTxt, productDescriptionTxt, productPriceTxt;
    private Button addProductBtn, showProductsBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing all our variables
        productNameTxt = findViewById(R.id.idTxtProductName);
        productDescriptionTxt = findViewById(R.id.idTxtProductDescription);
        productPriceTxt = findViewById(R.id.idTxtProductPrice);
        addProductBtn = findViewById(R.id.idBtnAddProduct);
        showProductsBtn = findViewById(R.id.idBtnShowProduct);
        //Creating a new dbhandler class and passing our context to it
        dbHandler = new DBHandler(MainActivity.this);
        //Below line is to add on click listener for our add product button
        addProductBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Getting data from all edit text fields
                String productName = productNameTxt.getText().toString();
                String productDescription = productDescriptionTxt.getText().toString();
                String productPrice = productPriceTxt.getText().toString();
                //Validating if the text fields are empty or not
                if (productName.isEmpty() && productDescription.isEmpty() && productPrice.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Adding a new product to sqlite data and pass all our values to it.
                dbHandler.addNewProduct(productName, productDescription, productPrice);
                //After adding the data we are displaying a toast message
                Toast.makeText(MainActivity.this, "Product has been added.", Toast.LENGTH_SHORT).show();
                productNameTxt.setText("");
                productDescriptionTxt.setText("");
                productPriceTxt.setText("");
            }
        });
        showProductsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Opening ViewProducts activity via a intent
                Intent i = new Intent(MainActivity.this, ViewProducts.class);
                startActivity(i);
            }
        });
    }
}
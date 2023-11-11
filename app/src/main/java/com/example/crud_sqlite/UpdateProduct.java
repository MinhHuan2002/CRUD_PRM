package com.example.crud_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateProduct extends AppCompatActivity {

    //Variables for our edit text, button, strings and dbhandler class
    private EditText productNameUpdate, productDescriptionUpdate, productPriceUpdate;
    private Button updateProductBtn, deleteProductBtn;
    private DBHandler dbHandler;
    String productName, productDescription, productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        productNameUpdate = findViewById(R.id.idUpdateProductName);
        productDescriptionUpdate = findViewById(R.id.idUpdateProductDescription);
        productPriceUpdate = findViewById(R.id.idUpdateProductPrice);
        updateProductBtn = findViewById(R.id.idBtnUpdateProduct);
        deleteProductBtn = findViewById(R.id.idBtnDeleteProduct);
        //Initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateProduct.this);
        //Getting data which we passed in our adapter class
        productName = getIntent().getStringExtra("name");
        productDescription = getIntent().getStringExtra("description");
        productPrice = getIntent().getStringExtra("price");
        //Setting data to edit text of our update activity
        productNameUpdate.setText(productName);
        productDescriptionUpdate.setText(productDescription);
        productPriceUpdate.setText(productPrice);
        //Adding on click listener to update product button
        updateProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calling a method to update our product
                dbHandler.updateProduct(productName,
                        productNameUpdate.getText().toString(),
                        productDescriptionUpdate.getText().toString(),
                        productPriceUpdate.getText().toString());
                Toast.makeText(UpdateProduct.this, "Product Updated..", Toast.LENGTH_SHORT).show();
                //Launching our main activity.
                Intent i = new Intent(UpdateProduct.this, MainActivity.class);
                startActivity(i);
            }
        });
        //Adding on click listener for delete button to delete our product.
        deleteProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calling a method to delete our product.
                dbHandler.deleteProduct(productName);
                Toast.makeText(UpdateProduct.this, "Deleted the product", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateProduct.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
package com.example.crud_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    // creating a constant variables for our database.
// below variable is for our database name.
    private static final String DB_NAME = "goods";
    // below int is our database version
    private static final int DB_VERSION = 1;
    // below variable is for our table name.
    private static final String TABLE_NAME = "product";
    // below variable is for our id column.
    private static final String ID_COL = "id";
    // below variable is for our product name column
    private static final String NAME_COL = "name";
    // below variable for our product description column.
    private static final String DESCRIPTION_COL = "description";
    // below variable id for our product price column.
    private static final String PRICE_COL = "price";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + PRICE_COL + " TEXT)";
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new product to our sqlite database.
    public void addNewProduct(String productName, String productDescription, String productPrice) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, productName);
        values.put(DESCRIPTION_COL, productDescription);
        values.put(PRICE_COL, productPrice);
        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);
        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the products.
    public ArrayList<ProductModal> showProducts() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorProducts = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        // on below line we are creating a new array list.
        ArrayList<ProductModal> productModalArrayList = new ArrayList<>();
        // moving our cursor to first position.
        if (cursorProducts.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                productModalArrayList.add(new ProductModal(cursorProducts.getString(1),
                        cursorProducts.getString(2),
                        cursorProducts.getString(3)));
            } while (cursorProducts.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorProducts.close();
        return productModalArrayList;
    }

    // below is the method for updating our products
    public void updateProduct(String originalProductName, String productName, String productDescription, String productPrice) {
        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, productName);
        values.put(DESCRIPTION_COL, productDescription);
        values.put(PRICE_COL, productPrice);
        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our product which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalProductName});
        db.close();
    }

    // below is the method for deleting our product.
    public void deleteProduct(String productName) {
        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are calling a method to delete our
        // product and we are comparing it with our product name.
        db.delete(TABLE_NAME, "name=?", new String[]{productName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

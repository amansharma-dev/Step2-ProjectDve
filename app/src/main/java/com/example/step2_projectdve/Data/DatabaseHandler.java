package com.example.step2_projectdve.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.step2_projectdve.Model.Product;
import com.example.step2_projectdve.R;
import com.example.step2_projectdve.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHandler";

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table

        String CREATE_TABLE = "CREATE TABLE "+Util.DATABASE_TABLE_NAME + "("
                +Util.COLUMN_ID + " INTEGER PRIMARY KEY,"
                +Util.COLUMN_NAME + " TEXT,"
                +Util.COLUMN_LINK + " TEXT" +")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d(TAG, "onCreate: TABLE CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.valueOf((R.string.drop_table));
        sqLiteDatabase.execSQL(DROP_TABLE,new String[]{Util.DATABASE_NAME});

        //create table again
        onCreate(sqLiteDatabase);
    }

    //CRUD

    //create-add-contentValues-insert

    public void addProduct(Product product){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.COLUMN_NAME,product.getName());
        contentValues.put(Util.COLUMN_LINK,product.getId());

        sqLiteDatabase.insert(Util.DATABASE_TABLE_NAME,null,contentValues);

        Log.d(TAG, "addProduct: PRODUCT INSERTED");
        //close connection
        sqLiteDatabase.close();
    }

    //read-singleProduct-cursor-query
    public Product getSingleProduct(int id){
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Util.DATABASE_TABLE_NAME,
                new String[]{Util.COLUMN_ID,Util.COLUMN_NAME,Util.COLUMN_LINK},
                Util.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null);

        if (cursor!=null)
            cursor.moveToFirst();
        Product product = new Product();
        product.setId(Integer.parseInt(cursor.getString(0)));
        product.setName(cursor.getString(1));
        product.setLink(cursor.getString(2));

        return product;
    }

    //read--allProduct--cursor-rawQuery
    public List<Product> getAllProduct(){
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String GET_ALL_PRODUCT = " SELECT * FROM "+ Util.DATABASE_TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_PRODUCT,null);

        if(cursor.moveToFirst()){
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setLink(cursor.getString(2));

                productList.add(product);

            }while(cursor.moveToNext());
        }
        return productList;
    }
}

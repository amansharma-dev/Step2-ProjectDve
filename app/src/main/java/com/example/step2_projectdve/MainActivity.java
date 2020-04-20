package com.example.step2_projectdve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.step2_projectdve.Adapter.RecyclerViewAdapter;
import com.example.step2_projectdve.Data.DatabaseHandler;
import com.example.step2_projectdve.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Product> productArrayList;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productArrayList = new ArrayList<>();

        DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);
//
//        databaseHandler.addProduct(new Product("Umano","https://umano.com/"));
//        databaseHandler.addProduct(new Product("Beard Head","https://beardhead.com/"));
//        databaseHandler.addProduct(new Product("Ice Shaker","https://www.iceshaker.com/"));
//        databaseHandler.addProduct(new Product("Ice Shaker","https://www.iceshaker.com/"));
//        databaseHandler.addProduct(new Product("Prx Performance","https://prxperformance.com/"));
//        databaseHandler.addProduct(new Product("Piper Wai","https://www.piperwai.com/"));
//        databaseHandler.addProduct(new Product("Love Pop","https://www.lovepopcards.com/"));
//        databaseHandler.addProduct(new Product("Nohbo Dops","https://nohbodrops.com/"));
//        databaseHandler.addProduct(new Product("Benjilock","https://benjilock.com/"));
//        databaseHandler.addProduct(new Product("R. Riveter","https://www.rriveter.com/"));
//        databaseHandler.addProduct(new Product("Slyde Handboards","https://www.slydehandboards.com/"));
//        databaseHandler.addProduct(new Product("Grip Clean","https://www.gripclean.com/"));
//

        //delete single product
//        databaseHandler.deleteSingleProduct(databaseHandler.getSingleProduct(13));

        //delete all products at once
//        databaseHandler.deleteAllProducts();
//        Log.d(TAG, "onCreate: ALL PRODUCT DELETED");


        //List all
        List<Product> productList = databaseHandler.getAllProduct();
        for (Product product : productList){
            Log.d(TAG, "onCreate: "+"NAME: "+product.getName()+", LINK: "+product.getLink());
            productArrayList.add(product);
        }

        //get count
        Log.d(TAG, "onCreate: total count:: "+databaseHandler.getCount());

        //setup adapter
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,productArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}

package com.example.step2_projectdve.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.step2_projectdve.Model.Product;
import com.example.step2_projectdve.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static final String TAG = "RecyclerViewAdapter";
    private Context context;
    private List<Product> productList;

    public RecyclerViewAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productLink.setText(product.getLink());
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView productName;
        public Button productLink;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName_textView);
            productLink = itemView.findViewById(R.id.productLink_Button);
            cardView = itemView.findViewById(R.id.cardProduct_cardView);
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorCardBackground));

            productLink.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d(TAG, "onClick: position "+position);
            Uri uri = Uri.parse(productLink.getText().toString());
            Log.d(TAG, "onClick: "+productLink.getText().toString());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            context.startActivity(intent);
        }
    }
}

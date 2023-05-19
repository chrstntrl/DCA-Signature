package com.example.dca;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
    private List<FoodListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public FoodListAdapter(List<FoodListElement> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public FoodListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.foods_list_elements, null);
        return new FoodListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final FoodListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<FoodListElement> items) {mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic1;
        TextView  name, desc, price;

        ViewHolder(View itemView) {
            super (itemView);
            pic1 = itemView.findViewById(R.id.pic1);
            name = itemView.findViewById(R.id.foodname1);
            desc = itemView.findViewById(R.id.fooddesc1);
            price = itemView.findViewById(R.id.foodprice1);
        }

        void bindData(final FoodListElement item) {
            pic1.setImageDrawable(item.getPic());
            name.setText(item.getName());
            desc.setText(item.getDesc());
            price.setText(item.getPrice());
        }
    }

}

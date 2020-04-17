package com.example.vmcassignment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<DataModel> names;
    int count = 0;

    public CustomAdapter(ArrayList<DataModel> names) {
        this.names = names;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.i("names.position---  ", String.valueOf(position));


/*
        if (count == 0) {
            holder.ndf.setVisibility(View.VISIBLE);
        } else {
            holder.ndf.setVisibility(View.GONE);
*/
            holder.title.setText("Title : " + names.get(position).getTitle());
            holder.description.setText("Description : " + names.get(position).getDescription());
            holder.price.setText("Price : " + names.get(position).getPrice());
//        }


    }

    @Override
    public int getItemCount() {
        Log.i("sizee", String.valueOf(names.size()));
        count = names.size();
        return names.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, description, ndf;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
            price = itemView.findViewById(R.id.tvPrice);
//            ndf = itemView.findViewById(R.id.ndf);
        }
    }

    public void filterList(ArrayList<DataModel> filterdNames) {
        this.names = filterdNames;
        notifyDataSetChanged();
    }
}

package com.example.smartclassapp.PDFWork;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.smartclassapp.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    public void update(String name, String url) {
        items.add(name);
        urls.add(url);
        notifyDataSetChanged(); //refreshes the recycler view automatically..
    }

    public MyAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls = urls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {// to create Views for recycler view items
        View view = LayoutInflater.from(context).inflate(R.layout.items, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        // initialize the element of indiv items..
        holder.nameOfFile.setText(items.get(i));
    }

    @Override
    public int getItemCount() {// return the no of items..

        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameOfFile;

        public ViewHolder(@NonNull View itemView) {// represent indiv list items..
            super(itemView);

            nameOfFile = itemView.findViewById(R.id.nameOfFile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int position = recyclerView.getChildLayoutPosition(v);
                    Intent intent = new Intent(context , ViewPdf.class);
                    intent.putExtra("pdf-url",urls.get(position));

                    context.startActivity(intent);

                }
            });


        }
    }
}
package com.example.smartclassapp.ProfileView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartclassapp.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Uploads> uploads;

    public MyAdapter(Context context, List<Uploads> uploads) {
        this.uploads = uploads;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_profile, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Uploads upload = uploads.get(position);

        holder.textViewName.setText(upload.getName());
        holder.contact.setText(upload.getContact());
        holder.carrier.setText(upload.getCarrier());


        Glide.with(context).load(upload.getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName,contact,carrier;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            contact = (TextView) itemView.findViewById(R.id.textViewContact);
            carrier = (TextView) itemView.findViewById(R.id.textViewCarrier);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
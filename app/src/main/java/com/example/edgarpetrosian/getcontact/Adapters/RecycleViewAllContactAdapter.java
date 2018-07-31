package com.example.edgarpetrosian.getcontact.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edgarpetrosian.getcontact.Engine.ModelGetContacts;
import com.example.edgarpetrosian.getcontact.R;

import java.util.List;

public class RecycleViewAllContactAdapter extends RecyclerView.Adapter<RecycleViewAllContactAdapter.MyViewHolder> {
    private List<ModelGetContacts> list;
    private Context context;

    public RecycleViewAllContactAdapter(List<ModelGetContacts> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_view_all_contact_item, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.contactName.setText(list.get(position).getName());
        holder.contactNumber.setText(list.get(position).getNumber());
        if (list.get(position).getUri().equals("No Image")) {
            holder.contactImage.setImageResource(R.drawable.favourite);
        } else {
            holder.contactImage.setImageURI(Uri.parse(list.get(position).getUri()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView contactImage;
        TextView contactName, contactNumber;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            contactImage = itemView.findViewById(R.id.rec_image_id);
          //  favoritBtnImage = itemView.findViewById(R.id.rec_image_favorit_id);
            contactName = itemView.findViewById(R.id.rec_name_id);
            contactNumber = itemView.findViewById(R.id.rec_number_id);
        }
    }

}

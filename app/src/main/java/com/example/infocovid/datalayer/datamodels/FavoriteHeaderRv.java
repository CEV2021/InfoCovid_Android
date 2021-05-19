package com.example.infocovid.datalayer.datamodels;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infocovid.R;

import java.util.ArrayList;

public class FavoriteHeaderRv extends RecyclerView.Adapter<FavoriteHeaderRv.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String headerText = "Lista de Favoritos";
        holder.bind(headerText);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    /*********************************************** viewholders ***********************************************/

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView header;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_header_item, parent, false));
            header = itemView.findViewById(R.id.favorite_header);
        }

        private void bind(String name) {
            header.setText(name);
        }
    }

}
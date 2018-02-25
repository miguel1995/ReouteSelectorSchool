package com.example.miguel.reouteselectorschool;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MIGUEL on 24/02/2018.
 */

class BusesAdapter extends RecyclerView.Adapter<BusesAdapter.ViewHolder> {

    ArrayList<Bus> listBus;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewID;
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewEstimatedTime;
        private TextView textViewRetryTime;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            //Se instancian los objetos a incorporar en cada Item de la lista
            textViewID = (TextView) itemView.findViewById(R.id.textView_id);
            textViewName = (TextView) itemView.findViewById(R.id.textView_name);
            textViewDescription = (TextView) itemView.findViewById(R.id.textView_description);
            textViewEstimatedTime = (TextView) itemView.findViewById(R.id.textView_estimated_time_milliseconds);
            textViewRetryTime = (TextView) itemView.findViewById(R.id.textView_retry_time_milliseconds);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    //constructor del adaptador
    public BusesAdapter(ArrayList<Bus> buses, Context context) {

        listBus = buses;
        this.context = context;
    }

    //Infla el contenido de un nuevo Item para la lista
    //enlaza un item layout con la lista
    @Override
    public BusesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // crea una nueva vista
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_bus, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //agrega los datos a los elementos de una tarjeta
    @Override
    public void onBindViewHolder(BusesAdapter.ViewHolder holder, int position) {
        holder.textViewID.setText( "Ruta No: " + String.valueOf(listBus.get(position).getId()));
        holder.textViewName.setText( listBus.get(position).getName());
        holder.textViewDescription.setText( listBus.get(position).getDescription());
        holder.textViewEstimatedTime.setText( String.valueOf(listBus.get(position).getRoute().getEstimatedTimeMilliseconds()));
        holder.textViewRetryTime.setText( String.valueOf(listBus.get(position).getRoute().getRetryTimeMilliseconds()));
        Picasso.with(context).load(listBus.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listBus.size();
    }

}

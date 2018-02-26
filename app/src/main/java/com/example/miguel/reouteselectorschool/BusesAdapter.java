package com.example.miguel.reouteselectorschool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        private Button itemButton;

        public ViewHolder(final View itemView) {
            super(itemView);

            //Se instancian los objetos a incorporar en cada Item de la lista
            textViewID = (TextView) itemView.findViewById(R.id.textView_id);
            textViewName = (TextView) itemView.findViewById(R.id.textView_name);
            textViewDescription = (TextView) itemView.findViewById(R.id.textView_description);
            textViewEstimatedTime = (TextView) itemView.findViewById(R.id.textView_estimated_time_milliseconds);
            textViewRetryTime = (TextView) itemView.findViewById(R.id.textView_retry_time_milliseconds);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemButton = (Button) itemView.findViewById(R.id.item_button);


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
    public void onBindViewHolder(BusesAdapter.ViewHolder holder, final int position) {
        holder.textViewID.setText( "Ruta No: " + String.valueOf(listBus.get(position).getId()));
        holder.textViewName.setText( listBus.get(position).getName());
        holder.textViewDescription.setText( listBus.get(position).getDescription());
        holder.textViewEstimatedTime.setText( String.valueOf(listBus.get(position).getRoute().getEstimatedTimeMinutes() + " seg."));
        holder.textViewRetryTime.setText( String.valueOf(listBus.get(position).getRoute().getRetryTimeMinutes()) + " seg.");
        Picasso.with(context).load(listBus.get(position).getImageUrl()).into(holder.imageView);

        holder.itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cuando el boton de una tarjeta sea ejecutado, se abre la activity con la ruta respectiva
                Intent intent = new Intent(context, MapsActivity.class);
                Route route = listBus.get(position).getRoute();
                intent.putExtra("pointsZise", route.getLongitudeOfTheStops().size());
                for(int i = 0; i < route.getLongitudeOfTheStops().size(); i++){
                    intent.putExtra("latitude" + i, route.getLatitudeOfTheStops().get(i));
                    intent.putExtra("longitude" + i, route.getLongitudeOfTheStops().get(i));
                }

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBus.size();
    }

}

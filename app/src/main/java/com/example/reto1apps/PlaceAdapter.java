package com.example.reto1apps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceView> {

    private ArrayList<AddPlace> places;

    public PlaceAdapter(){
        places = new ArrayList<>();
    }


    //Metodo que le entrega la info del modelo a la vista y se ejecuta por cada item
    @NonNull
    @Override
    public PlaceView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.placerow, null);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        PlaceView placeView = new PlaceView(rowroot);
        return placeView;
    }


    //Se carga cada info de cada uno de los renglones
    @Override
    public void onBindViewHolder(@NonNull PlaceView holder, int position) {
        holder.getNamePlace().setText(places.get(position).ge);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void addPlace(AddPlace place){
        places.add(place);

    }

}
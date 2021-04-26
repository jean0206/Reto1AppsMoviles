package com.example.reto1apps;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceView extends RecyclerView.ViewHolder {

    private ConstraintLayout root;
    private TextView namePlace;
    private TextView address;
    private ImageView imagePlace;

    public PlaceView(ConstraintLayout root) {
        super(root);
        this.root = root;
        namePlace = root.findViewById(R.id.namePlace);
        address = root.findViewById(R.id.addressPlace);
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public void setRoot(ConstraintLayout root) {
        this.root = root;
    }

    public TextView getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(TextView namePlace) {
        this.namePlace = namePlace;
    }

    public TextView getAddress() {
        return address;
    }

    public void setAddress(TextView address) {
        this.address = address;
    }

    public ImageView getImagePlace() {
        return imagePlace;
    }

    public void setImagePlace(ImageView imagePlace) {
        this.imagePlace = imagePlace;
    }
}

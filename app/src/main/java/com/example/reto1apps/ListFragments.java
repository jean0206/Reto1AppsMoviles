package com.example.reto1apps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragments extends AppCompatActivity {

    private RecyclerView placeViewList;
    private LinearLayoutManager layoutManager;
    private PlaceAdapter adapter;

    protected void OnCreate(Bundle savedInsanceState){
        setContentView(R.layout.activity_main);

        placeViewList = findViewById(R.id.placeViewList);
        placeViewList.setHasFixedSize(true);
        placeViewList.setLayoutManager(layoutManager);

        adapter = new PlaceAdapter();
        placeViewList.setAdapter(adapter);
    }


}

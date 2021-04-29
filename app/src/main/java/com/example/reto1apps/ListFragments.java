package com.example.reto1apps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragments extends AppCompatActivity implements View .OnClickListener{

    private RecyclerView placeViewList;
    private LinearLayoutManager layoutManager;
    private PlaceAdapter adapter;

    private EditText searchPlaceText;
    private Button buttonSearchPlace;


    protected void OnCreate(Bundle savedInsanceState){
        setContentView(R.layout.activity_main);

        placeViewList = findViewById(R.id.placeViewList);
        searchPlaceText = findViewById(R.id.searchPlaceText);
        buttonSearchPlace = findViewById(R.id.buttonSearchPlace);

        buttonSearchPlace.setOnClickListener(this);


        placeViewList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        placeViewList.setLayoutManager(layoutManager);

        //adapter = new PlaceAdapter();
        placeViewList.setAdapter(adapter);
    }


    //Aqui se hace el buscar
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSearchPlace:
                adapter.searchPlace(searchPlaceText.getText().toString());
                break;
        }
    }
}

package com.example.reto1apps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements View.OnClickListener, AddPlace.OnPlaceSave{


    private RecyclerView placeViewList;

    private LinearLayoutManager layoutManager;
    private PlaceAdapter adapter;

    private EditText searchPlaceText;
    private Button buttonSearchPlace;
    private ArrayList<Place> thePlaces;


    public SearchFragment() {
        // Required empty public constructor
        thePlaces = new ArrayList<>();
    }

    public RecyclerView getPlaceViewList() {
        return placeViewList;
    }

    public void setPlaceViewList(RecyclerView placeViewList) {
        this.placeViewList = placeViewList;
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public PlaceAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(PlaceAdapter adapter) {
        this.adapter = adapter;
    }

    public EditText getSearchPlaceText() {
        return searchPlaceText;
    }

    public void setSearchPlaceText(EditText searchPlaceText) {
        this.searchPlaceText = searchPlaceText;
    }

    public Button getButtonSearchPlace() {
        return buttonSearchPlace;
    }

    public void setButtonSearchPlace(Button buttonSearchPlace) {
        this.buttonSearchPlace = buttonSearchPlace;
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //thePlaces = new ArrayList<>();
        View root = inflater.inflate(R.layout.fragment_places, container, false);
        placeViewList = root.findViewById(R.id.placeViewList);

        layoutManager = new LinearLayoutManager(getActivity());

        addPlace(thePlaces.get(0));
        adapter = new PlaceAdapter(thePlaces);
        placeViewList.setAdapter(adapter);

        return root;
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSearchPlace:
                adapter.searchPlace(searchPlaceText.getText().toString());
                break;
        }
    }

    public void addPlace(Place place) {
        thePlaces.add(place);
    }

    @Override
    public void onPlaceSave(Place thePlace) {
        //SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPlace);
        //searchFragment.addPlace(thePlace);
        thePlaces.add(thePlace);
    }
    /*
    private void llenarLista(){
        thePlaces.add(new Place());
    }*/
    //Holi

/*
    //Lugar que va a llegar desde el otro fragmento
    @Override
    public void onPlaceSave(Place thePlace) {
        if (thePlace!=null) {
            thePlaces.add(thePlace);
        }else{
            Log.e("Error","es null");
        }
    }*/
}
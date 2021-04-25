package com.example.reto1apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener,modalFragment.OnOkListener{

    private AddPlace newAddPlaceFragment;
    private MapsFragment newMapFragment;
    private SearchFragment newSearchFragment;
    private BottomNavigationView navigator;
    //State


    private File file;

    public static final int PERMISSION_CALLBACK = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigator = findViewById(R.id.navigator);

        newAddPlaceFragment = AddPlace.newInstance();
        newMapFragment = new MapsFragment();
        newSearchFragment = SearchFragment.newInstance();
        showFragment(newAddPlaceFragment);




        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        },PERMISSION_CALLBACK);


        navigator.setOnNavigationItemSelectedListener(
                (menuItem)-> {
                    switch (menuItem.getItemId()){
                        case R.id.newPlace:
                            showFragment(newAddPlaceFragment);
                            break;
                        case R.id.menuMap:
                            showFragment(newMapFragment);
                            break;
                        case R.id.searchMenu:
                            showFragment(newSearchFragment);
                            break;
                    }
                    return true;
                }

        );

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode== PERMISSION_CALLBACK) {
            boolean allGrant = true;
            for (int i=0; i < grantResults.length; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    allGrant = false;
                    break;
                }
            }
            if (allGrant){
                Toast.makeText(this,"Todos los permisos fueron concebidos",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"No todos los permisos fueron concebidos",Toast.LENGTH_LONG).show();
            }
        }
    }



    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

    }



    @Override
    public void onOk(boolean setAddress) {

    }
}
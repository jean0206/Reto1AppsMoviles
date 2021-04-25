package com.example.reto1apps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPlace#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPlace extends Fragment implements View.OnClickListener {

    private ImageButton addImageButton;
    private ImageButton getUbication;
    private ImageView imageSelected;
    private TextView address;
    private File file;
    private MapsFragment maps;
    public final static int CAMERA_CALLBACK = 12;

    public AddPlace() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddPlace newInstance() {
        AddPlace fragment = new AddPlace();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_place,container,false);

        addImageButton = root.findViewById(R.id.addImageButton);
        imageSelected = root.findViewById(R.id.selectedImage);
        getUbication = root.findViewById(R.id.getUbication);
        address = root.findViewById(R.id.address);
        maps = new MapsFragment();
        addImageButton.setOnClickListener(this);
        getUbication.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.addImageButton):
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Context context = getContext();
                file = new File(context.getExternalFilesDir(null)+"/photo.png");
                Log.e("Ruta",""+file);
                Uri uri = FileProvider.getUriForFile(context,context.getPackageName(),file);
                i.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(i,CAMERA_CALLBACK);
                break;
            case (R.id.getUbication):
                showFragment(maps);
                break;
        }
    }
    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.commit();
    }

    public void addDirection(String texto){

        address.setText(texto);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_CALLBACK ) {
            Bitmap image = BitmapFactory.decodeFile(file.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(image,image.getWidth()/4,image.getHeight()/4,true);
            imageSelected.setImageBitmap(thumbnail);
        }else{
            Log.e("Error mi pai","sisa");
        }
    }
}
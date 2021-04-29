package com.example.reto1apps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.EditText;
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

    private String newNamePlace;
    private String newAddAddress;
    private ImageButton addImageButton;
    private ImageButton getUbication;
    private EditText namePlace;
    private ImageView imageSelected;
    private TextView address;
    private File file;
    private MapsFragment maps;
    private String path;

    private OnPlaceSave observer;
    private double lat;
    private double longi;


    public final static int CAMERA_CALLBACK = 12;

    public AddPlace() {
        // Required empty public constructor
    }

    public AddPlace(String newNamePlace, String newAddAddress, ImageButton addImageButton, ImageButton getUbication,
                    EditText namePlace, ImageView imageSelected, TextView address, File file, MapsFragment maps) {
        this.newNamePlace = newNamePlace;
        this.newAddAddress = newAddAddress;
        this.addImageButton = addImageButton;
        this.getUbication = getUbication;
        this.namePlace = namePlace;
        this.imageSelected = imageSelected;
        this.address = address;
        this.file = file;
        this.maps = maps;
    }

    public String getNewNamePlace() {
        return newNamePlace;
    }

    public void setNewNamePlace(String newNamePlace) {
        this.newNamePlace = newNamePlace;
    }

    public String getNewAddAddress() {
        return newAddAddress;
    }

    public void setNewAddAddress(String newAddAddress) {
        this.newAddAddress = newAddAddress;
    }

    public ImageButton getAddImageButton() {
        return addImageButton;
    }

    public void setAddImageButton(ImageButton addImageButton) {
        this.addImageButton = addImageButton;
    }

    public ImageButton getGetUbication() {
        return getUbication;
    }

    public void setGetUbication(ImageButton getUbication) {
        this.getUbication = getUbication;
    }

    public EditText getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(EditText namePlace) {
        this.namePlace = namePlace;
    }

    public ImageView getImageSelected() {
        return imageSelected;
    }

    public void setImageSelected(ImageView imageSelected) {
        this.imageSelected = imageSelected;
    }

    public TextView getAddress() {
        return address;
    }

    public void setAddress(TextView address) {
        this.address = address;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public MapsFragment getMaps() {
        return maps;
    }

    public void setMaps(MapsFragment maps) {
        this.maps = maps;
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
        View root = inflater.inflate(R.layout.fragment_add_place, container, false);
        Bundle bundle = this.getArguments();
        String newAddress = bundle.getString("address");
        Log.e("direccion:",""+newAddress);
        addImageButton = root.findViewById(R.id.addImageButton);
        imageSelected = root.findViewById(R.id.selectedImage);
        getUbication = root.findViewById(R.id.getUbication);
        address = root.findViewById(R.id.address);
        namePlace = root.findViewById(R.id.namePlace);
        address.setText(newAddress);
        maps = new MapsFragment();
        addImageButton.setOnClickListener(this);
        getUbication.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.addImageButton):
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Context context = getContext();
                file = new File(context.getExternalFilesDir(null) + "/photo.png");
                Log.e("Ruta", "" + file);
                Uri uri = FileProvider.getUriForFile(context, context.getPackageName(), file);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(i, CAMERA_CALLBACK);
                break;
            case (R.id.getUbication):
                MainActivity activity = (MainActivity) getActivity();
                activity.showFragment(activity.getMapsFragment());

                break;
            case (R.id.registerButton):
                String name = namePlace.getText().toString();
                String theAddress = address.getText().toString();
                Place thePlace = new Place(name, theAddress,"", 0.0,0.0,0);
                observer.onPlaceSave(thePlace);
                Log.e("sisa","sisa");
                break;

        }
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


    //Observer pattern
    public void setObserver(OnPlaceSave observer){
        this.observer = observer;
    }

    public interface OnPlaceSave{
        void onPlaceSave(Place thePlace);
    }

}
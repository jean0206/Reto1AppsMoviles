package com.example.reto1apps;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link modalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class modalFragment extends DialogFragment implements View.OnClickListener  {

    private Button sendButton;
    OnOkListener listener;

    public modalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment modalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static modalFragment newInstance(String param1, String param2) {
        modalFragment fragment = new modalFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_modal, container, false);
        sendButton = root.findViewById(R.id.sendAdressButton);
        sendButton.setOnClickListener(this);
        return root;
    }

    public void setListener(OnOkListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendAdressButton:
                Log.e("Error","Observer not found");
                if(listener==null){
                    Log.e("Error","Observer not found");
                }else{
                    Log.e("message","sisa");
                    listener.onOk(true);
                }

                break;
        }
    }

    public interface OnOkListener{

        void onOk(boolean setAddress);
    }
}
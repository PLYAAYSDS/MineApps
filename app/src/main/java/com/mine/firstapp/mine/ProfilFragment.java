package com.mine.firstapp.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment {
    private DatabaseReference ref, getDataref;
    private StorageReference storage, imagePath, filePath;
    private FirebaseAuth auth;
    private FirebaseUser user;
    Uri download;

    private User profile;
    String imagePosition;
    private ArrayList<String> pathArray;
    private int galery_intent = 2;
    private EditText email, nama, alamat, noHP;
    private ImageView photo;
    private TextView photoEdit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        Button btnUbah = (Button) view.findViewById(R.id.btnUbah);
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ubahProfil = new Intent(view.getContext(), EditProfile.class);
                startActivity(ubahProfil);
            }
        });

        return view;
    }

}

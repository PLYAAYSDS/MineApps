package com.mine.firstapp.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DaftarTopik.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DaftarTopik#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaftarTopik extends Fragment  implements ForumAdapter.OnItemClickListener{
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference ref;
    View rootView;
    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_TANGGAL = "tanggal";
    public static final String EXTRA_ISI = "isi";
    public static final String EXTRA_KEY = "key";


    private DatabaseReference mRootRef;
    private RecyclerView recyclerView;
    private ForumAdapter beritaAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Forum> allBerita;
    String a = "a";

    public DaftarTopik() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ref = database.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        View rootView = inflater.inflate(R.layout.activity_lihat_forum, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        allBerita = new ArrayList<Forum>();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        ValueEventListener beritaListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getAllTask(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
//Nothing here yet
            }
        };
        mRootRef.child("forum").addValueEventListener(beritaListener);

//        FloatingActionButton fab = (FloatingActionButton)
//                rootView.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), CreateQActivity.class);
//                startActivity(i);
//            }
//        });
        return rootView;
    }

    private void getAllTask(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
            Forum ber = singleSnapshot.getValue(Forum.class);
//Toast.makeText(getApplicationContext(),produk.getNama(),
            //           Toast.LENGTH_LONG).show();
            allBerita.add(ber);
            beritaAdapter = new ForumAdapter(getActivity(), allBerita);
            recyclerView.setAdapter(beritaAdapter);
            beritaAdapter.setItemOnClickListener(this);
        }

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), DetailPertanyaan.class);
        Forum clickedItem = allBerita.get(position);

        detailIntent.putExtra(EXTRA_JUDUL, clickedItem.getPertanyaan());
        detailIntent.putExtra(EXTRA_TANGGAL, clickedItem.getTopik());
        detailIntent.putExtra(EXTRA_ISI, clickedItem.getProfilId());
        detailIntent.putExtra(EXTRA_KEY, clickedItem.getKey());

        startActivity(detailIntent);
    }

}

package com.mine.firstapp.mine;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


public class BuatPertanyaan extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference forumRef;
    FirebaseUser user;
    FirebaseStorage storage;
    SharedPreferences countPertanyaan;

    private Forum forum;
    private Button kirim;
    private EditText topik, pertanyaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pertanyaan);

        forumRef = FirebaseDatabase.getInstance().getReference("forum");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        topik = findViewById(R.id.topik);
        pertanyaan = findViewById(R.id.pertanyaancreate);
        kirim = findViewById(R.id.kirim);

        forum = new Forum();

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPertanyaan();
            }
        });
    }

    public void insertPertanyaan(){
        final String ID = forumRef.push().getKey();
        forumRef.child(ID);
        forumRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                forum.setKey(ID);
                getData();
                forumRef.child(ID).setValue(forum);
                Toast.makeText(BuatPertanyaan.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BuatPertanyaan.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(){
        forum.setProfilId(auth.getUid());
        forum.setTopik(topik.getText().toString());
        forum.setPertanyaan(pertanyaan.getText().toString());
    }


}

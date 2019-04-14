package com.mine.firstapp.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import static com.mine.firstapp.mine.DaftarTopik.EXTRA_ISI;
import static com.mine.firstapp.mine.DaftarTopik.EXTRA_JUDUL;
import static com.mine.firstapp.mine.DaftarTopik.EXTRA_KEY;
import static com.mine.firstapp.mine.DaftarTopik.EXTRA_TANGGAL;

public class DetailPertanyaan extends AppCompatActivity {
    EditText jawab;
    Button btnJawab;
    Forum forum;
    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference forumRef;
    FirebaseUser user;
    FirebaseStorage storage;

    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pertanyaan);

        Intent intent = getIntent();

        String judul = intent.getStringExtra(EXTRA_JUDUL);
        String tanggal = intent.getStringExtra(EXTRA_TANGGAL);
        String isi = intent.getStringExtra(EXTRA_ISI);
        key = intent.getStringExtra(EXTRA_KEY);

        forumRef = FirebaseDatabase.getInstance().getReference("jawaban");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        jawab = findViewById(R.id.jawabPertanyaan);
        btnJawab = findViewById(R.id.jawabButton);
        TextView txtJudul = (TextView) findViewById(R.id.dJudul);
        TextView txtTanggal = (TextView) findViewById(R.id.dTanggal);
//        TextView txtIsi = (TextView) findViewById(R.id.dIsi);
        txtJudul.setText(tanggal);
        txtTanggal.setText(judul);
//        txtIsi.setText(isi);
        forum = new Forum();
        btnJawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertJawaban();
            }
        });
    }

    private void insertJawaban(){
        final String ID_JAWAB = forumRef.push().getKey();
        forumRef.child(key).child(ID_JAWAB);
        forumRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                forum.setKey(ID_JAWAB);
                forum.setJawaban(jawab.getText().toString());
                forumRef.child(key).child(ID_JAWAB).setValue(forum);
                Toast.makeText(DetailPertanyaan.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DetailPertanyaan.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

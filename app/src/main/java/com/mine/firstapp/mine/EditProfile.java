package com.mine.firstapp.mine;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class    EditProfile extends AppCompatActivity {
    private EditText nama,noHP,alamat;
    private Button smpn;
    private DatabaseReference ref, getDataref;
    private StorageReference storage, imagePath, filePath;
    private FirebaseAuth auth;
    private int galery_intent = 2;
    private FirebaseUser user;
    Uri download;
    private User profile;
    String imagePosition;
    private ImageView iV;

    private ArrayList<String> pathArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        iV = (ImageView)findViewById(R.id.uPhoto);
        nama = (EditText)findViewById(R.id.etNamaUser);
        noHP = (EditText)findViewById(R.id.etHPUser);
        alamat = (EditText)findViewById(R.id.etalamatUser);
        smpn = (Button)findViewById(R.id.btnUbah);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("profile");
        storage = FirebaseStorage.getInstance().getReference("user_image");

        profile = new User();

        iV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, galery_intent);
            }
        });
        getData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == galery_intent && resultCode == RESULT_OK){
            Uri uri = data.getData();
            iV.setImageURI(uri);
            imagePath = storage.child("profil").child(uri.getLastPathSegment());
            storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://mineapps-1st.appspot.com");
            imagePosition = uri.getLastPathSegment();

            filePath = storage.child("prodil").child(imagePosition);

            imagePath.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            download = taskSnapshot.getDownloadUrl();
                            Toast.makeText(EditProfile.this, "Uploaded...", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void getData(){
        getDataref = FirebaseDatabase.getInstance().getReference();
        getDataref.child("profile").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(EditProfile.this, "Have Data", Toast.LENGTH_SHORT).show();
                    User user = dataSnapshot.getValue(User.class);
                    nama.setText(user.getNama());
                    alamat.setText(user.getAlamat());
                    Glide.with(getApplicationContext()).load(user.getPhoto()).into(iV);
                }
                else{
                    Toast.makeText(EditProfile.this, "No data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void simpanData(){
        ref = FirebaseDatabase.getInstance().getReference("profile");
        ref.child(auth.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getValue();
                ref.child(user.getUid()).setValue(profile);
                Toast.makeText(EditProfile.this, "Data Inserted...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EditProfile.this,"Data Couldn't Inseted", Toast.LENGTH_SHORT).show();
            }
        });
//        getValue();
//        ref.child(user.getUid()).setValue(profile);
//        Toast.makeText(ProfilActivity.this,"Data Inserted...", Toast.LENGTH_SHORT).show();
    }

    private void getValue(){
        profile.setNama(nama.getText().toString());
        profile.setNoHP(noHP.getText().toString());
        profile.setEmail(user.getEmail());
        profile.setAlamat(alamat.getText().toString());
        profile.setPhoto(download.toString());
        profile.setuId(user.getUid());
    }
}

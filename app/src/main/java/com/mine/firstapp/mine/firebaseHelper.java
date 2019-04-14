//package com.mine.firstapp.mine;
//
//import android.content.Context;
//import android.net.Uri;
//import android.util.Log;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.google.android.gms.tasks.OnFailureListener;
//
//public class firebaseHelper {
//
//
//    private static final String TAG = firebaseHelper.class.getCanonicalName();
//
//    private firebaseHelper firebaseStorage;
//
//    private StorageReference rootRef;
//
//    private Context context;
//
//    public firebaseStorage(Context context){
//        this.context = context;
//        init();
//    }
//
//    private void init(){
//        this.firebaseStorage = FirebaseStorage.getInstance();
//        rootRef = firebaseStorage.getReferenceFromUrl("gs://fir-analyticexample.appspot.com");
//    }
//
//    public void saveProfileImageToCloud(String userId, Uri selectedImageUri, final ImageView imageView) {
//
//        StorageReference photoParentRef = rootRef.child(userId);
//        StorageReference photoRef = photoParentRef.child(selectedImageUri.getLastPathSegment());
//        UploadTask uploadTask = photoRef.putFile(selectedImageUri);
//
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG, "OnFailure " + e.getMessage());
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                Glide.with(context).load(downloadUrl.getPath()).into(imageView);
//            }
//        });
//
//    }
//}
//}

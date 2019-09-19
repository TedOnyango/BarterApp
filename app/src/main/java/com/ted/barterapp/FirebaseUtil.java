package com.ted.barterapp;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebasedatabase;
    public static DatabaseReference mDatabaseReference;
    public static FirebaseAuth mFirebaseAuth;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    public static FirebaseStorage mFirebasestorage;
    public static StorageReference mStorageReference;
    public static FirebaseUtil firebaseUtil;
    public static ChildEventListener mChildListener;
    public static ArrayList<BarterItem> mBarterItems;
    private static final int RC_SIGN_IN = 123;
    public static Activity caller;

    private FirebaseUtil() {}

    public static void openFbReference(String ref, final Activity callerActivity) {
       if (firebaseUtil == null) {
            new FirebaseUtil();
            mFirebasedatabase = FirebaseDatabase.getInstance();
            mFirebaseAuth = FirebaseAuth.getInstance();
           caller = callerActivity;
           mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() == null){
                        FirebaseUtil.singIn();
                    }
                    Toast.makeText(callerActivity.getBaseContext(), "Welcome back", Toast.LENGTH_LONG).show();


                }
            };


            mBarterItems = new ArrayList<BarterItem>();

       }
       mDatabaseReference = mFirebasedatabase.getReference().child(ref);
       connectStorage();

    }
    public static void singIn() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

    }
    public static void attachListener() {
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }
    public static void detachListener() {
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }

    public static void connectStorage() {
        mFirebasestorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebasestorage.getReference().child("barter_item_pictures");
    }
}

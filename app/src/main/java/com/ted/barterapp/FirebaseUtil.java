package com.ted.barterapp;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebasedatabase;
    public static DatabaseReference mDatabaseReference;
    public static FirebaseUtil firebaseUtil;
    public static ChildEventListener mChildListener;
    public static ArrayList<BarterItem> mBarterItems;

    private FirebaseUtil() {}

    public static void openFbReference(String ref) {
       if (firebaseUtil == null) {
            new FirebaseUtil();
            mFirebasedatabase = FirebaseDatabase.getInstance();
            mBarterItems = new ArrayList<BarterItem>();
       }
    }
}

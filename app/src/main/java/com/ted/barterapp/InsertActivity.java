package com.ted.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class InsertActivity extends AppCompatActivity {
    ImageView itemImage;
    EditText itemTitle;
    EditText itemDescription;
    EditText itemValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        itemImage = (ImageView) findViewById(R.id.itemImage);
        itemTitle = (EditText) findViewById(R.id.itemTitle);
        itemDescription = (EditText) findViewById(R.id.itemDescription);
        itemValue = (EditText) findViewById(R.id.itemValue);

    }
}

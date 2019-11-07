package com.ted.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemDetail extends AppCompatActivity {
    public TextView txtTitle;
    public TextView txtDescription;
    public TextView txtPreferredItems;
    public TextView txtEstimatedValue;
    public ImageView imageView;
    BarterItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtEstimatedValue = (TextView) findViewById(R.id.txtEstimatedValue);
        txtPreferredItems = (TextView) findViewById(R.id.txtPreferredItems);
        imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        BarterItem item = (BarterItem) intent.getSerializableExtra("BarterItem");
        this.item = item;
        txtTitle.setText(item.getTitle());
        txtDescription.setText(item.getDescription());
        txtEstimatedValue.setText(item.getEstimatedValue());
        txtPreferredItems.setText(item.getPreferredItmes());
        showImage(item.getImageUrl());



    }

    public void showImage(String url) {
        if (url != null && url.isEmpty() == false) {
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Picasso.get()
                    .load(url)
                    .resize(width, width*2/3)
                    .centerCrop()
                    .into(imageView);

        }

    }
}

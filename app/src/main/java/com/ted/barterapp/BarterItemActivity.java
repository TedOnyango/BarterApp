package com.ted.barterapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class BarterItemActivity extends AppCompatActivity {
    private static final int PICTURE_RESULT = 42;
    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference mDatabaseReference;
    EditText txtTitle;
    EditText txtDescription;
    EditText txtEstimatedValue;
    EditText txtPreferredItmes;
    ImageView txtImageUrl;
    Button uplpadButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        mFirebasedatabase = FirebaseUtil.mFirebasedatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;


        txtTitle = (EditText) findViewById(R.id.itemTitle);
        txtDescription = (EditText) findViewById(R.id.itemDescription);
        txtEstimatedValue = (EditText) findViewById(R.id.itemValue);
        txtPreferredItmes = (EditText) findViewById(R.id.preferredItems);
//        txtImageUrl = (ImageView) findViewById(R.id.itemImage);
        uplpadButton = (Button) findViewById(R.id.uploadImage);
        uplpadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(intent.createChooser(intent, "Insert Picture"), PICTURE_RESULT);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu:
                saveBarterItem();
                Toast.makeText(this,"Item saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void clean() {
        txtTitle.setText("");
        txtDescription.setText("");
        txtEstimatedValue.setText("");
        txtPreferredItmes.setText("");
        txtTitle.requestFocus();
    }

    private void saveBarterItem() {
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();
        String estimateValue = txtEstimatedValue.getText().toString();
        String preferredItems = txtPreferredItmes.getText().toString();
        BarterItem item = new BarterItem(title, description, estimateValue, preferredItems, "");
        mDatabaseReference.push().setValue(item);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK) {
            Uri file = data.getData();
            StorageReference reference = FirebaseUtil.mStorageReference.child(file.getLastPathSegment());
            reference.putFile(file);

        }
    }
}

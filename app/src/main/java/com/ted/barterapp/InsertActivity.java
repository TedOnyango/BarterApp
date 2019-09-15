package com.ted.barterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference mDatabaseReference;
    EditText txtTitle;
    EditText txtDescription;
    EditText txtEstimatedValue;
    EditText txtPreferredItmes;
    ImageView txtImageUrl;
    Button saveBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        mFirebasedatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebasedatabase.getReference().child("barterapp");


        txtTitle = (EditText) findViewById(R.id.itemTitle);
        txtDescription = (EditText) findViewById(R.id.itemDescription);
        txtEstimatedValue = (EditText) findViewById(R.id.itemValue);
        txtPreferredItmes = (EditText) findViewById(R.id.preferredItems);
//        txtImageUrl = (ImageView) findViewById(R.id.itemImage);
        saveBtn = (Button) findViewById(R.id.saveBtn);

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
}

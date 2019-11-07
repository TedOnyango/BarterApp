package com.ted.barterapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BarterItemAdapter extends RecyclerView.Adapter<BarterItemAdapter.BarterItemViewHolder> {
    public TextView tvTitle;
    public TextView tvDescription;
    public TextView tvPreferredItems;
    public TextView tvEstimatedValue;
    ArrayList<BarterItem> items;
    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    public ImageView imageItem;

    public BarterItemAdapter() {
        mFirebasedatabase = FirebaseUtil.mFirebasedatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        items = FirebaseUtil.mBarterItems;
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BarterItem bi = dataSnapshot.getValue(BarterItem.class);
                Log.d("Barter Item", bi.getTitle());
                bi.setId(dataSnapshot.getKey());
                items.add(bi);
                notifyItemInserted(items.size() - 1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);


    }


    @NonNull
    @Override
    public BarterItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.barter_item, parent, false);
        return new BarterItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarterItemViewHolder holder, int position) {
        BarterItem item = items.get(position);
        holder.bind(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BarterItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        public BarterItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvPreferredItems = (TextView) itemView.findViewById(R.id.tvPreferredItems);
            tvEstimatedValue = (TextView) itemView.findViewById(R.id.tvEstimatedValue);
            imageItem = (ImageView) itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }

        public void bind(BarterItem item) {
            tvTitle.setText(item.getTitle());
            tvDescription.setText(item.getDescription());
            tvPreferredItems.setText(item.getPreferredItmes());
            tvEstimatedValue.setText(item.getEstimatedValue());
            showImage(item.getImageUrl());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d("click", String.valueOf(position));
            BarterItem selectedItem = items.get(position);
            Intent intent = new Intent(v.getContext(), ItemDetail.class);
            intent.putExtra("BarterItem", selectedItem);
            v.getContext().startActivity(intent);

        }
    }

    public void showImage(String url) {
        if (url != null && url.isEmpty() == false) {
            Picasso.get()
                    .load(url)
                    .resize(80, 80)
                    .centerCrop()
                    .into(imageItem);
        }
    }
}
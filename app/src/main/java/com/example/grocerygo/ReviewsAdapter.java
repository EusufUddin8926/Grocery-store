package com.example.grocerygo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerygo.model.Review;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.viewHolder> {
    private static final String TAG = "ReviewsAdapter";

    private ArrayList<Review> reviews = new ArrayList<>();

    public ReviewsAdapter() {
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_adapter_rec_view,parent,false);
       viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.name.setText(reviews.get(position).getUsername());
        holder.text.setText(reviews.get(position).getText());
        holder.date.setText(reviews.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView name,text,date;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.username);
            text = itemView.findViewById(R.id.reviewtext);
            date = itemView.findViewById(R.id.txtdate);
        }

    }
}

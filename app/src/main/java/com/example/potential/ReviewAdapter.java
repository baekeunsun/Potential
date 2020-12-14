package com.example.potential;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private final ArrayList<Review> arrayList;
    private final Context context;

    public ReviewAdapter(ArrayList<Review>arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;

    }
    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        ReviewHolder holder = new ReviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position){
        holder.rv_content.setText(arrayList.get(position).getContent());
        holder.rv_email.setText(arrayList.get(position).getUsername());
        holder.rv_rating.setText(arrayList.get(position).getRating());


    }

    @Override
    public int getItemCount(){
        return(arrayList != null ? arrayList.size() : 0);
    }

    public  static final class ReviewHolder extends  RecyclerView.ViewHolder{

        TextView rv_content;
        TextView rv_email;
        TextView rv_rating;

        public ReviewHolder(@NonNull View itemView){
            super(itemView);
            this.rv_email = itemView.findViewById(R.id.rv_email);
            this.rv_content = itemView.findViewById(R.id.rv_content);
            this.rv_rating = itemView.findViewById(R.id.rv_rating);

        }
    }

}

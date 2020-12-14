package com.example.potential;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<User> arrayList;
    private final ArrayList<User> filterarrayList;

    private final Context context;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.filterarrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getStar())
                .into(holder.iv_star);
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_terri.setText(String.valueOf(arrayList.get(position).getTerri()));
        holder.tv_pro.setText(arrayList.get(position).getPro());
        holder.tv_rating.setText(arrayList.get(position).getRating());
        holder.tv_board.setText(arrayList.get(position).getBoard());


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, GalleryActivity.class);
                intent.putExtra("image_url", arrayList.get(position).getStar());
                intent.putExtra("image_name", arrayList.get(position).getName());
                intent.putExtra("addr", arrayList.get(position).getAddr());
                intent.putExtra("number", arrayList.get(position).getNumber());
                intent.putExtra("rating", arrayList.get(position).getRating());
                intent.putExtra("terri", arrayList.get(position).getTerri());
                intent.putExtra("pro", arrayList.get(position).getPro());
                // 게시판
                intent.putExtra("position", arrayList.get(position).getBoard());



                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //삼항연산자
        return (arrayList != null ? arrayList.size() : 0);
    }
    public void filterList(ArrayList<User> filterarrayList){
        arrayList = filterarrayList;
        notifyDataSetChanged();
    }

    public static final class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_star;
        TextView tv_name;
        TextView tv_terri;
        TextView tv_rating;
        TextView tv_pro;
        TextView tv_board;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_rating = itemView.findViewById(R.id.tv_rating);
            this.iv_star = itemView.findViewById(R.id.iv_star);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_terri = itemView.findViewById(R.id.tv_terri);
            this.tv_pro = itemView.findViewById(R.id.tv_pro);
            this.tv_board = itemView.findViewById(R.id.tv_board);
        }
    }
}

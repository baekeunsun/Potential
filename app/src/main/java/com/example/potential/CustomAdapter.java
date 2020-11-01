package com.example.potential;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<User> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        //삼항연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_star;
        TextView tv_name;
        TextView tv_terri;
        TextView tv_pro;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_star = itemView.findViewById(R.id.iv_star);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_terri = itemView.findViewById(R.id.tv_terri);
            this.tv_pro = itemView.findViewById(R.id.tv_pro);
        }
    }
}

package com.example.foodappproject.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodappproject.foodDetails.FoodDetailsActivity;
import com.example.foodappproject.R;
import com.example.foodappproject.model.Posts;

import java.util.List;

public class FastFoodAdapter extends RecyclerView.Adapter<FastFoodAdapter.FastFoodViewHolder> {
    List<Posts> postsList;
    Context context;

    public FastFoodAdapter(List<Posts> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public FastFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fastfood_item, parent, false);
        return new FastFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FastFoodViewHolder holder, int position) {

        Posts posts = postsList.get(position);

        Glide.with(context)
                .load(posts.getWebformatURL())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.fastFood_imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FoodDetailsActivity.class);
                i.putExtra("image", postsList.get(position).getWebformatURL());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    class FastFoodViewHolder extends RecyclerView.ViewHolder {

        ImageView fastFood_imageView;
        TextView fastFood_name_tv, fastFood_rating, recommended_delivery_time, delivery_type, fastFood_price;
        public FastFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            fastFood_imageView = itemView.findViewById(R.id.fastFood_imageView);
            fastFood_name_tv = itemView.findViewById(R.id.fastFood_name_tv);
            fastFood_rating = itemView.findViewById(R.id.fastFood_rating);
            recommended_delivery_time = itemView.findViewById(R.id.recommended_delivery_time);
            delivery_type = itemView.findViewById(R.id.delivery_type);
            fastFood_price = itemView.findViewById(R.id.fastFood_price);
        }
    }
}

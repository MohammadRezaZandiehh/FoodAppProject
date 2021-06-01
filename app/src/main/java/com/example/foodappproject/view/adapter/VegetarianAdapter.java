package com.example.foodappproject.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodappproject.foodDetails.FoodDetailsActivity;
import com.example.foodappproject.R;
import com.example.foodappproject.model.Posts;

import java.util.List;

public class VegetarianAdapter extends RecyclerView.Adapter<VegetarianAdapter.vegetarianViewHolder> {
    List<Posts> postsList;
    Context context;

    public VegetarianAdapter(List<Posts> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public vegetarianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vegetarian_item, parent, false);
        return new vegetarianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vegetarianViewHolder holder, int position) {
        Posts posts = postsList.get(position);
//        holder.tv_popular.setText(posts.getTags() + "");

        Glide.with(context)
                .load(posts.getWebformatURL())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.vegetarianImageView);

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


    class vegetarianViewHolder extends RecyclerView.ViewHolder {
        ImageView vegetarianImageView;

        public vegetarianViewHolder(@NonNull View itemView) {
            super(itemView);
            vegetarianImageView = itemView.findViewById(R.id.vegetarianImageView);
        }
    }
}

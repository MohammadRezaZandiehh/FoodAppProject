package com.example.foodappproject.main.adapter;

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

public class BarbecueAdapter extends RecyclerView.Adapter<BarbecueAdapter.BarbecueViewHolder> {
    Context context;
    List<Posts> postsList;

    public BarbecueAdapter(Context context, List<Posts> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public BarbecueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.barbecue_item, parent, false);
        return new BarbecueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarbecueViewHolder holder, int position) {
        Posts posts = postsList.get(position);

        Glide.with(context)
                .load(posts.getWebformatURL())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.barbecueImageView);


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

    class BarbecueViewHolder extends RecyclerView.ViewHolder {

        TextView barbecueName_tv, barbecueNote_tv, barbecue_rating_tv, barbecueDeliverytime, barbecue_delivery_charge, barbecue_price;
        ImageView barbecueImageView;

        public BarbecueViewHolder(@NonNull View itemView) {
            super(itemView);

            barbecueName_tv = itemView.findViewById(R.id.barbecueName_tv);
            barbecueNote_tv = itemView.findViewById(R.id.barbecueNote_tv);
            barbecue_delivery_charge = itemView.findViewById(R.id.barbecue_delivery_charge);
            barbecue_rating_tv = itemView.findViewById(R.id.barbecue_rating_tv);
            barbecueDeliverytime = itemView.findViewById(R.id.barbecue_deliverytime);
            barbecue_price = itemView.findViewById(R.id.barbecue_price);
            barbecueImageView = itemView.findViewById(R.id.barbecueImageView);


        }

    }

}

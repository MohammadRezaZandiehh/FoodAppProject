package com.example.foodappproject.foodDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodappproject.R;
import com.example.foodappproject.main.MainActivity;

public class FoodDetailsActivity extends AppCompatActivity {

    ImageView imageView, imageViewBack;
    TextView itemName, itemPrice, itemRating;
    RatingBar ratingBar;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("image");


        imageView = findViewById(R.id.imageView6);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.textViewPrice);
        itemRating = findViewById(R.id.textView_rating);
        ratingBar = findViewById(R.id.ratingBar);
        imageViewBack = findViewById(R.id.imageViewBack);

        Glide.with(getApplicationContext()).load(imageUrl)
                .into(imageView);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(FoodDetailsActivity.this, MainActivity.class);
                startActivity(intentBack);
            }
        });

    }
}
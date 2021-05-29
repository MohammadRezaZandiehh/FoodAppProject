package com.example.foodappproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.foodappproject.adapter.BarbecueAdapter;
import com.example.foodappproject.adapter.VegetarianAdapter;
import com.example.foodappproject.adapter.FastFoodAdapter;
import com.example.foodappproject.api.RetrofitClient;
import com.example.foodappproject.api.Service;
import com.example.foodappproject.model.PixabayPosts;
import com.example.foodappproject.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recommended_recycler;
    FastFoodAdapter recommendedAdapter;

    RecyclerView recyclerViewMain;
    VegetarianAdapter mainAdapter;

    BarbecueAdapter allMenuAdapter;
    RecyclerView recyclerViewAllMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitClient retrofitClient = new RetrofitClient();
        Service service = retrofitClient.getRetrofitInstance()
                .create(Service.class);

        //VegetarianFood
        service.getVegetarianFood().enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                vegetarianFood(response.body().getHits());
            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error !", Toast.LENGTH_SHORT).show();

            }
        });

        // FastFood
        service.getAllFastFood().enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                fastFood(response.body().getHits());
            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error !", Toast.LENGTH_SHORT).show();
            }
        });

        //barbecueFood
        service.getAllBarbecue().enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                barbecue(response.body().getHits());
            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error !", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void vegetarianFood(List<Posts> postsList) {
        recyclerViewMain = findViewById(R.id.rv_popular);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mainAdapter = new VegetarianAdapter(postsList, MainActivity.this);
        recyclerViewMain.setAdapter(mainAdapter);
    }


    public void fastFood(List<Posts> postsList) {
        recommended_recycler = findViewById(R.id.recommended_recycler);
        recommended_recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recommendedAdapter = new FastFoodAdapter(postsList, MainActivity.this);
        recommended_recycler.setAdapter(recommendedAdapter);
    }

    public void barbecue(List<Posts> postsList) {
        allMenuAdapter = new BarbecueAdapter(MainActivity.this, postsList);
        recyclerViewAllMenu = findViewById(R.id.rv_allMenu);
        recyclerViewAllMenu.setAdapter(allMenuAdapter);
        recyclerViewAllMenu.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
    }
}

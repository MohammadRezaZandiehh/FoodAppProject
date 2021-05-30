package com.example.foodappproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodappproject.adapter.BarbecueAdapter;
import com.example.foodappproject.adapter.VegetarianAdapter;
import com.example.foodappproject.adapter.FastFoodAdapter;
import com.example.foodappproject.api.RetrofitClient;
import com.example.foodappproject.api.Service;
import com.example.foodappproject.model.PixabayPosts;
import com.example.foodappproject.model.Posts;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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

    Disposable disposable;
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitClient retrofitClient = new RetrofitClient();
        service = retrofitClient.getRetrofitInstance()
                .create(Service.class);

        //VegetarianFood
        service.getVegetarianFood()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PixabayPosts>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onSuccess(@NonNull PixabayPosts pixabayPosts) {
                        vegetarianFood(pixabayPosts.getHits());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(MainActivity.this, "Error !", Toast.LENGTH_SHORT).show();
                    }
                });

        // FastFood
        service.getAllFastFood()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PixabayPosts>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onSuccess(@NonNull PixabayPosts pixabayPosts) {
                        fastFood(pixabayPosts.getHits());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(MainActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });
//
//        //barbecueFood
        service.getAllBarbecue()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PixabayPosts>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(@NonNull PixabayPosts pixabayPosts) {
                        barbecue(pixabayPosts.getHits());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(MainActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void vegetarianFood(List<Posts> postsList) {
        recyclerViewMain = findViewById(R.id.rv_vegetarian);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mainAdapter = new VegetarianAdapter(postsList, MainActivity.this);
        recyclerViewMain.setAdapter(mainAdapter);
    }


    public void fastFood(List<Posts> postsList) {
        recommended_recycler = findViewById(R.id.fastFood_recycler);
        recommended_recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recommendedAdapter = new FastFoodAdapter(postsList, MainActivity.this);
        recommended_recycler.setAdapter(recommendedAdapter);
    }

    public void barbecue(List<Posts> postsList) {
        allMenuAdapter = new BarbecueAdapter(MainActivity.this, postsList);
        recyclerViewAllMenu = findViewById(R.id.rv_barbecue);
        recyclerViewAllMenu.setAdapter(allMenuAdapter);
        recyclerViewAllMenu.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}

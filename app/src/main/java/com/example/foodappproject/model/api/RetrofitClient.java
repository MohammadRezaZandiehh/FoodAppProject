package com.example.foodappproject.model.api;

import com.example.foodappproject.model.PixabayPosts;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //    private static Retrofit retrofit;
    private static final String BASE_URL = "https://pixabay.com/api/";
    Service service;
    //    public static Retrofit getRetrofitInstance() {
//        if (retrofit == null) {

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(Service.class);
    }


    public Single<PixabayPosts> getBarbecue() {
        return service.getAllBarbecue();
    }

    public Single<PixabayPosts> getFastFood() {
        return service.getAllFastFood();
    }

    public Single<PixabayPosts> getVegetarian() {
        return service.getVegetarianFood();
    }
}

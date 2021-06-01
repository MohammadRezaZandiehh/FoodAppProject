package com.example.foodappproject.view;

import com.example.foodappproject.model.PixabayPosts;
import com.example.foodappproject.model.api.RetrofitClient;

import io.reactivex.Single;

public class MainViewModel {

private RetrofitClient retrofitClient;

    public MainViewModel(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    public Single<PixabayPosts> vegetarianData(){
        return retrofitClient.getVegetarian();
    }

    public Single<PixabayPosts> fastFoodData (){
        return retrofitClient.getFastFood();
    }

    public Single<PixabayPosts> barbecueData (){
        return retrofitClient.getBarbecue();
    }


}

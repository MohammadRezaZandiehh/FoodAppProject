package com.example.foodappproject.api;

import com.example.foodappproject.model.PixabayPosts;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    //https://pixabay.com/api/
    //https://pixabay.com/api/
    @GET("?key=20103943-432378ad3ccb78b05090ddc4b&q=food")
    Single<PixabayPosts> getVegetarianFood();

    @GET("?key=20103943-432378ad3ccb78b05090ddc4b&q=fastfood")
    Single<PixabayPosts>  getAllFastFood();

    @GET("?key=20103943-432378ad3ccb78b05090ddc4b&q=Barbecue")
    Single<PixabayPosts>  getAllBarbecue();
}

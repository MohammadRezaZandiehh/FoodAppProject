package com.example.foodappproject.view;

import com.example.foodappproject.model.PixabayPosts;
import com.example.foodappproject.model.api.RetrofitClient;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;

public class MainViewModel {

    private RetrofitClient retrofitClient;
    private BehaviorSubject<Boolean> progressBarSubject = BehaviorSubject.create();

    public MainViewModel(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    public Single<PixabayPosts> vegetarianData() {
        progressBarSubject.onNext(true);
        return retrofitClient.getVegetarian()
                .doFinally(() -> progressBarSubject.onNext(false));
        //agar list ro begire va ya agar khataei rokh bede bayad progressBar invisible beshe :
        // vase piade sazi e hamchin chizi az method e doFinally esefade mikonim :

    }

    public BehaviorSubject<Boolean> getProgressBarSubject() {
        return progressBarSubject;
    }

    public Single<PixabayPosts> fastFoodData() {
        return retrofitClient.getFastFood();
    }

    public Single<PixabayPosts> barbecueData() {
        return retrofitClient.getBarbecue();
    }


}

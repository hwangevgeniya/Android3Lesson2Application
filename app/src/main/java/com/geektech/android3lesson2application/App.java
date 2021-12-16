package com.geektech.android3lesson2application;

import android.app.Application;

import com.geektech.android3lesson2application.data.remote.HerokuApi;
import com.geektech.android3lesson2application.data.remote.RetrofitClient;

public class App extends Application {

    public static HerokuApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient client = new RetrofitClient();
        api = client.provideApi();
    }
}

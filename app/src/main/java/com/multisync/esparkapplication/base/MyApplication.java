package com.multisync.esparkapplication.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import com.multisync.esparkapplication.network.ApiClientAuth;
import com.multisync.esparkapplication.network.RetrofitServices;

import retrofit2.Retrofit;

public class MyApplication extends Application {

    private Activity activity = null;
    @SuppressLint("StaticFieldLeak")
    private static MyApplication mInstance;
    private RetrofitServices retrofitServices;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setCurrentActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public RetrofitServices getRetrofitServices() {
        Retrofit retrofit = ApiClientAuth.getRetrofitFactoryToken();
        retrofitServices = retrofit.create(RetrofitServices.class);
        return retrofitServices;
    }

}


package com.multisync.esparkapplication.network;

import com.multisync.esparkapplication.pojo.BaseResponse;
import com.multisync.esparkapplication.pojo.Devices;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitServices {

    @FormUrlEncoded
    @POST("auth/app/login")
    Call<ResponseBody> loginPin(@Field("loginPin") int loginPin);

    @GET("test_android/items.test")
    Call<BaseResponse<List<Devices>>> getDevicesList();


}

package com.multisync.esparkapplication.mvvm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.multisync.esparkapplication.base.MyApplication;
import com.multisync.esparkapplication.interfaces.OnErrorInteractor;
import com.multisync.esparkapplication.pojo.BaseResponse;
import com.multisync.esparkapplication.pojo.Devices;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceListRepo {

    OnErrorInteractor onErrorInteractor;
    MutableLiveData<BaseResponse<List<Devices>>> deviceMutableLiveData = new MutableLiveData<>();

    public DeviceListRepo(OnErrorInteractor onErrorInteractor) {
        this.onErrorInteractor = onErrorInteractor;
    }

    public void getDeviceList() {

        Call<BaseResponse<List<Devices>>> call = MyApplication.getInstance().getRetrofitServices().getDevicesList();
        call.enqueue(new Callback<BaseResponse<List<Devices>>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<List<Devices>>> call, @NotNull Response<BaseResponse<List<Devices>>> response) {

                if (!response.isSuccessful()) {
                    try {
                        assert response.errorBody() != null;
                        onErrorInteractor.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        onErrorInteractor.onError(e.getMessage());
                    }
                } else {
                    deviceMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<List<Devices>>> call, @NonNull Throwable t) {
                onErrorInteractor.onError(t.getMessage());
            }
        });
    }

    public MutableLiveData<BaseResponse<List<Devices>>> getDeviceMutableLiveData() {
        return deviceMutableLiveData;
    }
}

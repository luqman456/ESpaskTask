package com.multisync.esparkapplication.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.multisync.esparkapplication.interfaces.ErrorView;
import com.multisync.esparkapplication.interfaces.OnErrorInteractor;
import com.multisync.esparkapplication.pojo.BaseResponse;
import com.multisync.esparkapplication.pojo.Devices;

import java.util.List;

public class DeviceListViewModel extends AndroidViewModel implements OnErrorInteractor {
    ErrorView errorView;
    DeviceListRepo deviceListRepo;


    public DeviceListViewModel(@NonNull Application application) {
        super(application);
        deviceListRepo = new DeviceListRepo(this);
    }

    public void callDeviceListApi(){
        deviceListRepo.getDeviceList();
    }

    public MutableLiveData<BaseResponse<List<Devices>>> getDeviceList() {
        return deviceListRepo.getDeviceMutableLiveData();
    }

    public void setErrorView(ErrorView errorView) {
        this.errorView = errorView;
    }

    @Override
    public void onError(String error) {
        this.errorView.onError(error);
    }
}

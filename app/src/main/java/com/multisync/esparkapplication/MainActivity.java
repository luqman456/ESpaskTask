package com.multisync.esparkapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.multisync.esparkapplication.adapter.AdapterDeviceList;
import com.multisync.esparkapplication.base.BindingBaseActivity;
import com.multisync.esparkapplication.databinding.ActivityMainBinding;
import com.multisync.esparkapplication.helper.DisplayLog;
import com.multisync.esparkapplication.interfaces.ErrorView;
import com.multisync.esparkapplication.interfaces.OnClick;
import com.multisync.esparkapplication.mvvm.DeviceListViewModel;
import com.multisync.esparkapplication.pojo.Devices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BindingBaseActivity<ActivityMainBinding> implements ErrorView, OnClick {

    private List<Devices> devicesList;
    private AdapterDeviceList adapterDeviceList;
    private final int request_Code = 001;
    private int position;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Device List");
        DeviceListViewModel deviceListViewModel = new ViewModelProvider(this).get(DeviceListViewModel.class);
        deviceListViewModel.setErrorView(this);
        initializeRecycleView();


        showProgressDialog();
        deviceListViewModel.callDeviceListApi();

        deviceListViewModel.getDeviceList().observe(this, listBaseResponse -> {
            dismissProgressDialog();
            DisplayLog.showLog("TAG", " getMedicationList " + listBaseResponse);
            if (listBaseResponse.getData() != null) {
                DisplayLog.showLog("TAG", " ListSize " + listBaseResponse.getData().size());
                if (!devicesList.isEmpty()) {
                    devicesList.clear();
                }
                devicesList.addAll(listBaseResponse.getData());
                adapterDeviceList.notifyDataSetChanged();
            }
        });


    }

    private void initializeRecycleView() {
        devicesList = new ArrayList<>();
        dataBinding.deviceRecycleView.setHasFixedSize(true);
        dataBinding.deviceRecycleView.setItemAnimator(new DefaultItemAnimator());
        dataBinding.deviceRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterDeviceList = new AdapterDeviceList(devicesList);
        dataBinding.deviceRecycleView.setAdapter(adapterDeviceList);
        dataBinding.deviceRecycleView.setItemAnimator(null);
        adapterDeviceList.setOnClick(this);


    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onError(String error) {
        dismissProgressDialog();
    }

    @Override
    public void onClick(Devices devices) {
        Intent intent = new Intent(this, DeviceDetailsActivity.class);
        intent.putExtra("device_object", devices);
        intent.putExtra("only_view", "only_view");
        someActivityResultLauncher.launch(intent);
    }

    @Override
    public void onEdit(int position, Devices devices) {
        this.position = position;
        Intent intent = new Intent(this, DeviceDetailsActivity.class);
        intent.putExtra("device_object", devices);
        intent.putExtra("only_view", "edit");
        someActivityResultLauncher.launch(intent);
    }

    @Override
    public void onLongClick(int position, Devices devices) {
        this.position = position;
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    Toast.makeText(mContext, "Data Received", Toast.LENGTH_SHORT).show();

                }else{
                    Intent data = result.getData();
                    Toast.makeText(mContext, "Data Not Received"+result.getData().getStringExtra("name")+" "+data.getData(), Toast.LENGTH_SHORT).show();
                }
            });
}
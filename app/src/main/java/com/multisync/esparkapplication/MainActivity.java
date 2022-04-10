package com.multisync.esparkapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.multisync.esparkapplication.adapter.AdapterDeviceList;
import com.multisync.esparkapplication.base.BindingBaseActivity;
import com.multisync.esparkapplication.databinding.ActivityMainBinding;
import com.multisync.esparkapplication.helper.Constant;
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
    private int position;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Device List");
        DeviceListViewModel deviceListViewModel = new ViewModelProvider(this).get(DeviceListViewModel.class);
        deviceListViewModel.setErrorView(this);
        initializeRecycleView();

        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable myDrawable = getResources().getDrawable(R.drawable.vera_edge_big);
        dataBinding.userIv.setImageDrawable(myDrawable);


        showProgressDialog();
        deviceListViewModel.callDeviceListApi();

        deviceListViewModel.getDeviceList().observe(this, listBaseResponse -> {
            dismissProgressDialog();
            if (listBaseResponse.getData() != null) {
                dataBinding.dataNotExistTxt.setVisibility(View.GONE);
                dataBinding.deviceRecycleView.setVisibility(View.VISIBLE);
                devicesList.addAll(listBaseResponse.getData());
                adapterDeviceList.notifyDataSetChanged();
            }else{
                dataBinding.dataNotExistTxt.setVisibility(View.VISIBLE);
                dataBinding.deviceRecycleView.setVisibility(View.GONE);
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
        intent.putExtra(Constant.DEVICE_OBJECT, devices);
        intent.putExtra(Constant.VIEW, Constant.ONLY_VIEW);
        someActivityResultLauncher.launch(intent);
    }

    @Override
    public void onEdit(int position, Devices devices) {
        this.position = position;
        Intent intent = new Intent(this, DeviceDetailsActivity.class);
        intent.putExtra(Constant.DEVICE_OBJECT, devices);
        intent.putExtra(Constant.VIEW, Constant.EDIT);
        someActivityResultLauncher.launch(intent);
    }

    @Override
    public void onLongClick(int position, Devices devices) {
        this.position = position;
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Delete Action!");
        alertDialog.setMessage("Are you sure you want to delete device from list");
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete",
                (dialog, which) -> {
                    dialog.dismiss();
                    adapterDeviceList.deleteItem(position);
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();

        alertDialog.show();


    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    if (data.getStringExtra(Constant.VIEW).equals(Constant.EDIT)) {
                        Devices devices = (Devices) data.getSerializableExtra(Constant.DEVICE_OBJECT);
                        adapterDeviceList.updateItem(position, devices);
                        Toast.makeText(mContext, "Data Update", Toast.LENGTH_SHORT).show();
                    }

                }
            });
}
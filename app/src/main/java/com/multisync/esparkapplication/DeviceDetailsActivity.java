package com.multisync.esparkapplication;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.multisync.esparkapplication.base.BindingBaseActivity;
import com.multisync.esparkapplication.base.MyApplication;
import com.multisync.esparkapplication.databinding.ActivityDeviceDetailsBinding;
import com.multisync.esparkapplication.helper.Constant;
import com.multisync.esparkapplication.pojo.Devices;

public class DeviceDetailsActivity extends BindingBaseActivity<ActivityDeviceDetailsBinding> {

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Device Details Activity");

        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable myDrawable = getResources().getDrawable(R.drawable.vera_edge_big);
        dataBinding.userIv.setImageDrawable(myDrawable);

        Intent intent = getIntent();
        Devices devices = (Devices) intent.getSerializableExtra(Constant.DEVICE_OBJECT);
        dataBinding.setDeviceDetails(devices);
        dataBinding.deviceTitleTxt.setText(devices.getFirmware());
        if (intent.getStringExtra(Constant.VIEW).equals(Constant.EDIT)) {
            dataBinding.cancelBtn.setVisibility(View.VISIBLE);
            dataBinding.saveBtn.setVisibility(View.VISIBLE);
        } else {
            dataBinding.cancelBtn.setVisibility(View.GONE);
            dataBinding.saveBtn.setVisibility(View.GONE);
        }

        if (devices.getPlatform() != null) {
            dataBinding.deviceDetailIv.setImageDrawable(MyApplication.getInstance()
                    .getResources().getDrawable(R.drawable.vera_edge_big));
        } else {
            setImage(dataBinding.deviceDetailIv, devices.getPlatform());
        }

        dataBinding.saveBtn.setOnClickListener(view -> {
            if (dataBinding.deviceTitleTxt.getText().toString().isEmpty() || dataBinding.deviceTitleTxt.getText().toString().length() < 1) {
                Toast.makeText(mContext, "Field Required", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent1 = new Intent();
                intent1.putExtra(Constant.VIEW, Constant.EDIT);
                devices.setFirmware((dataBinding.deviceTitleTxt.getText().toString()));
                intent1.putExtra(Constant.DEVICE_OBJECT, devices);
                setResult(Activity.RESULT_OK, intent1);
                finish();
            }
        });

        dataBinding.cancelBtn.setOnClickListener(view -> cancelIntent());

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setImage(ImageView deviceIv, String platform) {

        switch (platform) {
            case "Sercomm G450":
                deviceIv.setImageDrawable(MyApplication.getInstance()
                        .getResources().getDrawable(R.drawable.vera_plus_big));
                break;
            case "Sercomm G550":
                deviceIv.setImageDrawable(MyApplication.getInstance()
                        .getResources().getDrawable(R.drawable.vera_secure_big));
                break;
            case "MiCasaVerde VeraLite":
            case "Sercomm NA900":
            case "Sercomm NA301":
            case "Sercomm NA930":
                deviceIv.setImageDrawable(MyApplication.getInstance()
                        .getResources().getDrawable(R.drawable.vera_edge_big));
                break;
        }
    }

    private void cancelIntent() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_device_details;
    }

    @Override
    public void onBackPressed() {
        cancelIntent();
    }
}
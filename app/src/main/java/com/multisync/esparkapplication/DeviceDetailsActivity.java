package com.multisync.esparkapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.multisync.esparkapplication.base.BindingBaseActivity;
import com.multisync.esparkapplication.databinding.ActivityDeviceDetailsBinding;
import com.multisync.esparkapplication.helper.Constant;
import com.multisync.esparkapplication.pojo.Devices;

public class DeviceDetailsActivity extends BindingBaseActivity<ActivityDeviceDetailsBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void cancelIntent(){
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
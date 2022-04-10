package com.multisync.esparkapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.multisync.esparkapplication.base.BindingBaseActivity;
import com.multisync.esparkapplication.databinding.ActivityDeviceDetailsBinding;
import com.multisync.esparkapplication.pojo.Devices;

public class DeviceDetailsActivity extends BindingBaseActivity<ActivityDeviceDetailsBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Devices devices = (Devices) intent.getSerializableExtra("device_object");
        dataBinding.setDeviceDetails(devices);
        if(intent.getStringExtra("only_view").equals("edit")){
            dataBinding.cancelBtn.setVisibility(View.VISIBLE);
            dataBinding.saveBtn.setVisibility(View.VISIBLE);
        }else{
            dataBinding.cancelBtn.setVisibility(View.GONE);
            dataBinding.saveBtn.setVisibility(View.GONE);
        }

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_device_details;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("name", "naammaa");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
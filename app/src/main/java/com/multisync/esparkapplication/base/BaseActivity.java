package com.multisync.esparkapplication.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.multisync.esparkapplication.helper.CustomProgressDialog;
import com.multisync.esparkapplication.sharedPreferences.SharedHelper;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customProgressDialog = new CustomProgressDialog(mContext);

    }

    @LayoutRes
    protected abstract int getLayoutRes();


    public void showProgressDialog() {
        customProgressDialog.showProgress();
    }

    public void showProgressDialogWithCustomText(String msg) {
        customProgressDialog.showProgressWithCustomText(msg);
    }

    public void dismissProgressDialog() {
        customProgressDialog.dismiss();
    }


    public void logOut() {
        SharedHelper.deleteAllSharedPrefs(mContext);
    }
}

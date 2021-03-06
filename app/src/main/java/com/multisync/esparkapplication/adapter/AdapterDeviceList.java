package com.multisync.esparkapplication.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.multisync.esparkapplication.R;
import com.multisync.esparkapplication.base.MyApplication;
import com.multisync.esparkapplication.databinding.DeviceListBinding;
import com.multisync.esparkapplication.interfaces.OnClick;
import com.multisync.esparkapplication.pojo.Devices;

import org.jetbrains.annotations.NotNull;


import java.util.List;

public class AdapterDeviceList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Devices> deviceList;
    private OnClick onClick;

    public AdapterDeviceList(List<Devices> patientList) {
        this.deviceList = patientList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        DeviceListBinding deviceListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.device_list, parent, false);
        return new DeviceViewHolder(deviceListBinding);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        DeviceViewHolder viewHolder = (DeviceViewHolder) holder;
        viewHolder.deviceListBinding.setDevice(deviceList.get(position));

        if (deviceList.get(position).getPlatform() != null) {
            viewHolder.deviceListBinding.deviceIv.setImageDrawable(MyApplication.getInstance()
                    .getResources().getDrawable(R.drawable.vera_edge_big));
        } else {
            setImage(viewHolder.deviceListBinding.deviceIv, deviceList.get(position).getPlatform());
        }

        viewHolder.deviceListBinding.nextIv.setOnClickListener(view -> {
            onClick.onClick(deviceList.get(position));
        });

        viewHolder.deviceListBinding.editIv.setOnClickListener(view -> {
            onClick.onEdit(position, deviceList.get(position));
        });

        viewHolder.deviceListBinding.cardViewParent.setOnLongClickListener(view -> {
            onClick.onLongClick(position, deviceList.get(position));
            return true;
        });

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


    public void updateItem(int pos, Devices devices) {
        this.deviceList.set(pos, devices);
        notifyItemChanged(pos);
    }

    public void deleteItem(int pos) {
        this.deviceList.remove(pos);
        notifyItemChanged(pos);
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }


    private class DeviceViewHolder extends RecyclerView.ViewHolder {
        DeviceListBinding deviceListBinding;

        public DeviceViewHolder(DeviceListBinding deviceListBinding) {
            super(deviceListBinding.getRoot());
            this.deviceListBinding = deviceListBinding;
        }
    }
}

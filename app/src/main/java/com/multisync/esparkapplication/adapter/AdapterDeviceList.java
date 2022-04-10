package com.multisync.esparkapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.multisync.esparkapplication.R;
import com.multisync.esparkapplication.databinding.DeviceListBinding;
import com.multisync.esparkapplication.interfaces.OnClick;
import com.multisync.esparkapplication.pojo.Devices;

import org.jetbrains.annotations.NotNull;


import java.util.List;

public class AdapterDeviceList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Devices> deviceList;
    private String TAG = "AdapterPatientList";
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

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        DeviceViewHolder viewHolder = (DeviceViewHolder) holder;
        viewHolder.deviceListBinding.setDevice(deviceList.get(position));

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

    public void updateItem(int pos, Devices devices) {
        this.deviceList.set(pos, devices);
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

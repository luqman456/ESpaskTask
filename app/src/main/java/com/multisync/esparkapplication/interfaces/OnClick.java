package com.multisync.esparkapplication.interfaces;

import com.multisync.esparkapplication.pojo.Devices;

public interface OnClick {
    void onClick(Devices devices);
    void onEdit(int position,Devices devices);
    void onLongClick(int position,Devices devices);
}

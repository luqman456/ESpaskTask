package com.multisync.esparkapplication.pojo;

import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.multisync.esparkapplication.BR;

import java.io.Serializable;

public class Devices implements Serializable {

    @SerializedName("PK_Device")
    private int PK_Device;
    @SerializedName("MacAddress")
    private String MacAddress;
    @SerializedName("PK_DeviceType")
    private int PK_DeviceType;
    @SerializedName("PK_DeviceSubType")
    private int PK_DeviceSubType;
    @SerializedName("Firmware")
    private String Firmware;
    @SerializedName("Server_Device")
    private String Server_Device;
    @SerializedName("Server_Event")
    private String Server_Event;
    @SerializedName("Server_Account")
    private String Server_Account;
    @SerializedName("InternalIP")
    private String InternalIP;
    @SerializedName("LastAliveReported")
    private String LastAliveReported;
    @SerializedName("Platform")
    private String Platform;
    @SerializedName("PK_Account")
    private String PK_Account;

    public int getPK_Device() {
        return PK_Device;
    }

    public void setPK_Device(int PK_Device) {
        this.PK_Device = PK_Device;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public int getPK_DeviceType() {
        return PK_DeviceType;
    }

    public void setPK_DeviceType(int PK_DeviceType) {
        this.PK_DeviceType = PK_DeviceType;
    }

    public int getPK_DeviceSubType() {
        return PK_DeviceSubType;
    }

    public void setPK_DeviceSubType(int PK_DeviceSubType) {
        this.PK_DeviceSubType = PK_DeviceSubType;
    }

    public String getFirmware() {
        return Firmware;
    }

    public void setFirmware(String firmware) {
        Firmware = firmware;
    }

    public String getServer_Device() {
        return Server_Device;
    }

    public void setServer_Device(String server_Device) {
        Server_Device = server_Device;
    }

    public String getServer_Event() {
        return Server_Event;
    }

    public void setServer_Event(String server_Event) {
        Server_Event = server_Event;
    }

    public String getServer_Account() {
        return Server_Account;
    }

    public void setServer_Account(String server_Account) {
        Server_Account = server_Account;
    }

    public String getInternalIP() {
        return InternalIP;
    }

    public void setInternalIP(String internalIP) {
        InternalIP = internalIP;
    }

    public String getLastAliveReported() {
        return LastAliveReported;
    }

    public void setLastAliveReported(String lastAliveReported) {
        LastAliveReported = lastAliveReported;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getPK_Account() {
        return PK_Account;
    }

    public void setPK_Account(String PK_Account) {
        this.PK_Account = PK_Account;
    }
}

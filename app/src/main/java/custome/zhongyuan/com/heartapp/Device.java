package custome.zhongyuan.com.heartapp;

import android.bluetooth.BluetoothDevice;

/**
 * Created by yangxiaoguang on 2017/6/19.
 */

public class Device {

    private String deviceName;
    private String deviceVer;
    private String deviceMAC;
    private int deviceSignalVales;
    private int deviceVerID;
    private String deviceID;
    private BluetoothDevice bluetoothDevice;

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceVer() {
        return deviceVer;
    }

    public void setDeviceVer(String deviceVer) {
        this.deviceVer = deviceVer;
    }

    public String getDeviceMAC() {
        return deviceMAC;
    }

    public void setDeviceMAC(String deviceMAC) {
        this.deviceMAC = deviceMAC;
    }

    public int getDeviceSignalVales() {
        return deviceSignalVales;
    }

    public void setDeviceSignalVales(int deviceSignalVales) {
        this.deviceSignalVales = deviceSignalVales;
    }

    public int getDeviceVerID() {
        return deviceVerID;
    }

    public void setDeviceVerID(int deviceVerID) {
        this.deviceVerID = deviceVerID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}

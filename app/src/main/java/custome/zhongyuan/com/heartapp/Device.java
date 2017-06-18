package custome.zhongyuan.com.heartapp;

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

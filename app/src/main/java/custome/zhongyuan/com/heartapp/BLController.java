package custome.zhongyuan.com.heartapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothProfile;
import android.os.Handler;
import android.util.Log;

import custome.zhongyuan.com.heartapp.Common.LibConfig;

/**
 * Created by yangxiaoguang on 2017/7/30.
 */

public class BLController {

    private static BLController blController;
    private BluetoothDevice bluetoothDevice;
    private BluetoothGatt bluetoothGatt;

    private BLCallBack blCallBack;
    private String macAddr;

    public void setBlCallBack(BLCallBack blCallBack) {
        this.blCallBack = blCallBack;
    }

    public static BLController getBlController() {
        if (blController == null) {
            blController = new BLController();
        }
        return blController;
    }


    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    /**
     * 连接指定的mac 地址
     * @param mac
     */
    public void connectDeviceForMac(String mac)
    {
        BluetoothDevice localBluetoothDevice = BluetoothAdapter.
                getDefaultAdapter().getRemoteDevice(mac);
        setBluetoothDevice(localBluetoothDevice);
        connectDevie();
    }

    public void connectDevie() {

        bluetoothGatt = bluetoothDevice.connectGatt(App.getApp(), true, bluetoothGattCallback);
        bluetoothGatt.connect();

    }


    public void disConnectDevice()
    {
        bluetoothGatt.disconnect();
        bluetoothDevice = null;
        bluetoothGatt = null;
        if (blCallBack != null)
            blCallBack.OnDisConenectDeivce();
        blCallBack = null;
    }

    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.i("连接服务，开始扫描蓝牙中得服务", "----》");
                if (blCallBack != null) {
                    LibConfig.setKeyShareVar("mac",bluetoothDevice.getAddress());
                    blCallBack.OnConnectedDevice();
                }
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
        }
    };

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public interface BLCallBack {

        void OnConnectedDevice();
        void OnDisConenectDeivce();
    }

}

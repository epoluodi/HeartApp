package custome.zhongyuan.com.heartapp.Activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import custome.zhongyuan.com.heartapp.BLController;
import custome.zhongyuan.com.heartapp.Common.Common;
import custome.zhongyuan.com.heartapp.Device;
import custome.zhongyuan.com.heartapp.R;

public class QueryDeviceActivity extends AppCompatActivity {


    public static final  int REQUESTCODE = 10;
    private Timer timer;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;


    private ImageView btnreturn;
    private ListView listView;
    private List<Device> mapList;
    private List<String> deviceName;


    private ImageView btnrefresh;
    private Myadpter myadpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_device);

        btnreturn = (ImageView) findViewById(R.id.btnreturn);
        listView = (ListView) findViewById(R.id.list);
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnrefresh = (ImageView) findViewById(R.id.btnquery);
        btnrefresh.setOnClickListener(onClickListenerrefresh);
        mapList = new ArrayList<>();
        deviceName = new ArrayList<>();
        myadpter = new Myadpter();
        listView.setAdapter(myadpter);
        refreshDevice();

        listView.setOnItemClickListener(onItemClickListener);
    }


    AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Device device =mapList.get((int)l);
            //连接设备
            Intent intent=new Intent();
            intent.putExtra("mac",device.getDeviceMAC());
            BLController.getBlController().setBluetoothDevice(device.getBluetoothDevice());
            setResult(1,intent);
            finish();

        }
    };

    /**
     * 搜索完成回调主线程
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Common.ShowPopWindow(listView, getLayoutInflater(), "正在搜索...");
                    break;
                case 0:
                    bluetoothAdapter.stopLeScan(leScanCallback);
                    Common.CLosePopwindow();
                    break;
            }

        }
    };


    /**
     * 刷新
     */
    View.OnClickListener onClickListenerrefresh = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mapList.clear();
            deviceName.clear();
            myadpter.notifyDataSetChanged();
            refreshDevice();

        }
    };


    /**
     * 刷新设备
     */
    private void refreshDevice() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(600);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);

                read();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0);
                        timer.cancel();
                        timer = null;
                    }
                }, 5000, 100);
            }
        }).start();
    }


    /**
     * 读取设备
     */
    private void read() {
        bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        bluetoothAdapter.startLeScan(leScanCallback);
    }

    BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            if (device.getName() == null)
                return;
            Log.i("搜索 设备名称", device.getName());//84:DD:20:ED:1F:48
            Log.i("搜索 信号", device.getAddress());
            Log.i("搜索 信号", String.valueOf(rssi));


            if (!deviceName.contains(device.getName())) {

                Device bldevice = new Device();
                bldevice.setBluetoothDevice(device);
                bldevice.setDeviceName(device.getName());
                bldevice.setDeviceMAC(device.getAddress());
                bldevice.setDeviceSignalVales(rssi);
                deviceName.add(device.getName());
                mapList.add(bldevice);
                myadpter.notifyDataSetChanged();
            }


        }


    };


    private class Myadpter extends BaseAdapter {
        private TextView name, macaddr, signal;

        @Override
        public int getCount() {
            return mapList.size();
        }

        @Override
        public Object getItem(int i) {
            return mapList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            Device device = mapList.get(i);
            view = LayoutInflater.from(QueryDeviceActivity.this).inflate(
                    R.layout.list_devieinfo, null);
            name = (TextView) view.findViewById(R.id.name);
            macaddr = (TextView) view.findViewById(R.id.macaddr);
            signal = (TextView) view.findViewById(R.id.signal);

            name.setText(device.getDeviceName());
            macaddr.setText("设备地址:" + device.getDeviceMAC());
            signal.setText("信号:"+String.valueOf(device.getDeviceSignalVales()));

            return view;
        }
    }

}

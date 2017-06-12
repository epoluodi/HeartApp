package custome.zhongyuan.com.heartapp.Activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import custome.zhongyuan.com.heartapp.Common;
import custome.zhongyuan.com.heartapp.R;

public class QueryDeviceActivity extends AppCompatActivity {


    private ListView listView;
    private Timer timer;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private List<Map<String,String>> mapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_device);
        listView = (ListView)findViewById(R.id.list);

        mapList=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(600);
                }catch (Exception e)
                {e.printStackTrace();}
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
                },5000,100);
            }
        }).start();

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    Common.ShowPopWindow(listView,getLayoutInflater(),"正在搜索...");
                    break;
                case 0:
                    bluetoothAdapter.stopLeScan(leScanCallback);
                    Common.CLosePopwindow();
                    break;
            }

        }
    };

    private void read() {
        bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        bluetoothAdapter.startLeScan(leScanCallback);
    }

    BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            Log.i("搜索 设备名称", device.getName());//84:DD:20:ED:1F:48
            Log.i("搜索 信号", device.getAddress());
            Log.i("搜索 信号", String.valueOf(rssi));

////            HC-08
//            if (device.getName().equals(blname)) {
//
//                Message message = handler.obtainMessage();
//                message.obj = "设备名称:" + device.getName() + "\n" +
//                        "设备地址:" + device.getAddress() + "\n";
//
//                handler.sendMessage(message);
//
//                bluetoothGatt = device.connectGatt(MainActivity.this, false, bluetoothGattCallback);
//                bluetoothGatt.connect();
//                bluetoothAdapter.stopLeScan(leScanCallback);
//            }

        }


    };

}

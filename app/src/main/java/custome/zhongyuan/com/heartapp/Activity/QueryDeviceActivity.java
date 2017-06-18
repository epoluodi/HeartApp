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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import custome.zhongyuan.com.heartapp.Common;
import custome.zhongyuan.com.heartapp.Device;
import custome.zhongyuan.com.heartapp.R;

public class QueryDeviceActivity extends AppCompatActivity {



    private Timer timer;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;

    private Map<String,Device> mapList;
    private ImageView btnreturn;
    private ListView listView;


    private ImageView btnrefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_device);

        btnreturn = (ImageView)findViewById(R.id.btnreturn);
        listView = (ListView)findViewById(R.id.list);
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnrefresh = (ImageView)findViewById(R.id.btnquery);
        btnrefresh.setOnClickListener(onClickListenerrefresh);
        mapList=new HashMap<>();
        refreshDevice();

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


    View.OnClickListener onClickListenerrefresh = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            refreshDevice();

        }
    };


    private void refreshDevice()
    {
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


            if (!mapList.containsKey(device.getName()))
            {
                Device bldevice=new Device();
                bldevice.setDeviceName(device.getName());
                bldevice.setDeviceMAC(device.getAddress());
                bldevice.setDeviceSignalVales(rssi);
                mapList.put(device.getName(),bldevice);
            }



        }


    };



    private class Myadpter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return mapList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {



            return view;
        }
    }

}

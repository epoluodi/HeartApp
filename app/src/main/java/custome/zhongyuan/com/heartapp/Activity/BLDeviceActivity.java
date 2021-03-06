package custome.zhongyuan.com.heartapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import custome.zhongyuan.com.heartapp.App;
import custome.zhongyuan.com.heartapp.BLController;
import custome.zhongyuan.com.heartapp.Common.Common;
import custome.zhongyuan.com.heartapp.Common.LibConfig;
import custome.zhongyuan.com.heartapp.MainActivity;
import custome.zhongyuan.com.heartapp.R;

public class BLDeviceActivity extends AppCompatActivity {
    private ImageView btnreturn;
    private ImageView btnquery;
    private Handler handler;
    private TextView dname,serial_number,mem,power,ver;
    private RelativeLayout btndisconnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bldevice);
        btnreturn = (ImageView)findViewById(R.id.btnreturn);

        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnquery = (ImageView)findViewById(R.id.btnquery);
        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BLDeviceActivity.this,QueryDeviceActivity.class);
                startActivityForResult(intent,QueryDeviceActivity.REQUESTCODE);
            }
        });

        dname = (TextView)findViewById(R.id.dname);
        serial_number = (TextView)findViewById(R.id.serial_number);
        mem = (TextView)findViewById(R.id.mem);
        power = (TextView)findViewById(R.id.power);
        ver = (TextView)findViewById(R.id.ver);


        btndisconnect = (RelativeLayout)findViewById(R.id.menu_disconnect);
        btndisconnect.setOnClickListener(onClickListenerdisconnect);

        BLController.getBlController().setBlCallBack(blCallBack);
        handler = new Handler();
        initDeviceInfo();
    }



    View.OnClickListener onClickListenerdisconnect = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dname.setText("设备名称: - ");
            serial_number.setText("设备序列号:" + "-");

            power.setText("-");//电量
            mem.setText("-"); //缓存信息
            ver.setText("-");//版本号

            BLController.getBlController().disConnectDevice();
            LibConfig.setKeyShareVar("mac", "-");
            Toast.makeText(BLDeviceActivity.this,"已经断开连接",Toast.LENGTH_SHORT).show();
        }
    };


    private void initDeviceInfo()
    {
        if (BLController.getBlController().getBluetoothDevice()!=null) {
            dname.setText("设备名称:" +
                    BLController.getBlController().getBluetoothDevice().getName());
            serial_number.setText("设备序列号:" + "-");

            power.setText("0%");//电量
            mem.setText("无"); //缓存信息
            ver.setText("V1.0");//版本号
        }
    }

    BLController.BLCallBack blCallBack=new BLController.BLCallBack() {
        @Override
        public void OnConnectedDevice() {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Common.CLosePopwindow();
                    initDeviceInfo();


                }
            });

        }

        @Override
        public void OnDisConenectDeivce() {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BLController.getBlController().setBlCallBack(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QueryDeviceActivity.REQUESTCODE)
        {
            if (resultCode ==1)
            {
                //开始连接
                Common.ShowPopWindow(btnquery, getLayoutInflater(), "正在连接...");

                BLController.getBlController().connectDevie();
            }

        }
    }
}

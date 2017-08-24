package custome.zhongyuan.com.heartapp;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import custome.zhongyuan.com.heartapp.Activity.BLDeviceActivity;
import custome.zhongyuan.com.heartapp.Activity.QueryDeviceActivity;
import custome.zhongyuan.com.heartapp.Common.Common;
import custome.zhongyuan.com.heartapp.Common.LibConfig;
import custome.zhongyuan.com.heartapp.FrameController.FragmentMangerX;
import custome.zhongyuan.com.heartapp.FrameController.FragmentName;

public class MainActivity extends AppCompatActivity {

    private UserInfoFragment userInfofragment;//用户个人信息
    private HomeFragment homeFragment;
    private ReportFragment reportFragment;
    private Fragment fragmentnow;
    private BottomNavigationView navigation;
    private Handler handler;
    private Timer timer;


    public static FragmentMangerX fragmentMangerX; //fragment框架

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.herthome:
                    fragmentMangerX.FragmentHide(fragmentnow);
                    fragmentMangerX.ShowFragment(homeFragment);
                    fragmentnow = homeFragment;
                    return true;
                case R.id.report:
                    fragmentMangerX.FragmentHide(fragmentnow);
                    fragmentMangerX.ShowFragment(reportFragment);
                    fragmentnow = reportFragment;
                    return true;
                case R.id.userinfo:
                    fragmentMangerX.FragmentHide(fragmentnow);
                    fragmentMangerX.ShowFragment(userInfofragment);
                    fragmentnow = userInfofragment;
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        navigation.setSelectedItemId(0);


        fragmentMangerX = new FragmentMangerX(getFragmentManager(), R.id.container);
        userInfofragment = new UserInfoFragment();
        reportFragment= new ReportFragment();
        homeFragment=new HomeFragment();

        ((FragmentName) userInfofragment).SetFragmentName("userInfofragment");
        fragmentMangerX.AddFragment(userInfofragment, "userInfofragment");
        fragmentMangerX.FragmentHide("userInfofragment");


        ((FragmentName) reportFragment).SetFragmentName("reportFragment");
        fragmentMangerX.AddFragment(reportFragment, "reportFragment");
        fragmentMangerX.FragmentHide("reportFragment");

        ((FragmentName) homeFragment).SetFragmentName("homeFragment");
        fragmentMangerX.AddFragment(homeFragment, "homeFragment");
        fragmentMangerX.ShowFragment("homeFragment");

        fragmentnow = homeFragment;


        //判断蓝牙是否打开
        if (!Common.isBluetoothEnable())
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("请打开蓝牙设置");
            builder.setPositiveButton("打开蓝牙", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    openSetting();
                    return;
                }
            });
            builder.setNeutralButton("取消",null);
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

        }



    }

    @Override
    protected void onStart() {
        super.onStart();

        if (BLController.getBlController().getMacAddr().equals("-"))
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("没有连接设备，现在开始连接");
            builder.setPositiveButton("开始连接", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Toast.makeText(MainActivity.this,"请确保设备属于打开状态",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,QueryDeviceActivity.class);
                    startActivityForResult(intent,QueryDeviceActivity.REQUESTCODE);
                    return;
                }
            });
            builder.setNeutralButton("取消",null);
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"准备开始连接设备",Toast.LENGTH_SHORT).show();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handlerdisconnect.sendEmptyMessage(0);
                    timer.cancel();
                    timer = null;
                }
            }, 5000, 100);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Common.ShowPopWindow(navigation, getLayoutInflater(), "正在连接...");
                    BLController.getBlController().setBlCallBack(blCallBack);
                    BLController.getBlController().connectDeviceForMac(
                            LibConfig.getKeyShareVarForString("mac"));
                }
            },1000);

        }
    }


    Handler handlerdisconnect = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 0:
                    BLController.getBlController().disConnectDevice();
                    Common.CLosePopwindow();
                    break;
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QueryDeviceActivity.REQUESTCODE)
        {
            if (resultCode ==1)
            {
                //开始连接
                Common.ShowPopWindow(navigation, getLayoutInflater(), "正在连接...");
                BLController.getBlController().setBlCallBack(blCallBack);
                BLController.getBlController().connectDevie();
            }

        }
    }


    BLController.BLCallBack blCallBack=new BLController.BLCallBack() {
        @Override
        public void OnConnectedDevice() {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Common.CLosePopwindow();
                    Toast.makeText(App.getApp(),"设备连接成功",Toast.LENGTH_SHORT).show();
                    if (timer !=null) {
                        timer.cancel();
                        timer = null;
                    }

                }
            });

        }

        @Override
        public void OnDisConenectDeivce() {
            Toast.makeText(App.getApp(),"设备连接断开",Toast.LENGTH_SHORT).show();
        }
    };


    private  void openSetting(){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            startActivity(intent);
        } catch(ActivityNotFoundException ex){
            ex.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

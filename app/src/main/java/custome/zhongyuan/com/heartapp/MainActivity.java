package custome.zhongyuan.com.heartapp;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import custome.zhongyuan.com.heartapp.FrameController.FragmentMangerX;
import custome.zhongyuan.com.heartapp.FrameController.FragmentName;

public class MainActivity extends AppCompatActivity {

    private UserInfoFragment userInfofragment;//用户个人信息
    private HomeFragment homeFragment;
    private ReportFragment reportFragment;
    private Fragment fragmentnow;

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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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

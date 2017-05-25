package custome.zhongyuan.com.heartapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

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





    }

}

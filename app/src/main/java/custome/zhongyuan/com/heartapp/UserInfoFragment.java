package custome.zhongyuan.com.heartapp;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import custome.zhongyuan.com.heartapp.Activity.BLDeviceActivity;
import custome.zhongyuan.com.heartapp.Activity.GuardianActivity;
import custome.zhongyuan.com.heartapp.Activity.SelfInfoActivity;
import custome.zhongyuan.com.heartapp.Activity.SettingActivity;
import custome.zhongyuan.com.heartapp.CustomView.STImageView;
import custome.zhongyuan.com.heartapp.FrameController.FragmentName;


/**
 * Created by Administrator on 14-11-30.
 */
public class UserInfoFragment extends Fragment implements FragmentName {

    private String Fragment_Name = "";

    private STImageView circleImg;


    private RelativeLayout menu_person, menu2, menu3, menu4, menu5, menu6;
    private TextView title, t1, t2, t3, t4, t5;


    private TextView txtname, loginname;
//    private AlertSheet alertSheet;




    @Override
    public void SetFragmentName(String name) {
        Fragment_Name = name;
    }

    @Override
    public String GetFragmentName() {
        return Fragment_Name;
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_userinfo, container, false);

        menu2 = (RelativeLayout)rootView.findViewById(R.id.menu2);
        menu3 = (RelativeLayout)rootView.findViewById(R.id.menu3);
        menu4 = (RelativeLayout)rootView.findViewById(R.id.menu4);
        menu5 = (RelativeLayout)rootView.findViewById(R.id.menu5);
        menu2.setOnClickListener(onClickListenermenu);
        menu3.setOnClickListener(onClickListenermenu);
        menu4.setOnClickListener(onClickListenermenu);
        menu5.setOnClickListener(onClickListenermenu);
        circleImg = (STImageView)rootView.findViewById(R.id.nickimg);
        circleImg.setmIsCircle(true);


        return rootView;
    }


    View.OnClickListener onClickListenermenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId())
            {
                case R.id.menu2:
                    intent=new Intent(getActivity(), SelfInfoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu3:
                    intent=new Intent(getActivity(), GuardianActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu4:
                    intent=new Intent(getActivity(), BLDeviceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu5:
                    intent=new Intent(getActivity(), SettingActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };






}

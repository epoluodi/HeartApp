package custome.zhongyuan.com.heartapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import custome.zhongyuan.com.heartapp.CustomView.STImageView;
import custome.zhongyuan.com.heartapp.FrameController.FragmentName;


/**
 * Created by Administrator on 14-11-30.
 */
public class ReportFragment extends Fragment implements FragmentName {

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
        View rootView = inflater.inflate(R.layout.fragment_report, container, false);


        return rootView;
    }






}

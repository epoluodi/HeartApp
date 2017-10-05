package custome.zhongyuan.com.heartapp.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import custome.zhongyuan.com.heartapp.Common.LibConfig;
import custome.zhongyuan.com.heartapp.R;

public class SettingActivity extends AppCompatActivity {

    private ImageView btnreturn;
    private Switch sw1,sw2,sw3,sw4,sw5;
    private RelativeLayout menu_common1,menu_common2;
    private TextView max,min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btnreturn = (ImageView)findViewById(R.id.btnreturn);

        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        menu_common1 = (RelativeLayout)findViewById(R.id.menu_common1);
        menu_common2 = (RelativeLayout)findViewById(R.id.menu_common2);
        menu_common1.setOnClickListener(onClickListenerMax);
        menu_common2.setOnClickListener(onClickListenerMin);
        max = (TextView)findViewById(R.id.max);
        min = (TextView)findViewById(R.id.min);
        max.setText(String.valueOf(LibConfig.getKeyShareVarForint("max")));
        min.setText(String.valueOf(LibConfig.getKeyShareVarForint("min")));

        sw1 = (Switch)findViewById(R.id.sw1);
        sw2 = (Switch)findViewById(R.id.sw2);
        sw3 = (Switch)findViewById(R.id.sw3);
        sw4 = (Switch)findViewById(R.id.sw4);
        sw5 = (Switch)findViewById(R.id.sw5);

        sw1.setChecked(LibConfig.getKeyShareVarForBoolean("sw1"));
        sw2.setChecked(LibConfig.getKeyShareVarForBoolean("sw2"));
        sw3.setChecked(LibConfig.getKeyShareVarForBoolean("sw3"));
        sw4.setChecked(LibConfig.getKeyShareVarForBoolean("sw4"));
        sw5.setChecked(LibConfig.getKeyShareVarForBoolean("sw5"));

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LibConfig.setKeyShareVar("sw1",b);
            }
        });
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LibConfig.setKeyShareVar("sw2",b);
            }
        });
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LibConfig.setKeyShareVar("sw3",b);
            }
        });
        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LibConfig.setKeyShareVar("sw4",b);
            }
        });
        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LibConfig.setKeyShareVar("sw5",b);
            }
        });
    }



    View.OnClickListener onClickListenerMax = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
            builder.setMessage("请输入最大心率");
            final EditText editText =new EditText(SettingActivity.this);
            builder.setView(editText);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LibConfig.setKeyShareVar("max",Integer.valueOf(editText.getText().toString()));
                    dialogInterface.dismiss();
                    max.setText(editText.getText().toString());
                    Toast.makeText(SettingActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNeutralButton("取消",null);

            builder.show();


        }
    };
    View.OnClickListener onClickListenerMin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
            builder.setMessage("请输入最小心率");
            final EditText editText =new EditText(SettingActivity.this);
            builder.setView(editText);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LibConfig.setKeyShareVar("min",Integer.valueOf(editText.getText().toString()));
                    dialogInterface.dismiss();
                    min.setText(editText.getText().toString());
                    Toast.makeText(SettingActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNeutralButton("取消",null);

            builder.show();


        }
    };


}

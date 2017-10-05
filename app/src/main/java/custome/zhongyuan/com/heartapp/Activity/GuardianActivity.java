package custome.zhongyuan.com.heartapp.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import custome.zhongyuan.com.heartapp.Common.LibConfig;
import custome.zhongyuan.com.heartapp.R;

public class GuardianActivity extends AppCompatActivity {
    private ImageView btnreturn;
    private RelativeLayout menu_nickname,menu_sex,menu_phone;
    private TextView nickname,sex,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian);
        btnreturn = (ImageView)findViewById(R.id.btnreturn);

        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        menu_nickname = (RelativeLayout)findViewById(R.id.menu_nickname);
        menu_sex = (RelativeLayout)findViewById(R.id.menu_sex);
        menu_phone = (RelativeLayout)findViewById(R.id.menu_phone);


        menu_nickname.setOnClickListener(onClickListenernickName);
        nickname = (TextView)findViewById(R.id.nickname);
        nickname.setText(LibConfig.getKeyShareVarForString("guardian_nickName"));


        menu_sex.setOnClickListener(onClickListenerSex);
        sex = (TextView)findViewById(R.id.sex);
        sex.setText(LibConfig.getKeyShareVarForString("guardian_sex"));

        menu_phone.setOnClickListener(onClickListenerPhone);
        phone = (TextView)findViewById(R.id.phone);
        phone.setText(LibConfig.getKeyShareVarForString("guardian_phone"));


    }

    View.OnClickListener onClickListenernickName = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GuardianActivity.this);
            builder.setMessage("请输入姓名");
            final EditText editText =new EditText(GuardianActivity.this);
            builder.setView(editText);

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LibConfig.setKeyShareVar("guardian_nickName",editText.getText().toString());
                    dialogInterface.dismiss();
                    nickname.setText(editText.getText().toString());
                    Toast.makeText(GuardianActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNeutralButton("取消",null);

            builder.show();


        }
    };

    View.OnClickListener onClickListenerSex = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GuardianActivity.this);
            builder.setMessage("请选择性别");
            builder.setPositiveButton("男", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LibConfig.setKeyShareVar("guardian_sex","男");
                    dialogInterface.dismiss();
                    sex.setText("男");
                    Toast.makeText(GuardianActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNeutralButton("女", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LibConfig.setKeyShareVar("guardian_sex","女");
                    dialogInterface.dismiss();
                    sex.setText("女");
                    Toast.makeText(GuardianActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();


        }
    };


    View.OnClickListener onClickListenerPhone = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GuardianActivity.this);
            builder.setMessage("请输入手机号");
            final EditText editText =new EditText(GuardianActivity.this);
            builder.setView(editText);
            editText.setInputType(InputType.TYPE_CLASS_PHONE);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LibConfig.setKeyShareVar("guardian_phone",editText.getText().toString());
                    dialogInterface.dismiss();
                    phone.setText(editText.getText().toString());
                    Toast.makeText(GuardianActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNeutralButton("取消",null);

            builder.show();


        }
    };

}

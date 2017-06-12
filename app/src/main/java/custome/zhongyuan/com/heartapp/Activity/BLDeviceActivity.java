package custome.zhongyuan.com.heartapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import custome.zhongyuan.com.heartapp.R;

public class BLDeviceActivity extends AppCompatActivity {

    private ImageView btnquery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bldevice);
        btnquery = (ImageView)findViewById(R.id.btnquery);
        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BLDeviceActivity.this,QueryDeviceActivity.class);
                startActivity(intent);
            }
        });
    }
}

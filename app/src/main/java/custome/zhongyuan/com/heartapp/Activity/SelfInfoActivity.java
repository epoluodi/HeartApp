package custome.zhongyuan.com.heartapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import custome.zhongyuan.com.heartapp.R;

public class SelfInfoActivity extends AppCompatActivity {

    private ImageView btnreturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_info);
        btnreturn = (ImageView)findViewById(R.id.btnreturn);

        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

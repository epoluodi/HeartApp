package custome.zhongyuan.com.heartapp;

import android.app.Application;

import custome.zhongyuan.com.heartapp.Common.LibConfig;

/**
 * Created by yangxiaoguang on 2017/7/30.
 */

public class App extends Application {
    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        BLController.getBlController();

        if (!LibConfig.getKeyShareVarForBoolean("IsFirst")) {
            LibConfig.setKeyShareVar("IsFirst",true);
            LibConfig.setKeyShareVar("mac","-");
            LibConfig.setKeyShareVar("nickName","-");
            LibConfig.setKeyShareVar("sex","男");
            LibConfig.setKeyShareVar("phone","-");
            LibConfig.setKeyShareVar("address","-");

            LibConfig.setKeyShareVar("guardian_nickName","-");
            LibConfig.setKeyShareVar("guardian_sex","男");
            LibConfig.setKeyShareVar("guardian_phone","-");

            LibConfig.setKeyShareVar("sw1",true);
            LibConfig.setKeyShareVar("sw2",true);
            LibConfig.setKeyShareVar("sw3",true);
            LibConfig.setKeyShareVar("sw4",true);
            LibConfig.setKeyShareVar("sw5",true);

            LibConfig.setKeyShareVar("max",90);
            LibConfig.setKeyShareVar("min",90);
        }

        BLController.getBlController().setMacAddr(LibConfig.getKeyShareVarForString("mac"));



    }


}

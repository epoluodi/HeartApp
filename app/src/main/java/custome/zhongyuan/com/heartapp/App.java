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
        }

        BLController.getBlController().setMacAddr(LibConfig.getKeyShareVarForString("mac"));



    }


}

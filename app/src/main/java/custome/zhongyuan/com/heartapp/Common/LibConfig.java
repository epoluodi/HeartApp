package custome.zhongyuan.com.heartapp.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Date;

import custome.zhongyuan.com.heartapp.App;

/**
 * Created by Stereo on 16/7/14.
 */
public class LibConfig {

    /**
     * 获取本地用户信息
     * @param key
     * @return
     */
    public static String getKeyShareVarForString(String key) {
        SharedPreferences sharedPreferences = App.getApp().getSharedPreferences("userinfo", Context.MODE_APPEND);
        return sharedPreferences.getString(key, "null");
    }


    public static Boolean getKeyShareVarForBoolean(String key)
    {
        SharedPreferences sharedPreferences =App.getApp().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static int getKeyShareVarForint(String key)
    {
        SharedPreferences sharedPreferences = App.getApp().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

    public static long getKeyShareVarForLong(String key)
    {
        SharedPreferences sharedPreferences = App.getApp().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, -1);
    }
    /**
     * 设置信息

     * @param key
     * @param value
     */
    public static void setKeyShareVar(String key,String value)
    {
        SharedPreferences sharedPreferences = App.getApp().getSharedPreferences("userinfo", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static void setKeyShareVar(String key,int value)
    {
        SharedPreferences sharedPreferences = App.getApp().getSharedPreferences("userinfo", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public static void setKeyShareVar(String key,boolean value)
    {
        SharedPreferences sharedPreferences = App.getApp().getSharedPreferences("userinfo", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    /**
     * 删除信息
     * @param key
     */
    public static void delKeyShareVar(String key)
    {
        SharedPreferences sharedPreferences = App.getApp().getSharedPreferences("userinfo", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }





}

package com.bdxw.impression;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * Name: AppLication
 * Author: 王兵洋的Computer
 * Comment: //TODO
 * Date: 2017-12-16 11:52
 */
public class AppLication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //配置QQ登陆
        PlatformConfig.setQQZone("1106492039", "r9rzq90OHuSDZSpo");
        //初始化SDK
        UMShareAPI.get(this);
        Config.DEBUG = true;
    }
}

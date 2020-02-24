package com.tencent.bugly.hotfix.easy;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;

import java.util.Locale;

/**
 * Demo Application示例.
 *
 * @author wenjiewu
 * @since 2017/1/3
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setStrictMode();
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁
        Beta.canAutoDownloadPatch = true;
        // 设置是否提示用户重启
        Beta.canNotifyUserRestart = false;
        // 设置是否自动合成补丁
        Beta.canAutoPatch = true;

        /**
         * 补丁回调接口，可以监听补丁接收、下载、合成的回调
         */
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFileUrl) {
                Log.i("Simon","onPatchReceived");
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Log.i("Simon","onDownloadReceived");
            }

            @Override
            public void onDownloadSuccess(String patchFilePath) {
                Log.i("Simon","onDownloadSuccess");
//                Beta.applyDownloadedPatch();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Log.i("Simon","onDownloadFailure");
            }

            @Override
            public void onApplySuccess(String msg) {
                Log.i("Simon","onApplySuccess");
            }

            @Override
            public void onApplyFailure(String msg) {
                Log.i("Simon","onApplyFailure");
            }

            @Override
            public void onPatchRollback() {
                Log.i("Simon","onApplyFailure");
            }
        };

        long start = System.currentTimeMillis();
//        Bugly.setUserId(this, "falue");
//        Bugly.setUserTag(this, 123456);
//        Bugly.putUserData(this, "key1", "123");
//        Bugly.setAppChannel(this, "bugly");


        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId,调试时将第三个参数设置为true
        Bugly.init(this, "54e3b66da1", true);
        long end = System.currentTimeMillis();
        Log.e("Simon", end - start + "ms");
}

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(base);

        // 安装tinker
        Beta.installTinker();
    }


    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }
}

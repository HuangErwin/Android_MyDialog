package com.jgduan;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Created by huangjunhui on 2017/8/15.10:29
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SophixManager.getInstance().queryAndLoadNewPatch();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        initHotFix();
    }

    private void initHotFix() {
        // initialize最好放在attachBaseContext最前面
        PackageInfo packageInfo = null;
        try {
             packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);//获取包对象信息

            Log.e("BaseApplication", packageInfo.versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SophixManager.getInstance().setContext(this)
                .setAppVersion(packageInfo.versionName)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
//                             Log.e("BaseApplication","表明新补丁生效需要重启");
                            Log.e("BaseApplication","表明补丁加载成功");

//  Toast.makeText(BaseApplication.this, "表明补丁加载成功", Toast.LENGTH_SHORT).show();
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
//                            Toast.makeText(BaseApplication.this, "表明新补丁生效需要重启", Toast.LENGTH_SHORT).show();
                            Log.e("BaseApplication","表明新补丁生效需要重启");

                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
//                            Toast.makeText(BaseApplication.this, "用户可以监听进入后台事件", Toast.LENGTH_SHORT).show();
                            Log.e("BaseApplication","用户可以监听进入后台事件");
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
// queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中

    }
}

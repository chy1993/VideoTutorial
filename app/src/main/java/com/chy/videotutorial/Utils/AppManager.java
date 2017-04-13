package com.chy.videotutorial.Utils;

import android.app.Activity;
import android.content.Context;

import java.util.LinkedList;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class AppManager {
    private LinkedList<Activity> mActivityList = null;
    private Activity mCurrentAty = null;

    private AppManager() {
        mActivityList = new LinkedList<Activity>();
    }

    private static class AppManagerHolder{
        private static AppManager mInstance = new AppManager();
    }

    /**
     * 单例模式
     *
     * @return
     * @exception
     */
    public static AppManager getInstance() {
        return AppManagerHolder.mInstance;
    }

    /**
     * 添加Activity到管理队列
     *
     *
     * @return
     * @exception
     */
    public void addActivity(Activity activity) {
        mActivityList.add(activity);
//        LogUtils.getInstance().i("添加进栈：" + activity.getClass().getSimpleName());
    }

    /**
     * 移除指定Activity
     *
     * @return
     * @exception
     */
    public void removeActivity(Activity activity) {
        // if ( null != mActivityList )
        mActivityList.remove(activity);

//        LogUtils.getInstance().i("出栈：" + activity.getClass().getSimpleName());
    }

    public void onResume(Activity activity) {
        mCurrentAty = activity;
//        LogUtils.getInstance().i("aty 名称：" + mCurrentAty.getClass().getSimpleName());
    }

    public Activity getCurrentAty() {
        return mCurrentAty;
    }

    /**
     * 结束所有Activity
     *      
     * @return
     * @exception
     */
    public void finishAllActivity() {
        for (Activity activity : mActivityList) {
            activity.finish();
            activity = null;
        }
        mActivityList.clear();
    }

    public void finishAllAtyWithoutCurrent() {
        for (Activity activity : mActivityList) {
            if ( null != mCurrentAty && mCurrentAty != activity ) {
                activity.finish();
                activity = null;
            }
        }

        mActivityList.clear();
        mActivityList.add(mCurrentAty);
    }

    /**
     * 退出应用程序
     *
     * @return
     * @exception
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            mCurrentAty = null;
//             ActivityManager activityMgr = (ActivityManager) context
//             .getSystemService(Context.ACTIVITY_SERVICE);
//             activityMgr.killBackgroundProcesses(context.getPackageName());
//             System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}
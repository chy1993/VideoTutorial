package com.chy.videotutorial.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/21
 */
public class AppUtils {

	public static final int getHeightInPx(Context context) {
		final int height = context.getResources().getDisplayMetrics().heightPixels;
		return height;
	}

	public static final int getWidthInPx(Context context) {
		final int width = context.getResources().getDisplayMetrics().widthPixels;
		return width;
	}

	public static final int getHeightInDp(Context context) {
		final float height = context.getResources().getDisplayMetrics().heightPixels;
		int heightInDp = px2dip(context, height);
		return heightInDp;
	}

	public static final int getWidthInDp(Context context) {
		final float width = context.getResources().getDisplayMetrics().widthPixels;
		int widthInDp = px2dip(context, width);
		return widthInDp;
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int px2sp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int sp2px(Context context, float spValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (spValue * scale + 0.5f);
	}
	
	/**
	 * 获得状态栏的高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusHeight(Context context) {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 判断网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				Network[] networks = cm.getAllNetworks();
				NetworkInfo networkInfo;
				for (Network mNetwork : networks) {
					networkInfo = cm.getNetworkInfo(mNetwork);
					if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
						return true;
					}
				}

//				NetworkRequest request = new NetworkRequest.Builder()
//						.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//						.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//						.build();
//				final ConnectivityManager manager = getSystemService(ConnectivityManager.class);
//				manager.registerNetworkCallback(request, new ConnectivityManager.NetworkCallback(){
//					@Override
//					public void onAvailable(Network network) {
//						NetworkInfo info = manager.getNetworkInfo(network);
//						Log.v(TAG, "== NETWORK type: " + info.getTypeName() + "[" + info.getSubtypeName() + "], state: " + info.getDetailedState());
//					});
			}else {
				//如果仅仅是用来判断网络连接
				//则可以使用 cm.getActiveNetworkInfo().isAvailable();
				@SuppressWarnings("deprecation")
				NetworkInfo[] info = cm.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 返回当前程序版本名
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			LogUtils.getInstance().e(e);
		}
		return versionName;
	}

	/**
	 * 返回当前程序版本号
	 */
	public static int getAppVersionCode(Context context) {
		int vsersionCode = 0;
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			vsersionCode = pi.versionCode;
			return pi.versionCode;

		} catch (Exception e) {
			LogUtils.getInstance().e(e);
		}
		return vsersionCode;
	}

	/**
	 * 获取本机电话号码
	 */
	public static String getPhoneNumb(Context context){
		TelephonyManager mTelephonyMgr;
		mTelephonyMgr = (TelephonyManager)  context.getSystemService(Context.TELEPHONY_SERVICE);
		return mTelephonyMgr.getLine1Number();
	}

	/**
	 * 防止按钮连续点击
	 */
	private static long lastClickTime;
	public synchronized static boolean isFastClick() {
		long time = System.currentTimeMillis();
		if ( time - lastClickTime < 500) {
			LogUtils.getInstance().i("快速点击中");
			return true;
		}
		lastClickTime = time;
		return false;
	}

	public synchronized static void clearLastClickTime(){
		lastClickTime = 0;
	}

	/**
	 * 判断SD卡是否挂载
	 *
	 * @return
	 */
	public static  boolean isSDCARDMounted(){
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}
			return false;
		}

	/**
	 * 获取屏幕高度
	 */
	public static int getScreenHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenHeight = dm.heightPixels;
		return screenHeight;
	}

	/**
	 * 获取采样率
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
     * @return
     */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if( height > reqHeight || width > reqWidth){
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;
			while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth){
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}
}

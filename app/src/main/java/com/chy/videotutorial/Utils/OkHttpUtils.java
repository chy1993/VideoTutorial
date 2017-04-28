package com.chy.videotutorial.Utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Description : OkHttp网络连接封装工具类
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/17
 */
public class OkHttpUtils {

    private static final String TAG = "OkHttpUtils";

    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.getMainLooper());

//        try {
//            setCertificates(HTLogisticsApp.getInstance().getAssets()
//                    .open(Constants.AssetsFileConstants.HTTPS_CER_FILE));
//        } catch (IOException e) {
//            Log.e("SslContextFactory", e.getMessage());
//        }
    }

    private void setCertificates(InputStream... certificates)
    {
        try
        {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try
                {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                    Log.e("SslContextFactory", e.getMessage());
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            Log.e("SslContextFactory", e.getMessage());
        }

    }

    private synchronized static OkHttpUtils getmInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpUtils();
        }
        return mInstance;
    }

    private void getRequest(String url, final ResultCallback callback) {
        final Request request = new Request.Builder().url(url).build();
        deliveryResult(callback, request);

        LogUtils.getInstance().i("Get接口地址：" + request);
    }

    private void postRequest(String url, final ResultCallback callback, List<Param> params, Object tag) {
        Request request = buildPostRequest(url, params, tag);
        deliveryResult(callback, request);

        LogUtils.getInstance().i("Post接口地址：" + request + "参数：" + genParamsUrl(params));
    }

    /**
     * 异步POST请求，支持图片上传
     *
     * @param url           接口URL
     * @param callback      结果回调
     * @param params        接口参数
     * @param file          文件
     * @param fileKey       文件参数
     * @param tag           请求标识
     */
    private void postRequest(String url, final ResultCallback callback, List<Param> params, File file, String fileKey, Object tag) {
        File[] files = null;
        String[] fileKeys = null;
        if ( null != file && null != fileKey ) {
            files = new File[]{file};
            fileKeys = new String[]{fileKey};
        }

        Request request = buildMultipartFormRequest(url, files, fileKeys, params, tag);
        deliveryResult(callback, request);

        LogUtils.getInstance().i("Post接口地址：" + request + "参数：" + genParamsUrl(params));
    }

    private void deliveryResult(final ResultCallback callback, Request request) {

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                if (null != e && null != e.getMessage()) {
                    // 请求取消，暂时不抛出事件
                    if (e.getMessage().equalsIgnoreCase("Canceled"))
                        return;
                }

                sendFailCallback(callback, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String str = response.body().string();
                    if (callback.mType == String.class) {
                        sendSuccessCallBack(callback, str);
                    } else {
                        Object object = JsonUtils.deserialize(str, callback.mType);
                        sendSuccessCallBack(callback, object);
                    }
                } catch (final Exception e) {
                    LogUtils.getInstance().e(e);
                    sendFailCallback(callback, e);
                }

            }
        });
    }

    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    private void sendSuccessCallBack(final ResultCallback callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }

    private Request buildPostRequest(String url, List<Param> params, Object tag) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().tag(tag).url(url).post(requestBody).build();
    }

    private void cancel(Object tag){
        mOkHttpClient.cancel(tag);
    }



    /**
     * 构建图片上传的请求
     *
     * @param files     图片文件
     * @param fileKeys  文件接收参数
     * @param params    其他参数
     * @return
     */
    private Request buildMultipartFormRequest(String url,File[] files,
                                              String[] fileKeys, List<Param> params, Object tag)
    {
        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);

        for (Param param : params)
            builder.addFormDataPart(param.key, param.value);

        if ( null != files && null != fileKeys )
        {
            if ( fileKeys.length == files.length ) {
                for ( int i = 0; i < files.length; i ++ ) {
                    builder.addFormDataPart(fileKeys[i], files[i].getName(), RequestBody.create(MEDIA_TYPE_PNG, files[i]));
                }
            }
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .tag(tag)
                .url(url)
                .post(requestBody)
                .build();
    }

    /**********************对外接口************************/

    /**
     * 生成接口参数url
     *
     * @param params 接口参数
     * @return 参数Url（如：userName=df&password=123）
     */
    public static String genParamsUrl(List<Param> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for ( Param param: params ){
            stringBuilder.append("&");
            stringBuilder.append(param.key);
            stringBuilder.append("=");
            stringBuilder.append(param.value);
        }

        if ( stringBuilder.length() > 1 )
            stringBuilder.deleteCharAt(0);

        return stringBuilder.toString();
    }

    /**
     * get同步请求
     * @param url
     * @return  Response
     * @throws IOException
     */
    public static Response getSync(String url) throws IOException {
        final Request request = new Request.Builder().url(url).build();
        return getmInstance().mOkHttpClient.newCall(request).execute();
    }

    /**
     * get同步请求
     * @param url
     * @return  String
     * @throws IOException
     */
    public static String getSyncAsString(String url) throws IOException {
        Response response = getSync(url);
        if ( response.isSuccessful() ){
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static Response postSync(String url, List<Param> params, Object tag) throws IOException {
        Request request = getmInstance().buildPostRequest(url, params, tag);
        OkHttpClient client = getmInstance().mOkHttpClient.clone();
        client.setConnectTimeout(3, TimeUnit.SECONDS);
        client.setReadTimeout(3, TimeUnit.SECONDS);
        return client.newCall(request).execute();
    }

    public static String postSyncAsString(String url, List<Param> params) throws IOException {
//        Response response = postSync(url, params);
//        if ( response.isSuccessful() ){
//            return response.body().string();
//        } else {
//            throw new IOException("Unexpected code " + response);
//        }

        return postSyncAsString(url, params, null);
    }

    public static String postSyncAsString(String url, List<Param> params, Object tag) throws IOException {
        Response response = postSync(url, params, tag);
        if ( response.isSuccessful() ){
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * get请求
     * @param url  请求url
     * @param callback  请求回调
     */
    public static void get(String url, ResultCallback callback) {
        getmInstance().getRequest(url, callback);
    }

    /**
     * post请求
     * @param url       请求url
     * @param callback  请求回调
     * @param params    请求参数
     */
    public static void post(String url, final ResultCallback callback, List<Param> params, Object tag) {
        getmInstance().postRequest(url, callback, params, tag);
    }

    public static void post(String url, final ResultCallback callback, List<Param> params) {
        post(url, callback, params, null);
    }

    /**
     * 异步POST请求，支持图片上传
     *
     * @param url           接口URL
     * @param callback      结果回调
     * @param params        接口参数
     * @param file          文件
     * @param fileKey       文件参数
     * @param tag           请求标识
     */
    public static void post(String url, final ResultCallback callback, List<Param> params, File file,
                                              String fileKey, Object tag) {
        getmInstance().postRequest(url, callback, params, file, fileKey, tag);
    }

    public static void cancelTag(Object tag)
    {
        getmInstance().cancel(tag);
    }


    /**
     * http请求回调类,回调方法在UI线程中执行
     * @param <T>
     */
    public static abstract class ResultCallback<T> {

        Type mType;

        public ResultCallback(){
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功回调
         * @param response
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败回调
         * @param e
         */
        public abstract void onFailure(Exception e);
    }

    /**
     * post请求参数类
     */
    public static class Param {

        String key;
        String value;

        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}

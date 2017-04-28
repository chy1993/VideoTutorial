package com.chy.videotutorial.module.main.model;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class MainModel {
    public interface OnLoadVideoListInfoListener{
        void onLoadVideoListInfoSuccess();
        void onLoadVideoListInfoFailure();
    }


//    public interface OnLoadMessageListener {
//        void onSuccess(List<MessageEntity> messageList);
//        void onFailure(String msg, Exception e);
//    }
//
    public void requestMessagesData(final int pageIndex, final OnLoadVideoListInfoListener messageListener) {
//        // 接口数据
//        OkHttpUtils.ResultCallback<String> loginCallBack = new OkHttpUtils.ResultCallback<String>() {
//
//            @Override
//            public void onSuccess(String response) {
//                LogUtils.getInstance().i("消息列表结果：" + response);
//                try
//                {
//                    ApiResponse<List<MessageEntity>> apiResponse = JsonUtils.deserialize(response, ApiResponse.class);
//                    if ( apiResponse.isSuccess() ){
//                        List<MessageEntity> beans = JsonUtils.deserialize(apiResponse.getData(), new TypeToken<List<MessageEntity>>() {}.getType());
//                        for ( MessageEntity entity: beans ) {
//                            entity.genHtmlText();
//                        }
//                        messageListener.onSuccess(beans);
//                    } else {
//                        messageListener.onFailure(apiResponse.getMessage(), null);
//                    }
//                } catch (Exception e) {
//                    messageListener.onFailure(HTLogisticsApp.getInstance().getString(R.string.message_model_loading_fail), e);
//                    LogUtils.getInstance().e(e);
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                messageListener.onFailure(HTLogisticsApp.getInstance().getString(R.string.url_connect_fail), e);
//            }
//        };
//
//        // 获取当前用户信息
//        LoginInfoEntity loginInfoEntity = AppData.getInstance().getLoginInfo();
//
//        List<OkHttpUtils.Param> params = new ArrayList<>();
//        params.add(new OkHttpUtils.Param("appInterface","0"));
//        params.add(new OkHttpUtils.Param("page", String.valueOf(pageIndex)));
//        params.add(new OkHttpUtils.Param("rows", String.valueOf(Urls.PAGE_SIZE)));
//        params.add(new OkHttpUtils.Param("username", loginInfoEntity.mUserName));
//        OkHttpUtils.post(Urls.MESSAGE_LIST_URL, loginCallBack, params);

    }
}
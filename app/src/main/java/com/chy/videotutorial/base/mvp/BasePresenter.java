package com.chy.videotutorial.base.mvp;


/**
 * 所有Presenter的基类
 *
 * Created by 丁飞 on 2016/1/7.
 */
public abstract class BasePresenter<V extends IBaseView> {
    /** View引用 */
    protected V mView;

//    private Subscription mSubscription;

    public BasePresenter(V view) {
        this.attachView(view);
    }

    public void attachView(V view) { this.mView = view; }
    public void detachView() {
//        this.unCurrentSubscribe();
        this.mView = null;
    }

//    protected void setCurrentSubscription( Subscription subscription) {
//        unCurrentSubscribe();
//        this.mSubscription = subscription;
//    }
//
//    protected void unCurrentSubscribe() {
//        if ( null != mSubscription && mSubscription.isUnsubscribed() ) {
//            mSubscription.unsubscribe();
//        }
//    }
//
//    protected String genExceptionMsg(Throwable e) {
//        String msg;
//        if (!NetworkUtils.isNetworkConnected(PandoraApp.getInstance())) {
//            msg = "网络不可用";
//        } else if ( e instanceof ApiException ) {
//            msg = e.getMessage();
//        } else if ( e instanceof ApiTokenInvalidException ) {
//            msg = e.getMessage();
//        } else if ( e instanceof HttpException ) {
//            HttpException httpException = (HttpException)e;
//            if ( 413 == httpException.code() ) {
//                msg = "亲，可能上传的图片容量过大，请调整后重新上传试试…";
//            } else {
//                msg = "服务器连接失败，请稍后重试...";
//            }
//        } else {
//            msg = "服务器连接失败，请稍后重试...";
//        }
//        return msg;
//    }

}
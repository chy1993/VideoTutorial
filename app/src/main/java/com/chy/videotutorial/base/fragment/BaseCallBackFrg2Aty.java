package com.chy.videotutorial.base.fragment;


import android.content.Context;

/**
 * Fragment基类： 用于Activity下的Fragment
 *
 * @param <L> Fragment向上层Activity反馈的Listener
 *
 */
public abstract class BaseCallBackFrg2Aty<L> extends BaseFragment {
    /**
     * 父窗口的回调引用
     */
    protected L mListener = null;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (L) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement OnFragmentCallBack");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
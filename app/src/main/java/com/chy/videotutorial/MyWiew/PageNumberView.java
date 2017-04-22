package com.chy.videotutorial.MyWiew;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.chy.videotutorial.R;


/**
 * Created by chenyu on 2017/4/22.
 */

public class PageNumberView extends RelativeLayout {
    private Context  mContext;

    private Button mPrevPage;
    private Button mFirstPage;
    private RadioGroup mPageNumber;
    private Button mLastPage;
    private Button mNextPage;
    private RadioButton mFirst;
    private RadioButton mSecond;
    private RadioButton mThird;
    private RadioButton mFourth;
    private RadioButton mFifth;



    public PageNumberView(Context context) {
        super(context);
        mContext = context;
        initLayout(mContext);
    }

    public PageNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.UniversalMediaController);
//        mScalable = a.getBoolean(R.styleable.UniversalMediaController_uvv_scalable, false);
//        a.recycle();
        initLayout(mContext);
    }


    /**
     * 自定义布局的地方
     * @param context
     */
    private void initLayout(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRoot = inflater.inflate(R.layout.page_number_layout, this);
        initPageNumView(viewRoot);
    }


    /**
     * 初始化各控件的地方
     * @param v
     */
    private void initPageNumView(View v) {
        mPrevPage = (Button) v.findViewById(R.id.btPrevPage);
        mFirstPage = (Button) v.findViewById(R.id.btFirstPage);
        mPageNumber = (RadioGroup) v.findViewById(R.id.rgPageNumber);
        mLastPage = (Button) v.findViewById(R.id.btLastPage);
        mNextPage = (Button) v.findViewById(R.id.btNextPage);
        mFirst = (RadioButton) v.findViewById(R.id.rbFirst);
        mSecond = (RadioButton) v.findViewById(R.id.rbSecond);
        mThird = (RadioButton) v.findViewById(R.id.rbThird);
        mFourth = (RadioButton) v.findViewById(R.id.rbFourth);
        mFifth = (RadioButton) v.findViewById(R.id.rbFifth);


    }



}

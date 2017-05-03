package com.chy.videotutorial.MyWiew;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.chy.videotutorial.R;
import com.chy.videotutorial.entities.VideoTypeDetailInfo;
import com.chy.videotutorial.module.main.view.OnlineVideoGVAdapter;

import java.util.List;


/**
 * Created by chenyu on 2017/4/22.
 */

public class OnlinePageNumberView extends RelativeLayout{
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

    public int mTotalPages;                        //总页数
    private int mCurrentPageNum;                   //当前选中的RadioButton展示的页码

    private boolean isHandCheck = true;            //是否是手动切换按钮 默认是

    OnPageChangeListener mListener;

    //切换页码时数据切换
    private OnlineVideoGVAdapter mAdapter;
    private List<VideoTypeDetailInfo> mData;

    public interface OnPageChangeListener{
        void onChanged(int pageNum);
    }

    public void setPageChangeListener(OnPageChangeListener listener){
        this.mListener = listener;
    }


    public OnlinePageNumberView(Context context) {
        super(context);
        mContext = context;
        initLayout(mContext);
    }

    public OnlinePageNumberView(Context context, AttributeSet attrs) {
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

        initRadioGruop();

        mPrevPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFirst.isChecked()){

                }else if (mSecond.isChecked()){
                    mFirst.setChecked(true);
                }else if (mThird.isChecked()){
                    mSecond.setChecked(true);
                }else if(mFourth.isChecked()){
                    mThird.setChecked(true);
                }else if(mFifth.isChecked()){
                    mFourth.setChecked(true);
                }
            }
        });

        mFirstPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioButtonText(1);
                mFirst.setChecked(true);
            }
        });

        mNextPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFirst.isChecked()){
                    mSecond.setChecked(true);
                }else if (mSecond.isChecked()){
                    mThird.setChecked(true);
                }else if (mThird.isChecked()){
                    mFourth.setChecked(true);
                }else if(mFourth.isChecked()){
                    mFifth.setChecked(true);
                }else if(mFifth.isChecked()){
//                    mFirst.setChecked(true);
                }
            }
        });

        mLastPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioButtonText(mTotalPages-4);
                mFifth.setChecked(true);
            }
        });

    }

    private void initRadioGruop(){
        //默认的RadioButton显示
        mFirst.setChecked(true);                                      //默认第一页被选中
        setRadioButtonText(1);

        mPageNumber.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (mTotalPages <= 5){
                        switch (checkedId){
                            case R.id.rbFirst:
                                mListener.onChanged(1);
                                break;
                            case R.id.rbSecond:
                                mListener.onChanged(2);
                                break;
                            case R.id.rbThird:
                                if (isHandCheck)
                                    mListener.onChanged(3);
                                break;
                            case R.id.rbFourth:
                                mListener.onChanged(4);
                                break;
                            case R.id.rbFifth:
                                mListener.onChanged(5);
                                break;
                            default:
                                break;

                        }
                    }else {
                        switch (checkedId){
                            case R.id.rbFirst:
                                    mCurrentPageNum = Integer.parseInt(mFirst.getText().toString());
                                    updatePadioButtonText(mCurrentPageNum);
                                    mListener.onChanged(mCurrentPageNum);
                                break;
                            case R.id.rbSecond:
                                mCurrentPageNum = Integer.parseInt(mSecond.getText().toString());
                                updatePadioButtonText(mCurrentPageNum);
                                mListener.onChanged(mCurrentPageNum);
                                break;
                            case R.id.rbThird:
                                if (isHandCheck){
                                    mCurrentPageNum = Integer.parseInt(mThird.getText().toString());
                                    updatePadioButtonText(mCurrentPageNum);
                                    mListener.onChanged(mCurrentPageNum);
                                }
                                break;
                            case R.id.rbFourth:
                                mCurrentPageNum = Integer.parseInt(mFourth.getText().toString());
                                updatePadioButtonText(mCurrentPageNum);
                                mListener.onChanged(mCurrentPageNum);
                                break;
                            case R.id.rbFifth:
                                mCurrentPageNum = Integer.parseInt(mFifth.getText().toString());
                                updatePadioButtonText(mCurrentPageNum);
                                mListener.onChanged(mCurrentPageNum);
                                break;
                            default:
                                break;
                        }
                    }
            }
        });
    }



    /**
     * 给外界提供设置页码的方法
     * @param totalPages
     */
    public void setmTotalPages(int totalPages){
        this.mTotalPages = totalPages;
        updatePageNum();
    }

    /**
     * 根据总页数更新页码显示状况
     */
    private void updatePageNum(){
        if (mTotalPages <= 5){
            setPageNubRadioButton(mTotalPages);
        }else {
            setPageNubRadioButton(5);
        }
    }

    /**
     * 控制显示页码的RadioButton显示个数
     */
    private void setPageNubRadioButton(int pageNum){
        if (pageNum == 1){
            mFirst.setVisibility(View.VISIBLE);
            mSecond.setVisibility(View.GONE);
            mThird.setVisibility(View.GONE);
            mFourth.setVisibility(View.GONE);
            mFifth.setVisibility(View.GONE);

        }else if (pageNum == 2){
            mFirst.setVisibility(View.VISIBLE);
            mSecond.setVisibility(View.VISIBLE);
            mThird.setVisibility(View.GONE);
            mFourth.setVisibility(View.GONE);
            mFifth.setVisibility(View.GONE);
        }else if (pageNum == 3){
            mFirst.setVisibility(View.VISIBLE);
            mSecond.setVisibility(View.VISIBLE);
            mThird.setVisibility(View.VISIBLE);
            mFourth.setVisibility(View.GONE);
            mFifth.setVisibility(View.GONE);
        }else if (pageNum == 4){
            mFirst.setVisibility(View.VISIBLE);
            mSecond.setVisibility(View.VISIBLE);
            mThird.setVisibility(View.VISIBLE);
            mFourth.setVisibility(View.VISIBLE);
            mFifth.setVisibility(View.GONE);
        }else if (pageNum == 5){
            mFirst.setVisibility(View.VISIBLE);
            mSecond.setVisibility(View.VISIBLE);
            mThird.setVisibility(View.VISIBLE);
            mFourth.setVisibility(View.VISIBLE);
            mFifth.setVisibility(View.VISIBLE);
        }

    }

    /**
     * RadioButton的Text设置
     * @param first
     */
    private void setRadioButtonText(int first){
        mFirst.setText(first+"");
        mSecond.setText(first + 1 +"");
        mThird.setText(first + 2 +"");
        mFourth.setText(first + 3 +"");
        mFifth.setText(first + 4 +"");
    }

    /**
     * 更新RadioButton的Text设置
     */
    private void updatePadioButtonText(int currentPageNum){
        if (currentPageNum+2 <= mTotalPages && currentPageNum-2 >= 1){
            setRadioButtonText(currentPageNum-2);
            isHandCheck = false;
            mThird.setChecked(true);
            isHandCheck = true;
        }else if (currentPageNum == mTotalPages){
            setRadioButtonText(currentPageNum-4);
        }else if (currentPageNum+1 == mTotalPages){
            setRadioButtonText(currentPageNum-3);
        }else if (currentPageNum == 1){
            setRadioButtonText(1);
        }else if (currentPageNum == 2){
            setRadioButtonText(1);
        }else {
            setRadioButtonText(1);
        }
    }

}

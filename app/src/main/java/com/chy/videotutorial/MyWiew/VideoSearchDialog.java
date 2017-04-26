package com.chy.videotutorial.MyWiew;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chy.videotutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/4/25.
 */

public class VideoSearchDialog extends Dialog {
    private Context mContext;

    @BindView(R.id.etSearchContent)
    EditText mSearchContent;

    @BindView(R.id.btSearch)
    Button mSearch;

    @BindView(R.id.ibUpPage)
    ImageButton mUpPage;

    @BindView(R.id.ibDownPage)
    ImageButton mDownPage;

    @BindView(R.id.rvSearchContent)
    RecyclerView mRecycleView;

    @BindView(R.id.tvPageNum)
    TextView mPage;

    @BindView(R.id.ivCloseDialog)
    ImageView mCloseDialog;

    SearchContentRcyViewAdapter mAdapter;

    public VideoSearchDialog(Context context) {
        super(context, R.style.MyDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_video_search_layout);
        ButterKnife.bind(this);
        //按空白处不能取消动画
//        setCanceledOnTouchOutside(false);

//        //初始化界面控件
//        initView();
//        //初始化界面数据
//        initData();
//        //初始化界面控件的事件
//        initEvent();

    }

    private void initView(){

    }

    private void initRecycleView(){
    }



    @OnClick(R.id.btSearch)
    public void onSearch(){
        Toast.makeText(mContext,"111",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.ivCloseDialog)
    public void onCloseDialog(){

    }


}

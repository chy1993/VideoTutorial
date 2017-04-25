package com.chy.videotutorial.MyWiew;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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


    @OnClick(R.id.btSearch)
    public void onSearch(){
        Toast.makeText(mContext,"111",Toast.LENGTH_SHORT).show();
    }


}

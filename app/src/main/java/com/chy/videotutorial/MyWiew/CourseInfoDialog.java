package com.chy.videotutorial.MyWiew;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chy.videotutorial.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 陈宇 on 2017/4/25.
 */

public class CourseInfoDialog extends Dialog {
    @BindView(R.id.tvCourseName)
    TextView mCourseName;

    @BindView(R.id.tvCourseInfo)
    TextView mCourseInfo;

    private Context mContext;
    private String uri;

    public CourseInfoDialog(Context context) {
        super(context, R.style.MyDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_video_info_layout);
        ButterKnife.bind(this);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();

    }

    private void initView(){

    }




    @OnClick(R.id.ivCloseDialogInfo)
    public void onCloseDialog(){
        this.dismiss();
    }

    @OnClick(R.id.btStartPlayer)
    public void onStartPlayer(){
        Toast.makeText(mContext,"3222",Toast.LENGTH_SHORT).show();
    }

}

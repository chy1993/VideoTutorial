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
import com.chy.videotutorial.Utils.Constants;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;
import com.chy.videotutorial.entities.VideoTypeDetailPlusInfo;
import com.chy.videotutorial.module.videoplayer.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by chenyu on 2017/4/25.
 */

public class CourseInfoDialog extends Dialog {
    @BindView(R.id.tvCourseName)
    TextView mCourseName;

    @BindView(R.id.tvCourseInfo)
    TextView mCourseInfo;

    private Context mContext;
    private String uri;
    private VideoTypeDetailPlusInfo mData;

    public CourseInfoDialog(Context context, VideoTypeDetailPlusInfo data) {
        super(context, R.style.MyDialog);
        mData = data;
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
        mCourseName.setText(mData.getContent().get(0).getTitle());
        mCourseInfo.setText(mData.getContent().get(0).getCharacteristic());
        uri = mData.getContent().get(0).getVideoPath();
    }




    @OnClick(R.id.ivCloseDialogInfo)
    public void onCloseDialog(){
        this.dismiss();
    }

    @OnClick(R.id.btStartPlayer)
    public void onStartPlayer(){
        VideoPlayerActivity.navigationToActivity((BaseAppCompatActivity) mContext, Constants.ONLINE_VIDEO,uri);
    }

}

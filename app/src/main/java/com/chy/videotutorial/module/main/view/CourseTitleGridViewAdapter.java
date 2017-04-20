package com.chy.videotutorial.module.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.chy.videotutorial.R;

/**
 * Created by Administrator on 2017/4/19.
 */

public class CourseTitleGridViewAdapter extends BaseAdapter {
    //上下文对象
    private Context mContext;
    //图片数组
    private Integer[] imgs = {

    };
    CourseTitleGridViewAdapter(Context context){
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(75, 75));//设置ImageView对象布局
            imageView.setAdjustViewBounds(false);//设置边界对齐
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置刻度的类型
            imageView.setPadding(8, 8, 8, 8);//设置间距
        }
        else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(R.mipmap.course_title_bg);//为ImageView设置图片资源
        return imageView;
    }
}

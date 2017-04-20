package com.chy.videotutorial.module.main.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chy.videotutorial.R;

/**
 * Created by Administrator on 2017/4/19.
 */

public class CourseTitleGridViewAdapter extends BaseAdapter {
    //上下文对象
    private Context mContext;
//    //图片数组
//    private Integer[] imgs = {
//
//    };
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
        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_gridview_course_title_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.mCourseTitle = (TextView) convertView.findViewById(R.id.tvCourseTitle);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mCourseTitle.setText("课程" + position );
        return convertView;

    }

    class ViewHolder
    {
        public TextView mCourseTitle;
    }
}

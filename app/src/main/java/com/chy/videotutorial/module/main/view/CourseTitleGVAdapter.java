package com.chy.videotutorial.module.main.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chy.videotutorial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class CourseTitleGVAdapter extends BaseAdapter {
    private Context mContext;
    private List lists;//数据源
    private int mIndex; // 页数下标，标示第几页，从0开始
    private int mPargerSize;// 每页显示的最大的数量



    public CourseTitleGVAdapter(Context context, List lists, int mIndex, int mPargerSize) {
        this.mContext = context;
        this.mIndex = mIndex;
        this.mPargerSize = mPargerSize;
        this.lists = lists;

    }

    /**
     * 先判断数据集的大小是否显示满本页lists.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量lists的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个
     */
    @Override
    public int getCount() {
        return lists.size() > (mIndex + 1) * mPargerSize ?
                mPargerSize : (lists.size() - mIndex*mPargerSize);
    }

    @Override
    public Object getItem(int arg0) {
        return lists.get(arg0 + mIndex * mPargerSize);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0 + mIndex * mPargerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseTitleGVAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_gridview_course_title_layout, null);
            viewHolder = new CourseTitleGVAdapter.ViewHolder();

            viewHolder.mCourseTitle = (TextView) convertView.findViewById(R.id.tvCourseTitle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CourseTitleGVAdapter.ViewHolder) convertView.getTag();
        }

        //重新确定position因为拿到的是总数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + mIndex * mPargerSize;//假设mPageSiez

        viewHolder.mCourseTitle.setText("课程" + pos );

        return convertView;
    }
    class ViewHolder
    {
        public TextView mCourseTitle;
    }
}

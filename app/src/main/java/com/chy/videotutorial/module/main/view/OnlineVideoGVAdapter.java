package com.chy.videotutorial.module.main.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chy.videotutorial.R;
import com.chy.videotutorial.entities.VideoTypeDetailInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class OnlineVideoGVAdapter extends BaseAdapter {
    private Context mContext;
    private List lists;                                     //数据源
    private int mIndex;                                     // 页数下标，标示第几页，从0开始
    private int mPargerSize;                                // 每页显示的最大的数量
    List<VideoTypeDetailInfo> mVideoTypeDetailInfos;



    public OnlineVideoGVAdapter(Context context, List lists, int mIndex, int mPargerSize) {
        this.mContext = context;
        this.mIndex = mIndex;
        this.mPargerSize = mPargerSize;
        this.lists = lists;

    }

    public void setData(List<VideoTypeDetailInfo> data){
        mVideoTypeDetailInfos = data;
        this.notifyDataSetChanged();
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
    public Object getItem(int position) {
        return lists.get(position + mIndex * mPargerSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPargerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OnlineVideoGVAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_gridview_online_video_layout, null);
            viewHolder = new OnlineVideoGVAdapter.ViewHolder();

            viewHolder.mVideoContent = (TextView) convertView.findViewById(R.id.tvVideoContent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OnlineVideoGVAdapter.ViewHolder) convertView.getTag();
        }

        //重新确定position因为拿到的是总数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + mIndex * mPargerSize;                            //假设mPageSiez

        viewHolder.mVideoContent.setText("课程" + pos );

        return convertView;
    }
    private static class ViewHolder
    {
        ImageView mVideoImg;
        TextView mVideoTitle;
        TextView mVideoContent;
        TextView mVideoTeacher;
    }
}

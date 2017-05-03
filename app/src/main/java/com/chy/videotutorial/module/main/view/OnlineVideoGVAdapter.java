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
    private int mPargerSize;                                // 每页显示的最大的数量
    List<VideoTypeDetailInfo> mData;                        //数据源

    public OnlineVideoGVAdapter(Context context, int mPargerSize) {
        this.mContext = context;
        this.mPargerSize = mPargerSize;
    }

    public void setData(List<VideoTypeDetailInfo> data){
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return (mData == null)? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OnlineVideoGVAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_gridview_online_video_layout, null);
            viewHolder = new OnlineVideoGVAdapter.ViewHolder();

            viewHolder.mVideoContent = (TextView) convertView.findViewById(R.id.tvVideoContent);
            viewHolder.mVideoTitle = (TextView) convertView.findViewById(R.id.tvVideoTitle);
            viewHolder.mVideoTeacher = (TextView) convertView.findViewById(R.id.tvVideoTeacher);
            viewHolder.mVideoImg = (ImageView) convertView.findViewById(R.id.ivOnlineVideo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OnlineVideoGVAdapter.ViewHolder) convertView.getTag();
        }


        viewHolder.mVideoContent.setText( mData.get(position).getTitle());
        viewHolder.mVideoTitle.setText(mData.get(position).getSerices());
        viewHolder.mVideoTeacher.setText(mData.get(position).getKeynote());

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

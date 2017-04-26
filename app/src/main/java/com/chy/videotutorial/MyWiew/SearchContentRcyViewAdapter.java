package com.chy.videotutorial.MyWiew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chy.videotutorial.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 视频搜索展示列表的适配器
 */
public class SearchContentRcyViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    List mData;

    public void setData(List data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }


    public SearchContentRcyViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcyview_search_content_layout, parent, false);
        SearchContentViewHolder bindViewHolder = new SearchContentViewHolder(view);


        return bindViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SearchContentViewHolder itemViewHolder = (SearchContentViewHolder) holder;

    }


    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();
    }

    /**
     * 订单信息项布局缓存
     */
    class SearchContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvVideoContent)
        TextView mVideoContent;

        @BindView(R.id.tvVideoTitle)
        TextView mVideoTitle;

        public SearchContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

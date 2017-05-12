package com.houliangliang.bawei.mycheckboxrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * date: 2017/5/12
 * author:侯亮亮候亮亮
 */

public class Main2Adapter extends RecyclerView.Adapter<Main2Adapter.ListViewHodler> {
    private Context mContext;
    private String[] str;

    public Main2Adapter(Context context, String[] str) {
        mContext = context;
        this.str = str;
    }

    @Override
    public ListViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.list_item2, null);
        ListViewHodler listViewHodler = new ListViewHodler(inflate);
        return listViewHodler;
    }

    @Override
    public void onBindViewHolder(ListViewHodler holder, int position) {
        holder.setData(str[position]);
    }

    @Override
    public int getItemCount() {
        return str.length;
    }

    public class ListViewHodler extends RecyclerView.ViewHolder {

        private final TextView mTv;

        public ListViewHodler(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tv2);

        }

        public void setData(String data) {
            mTv.setText(data);
        }
    }
}

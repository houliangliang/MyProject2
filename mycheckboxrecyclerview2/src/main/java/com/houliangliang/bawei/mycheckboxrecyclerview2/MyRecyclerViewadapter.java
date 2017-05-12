package com.houliangliang.bawei.mycheckboxrecyclerview2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * date: 2017/5/12
 * author:侯亮亮候亮亮
 */

public class MyRecyclerViewadapter extends RecyclerView.Adapter<MyRecyclerViewadapter.listViewHodler> {



    private Context mContext;
    private List<String> arr;

    public MyRecyclerViewadapter(Context context, List<String> arr) {
        mContext = context;
        this.arr = arr;
    }

    @Override
    public MyRecyclerViewadapter.listViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.layout, null);
        listViewHodler listViewHodler = new listViewHodler(inflate);
        return listViewHodler;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewadapter.listViewHodler holder, int position) {

        holder.setdata(arr.get(position));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class listViewHodler extends RecyclerView.ViewHolder {

        private final TextView mTv;
        private final CheckBox mCheckBox;

        public listViewHodler(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.layout_tv);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.layout_check);

        }

        public void setdata(String data) {
            mTv.setText(data);
        }
    }
}

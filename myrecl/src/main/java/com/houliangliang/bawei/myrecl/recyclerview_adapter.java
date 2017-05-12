package com.houliangliang.bawei.myrecl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * date: 2017/5/10
 * author:侯亮亮候亮亮
 */

public class recyclerview_adapter  extends RecyclerView.Adapter<recyclerview_adapter.listViewHoder> {

    private Context mContext;
    private List<DataBean> list;
    OnItemClickListener mOnItemClickListener;

    public recyclerview_adapter(Context context, List list) {
        mContext = context;
        this.list = list;
    }
    public  void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }

    @Override
    public recyclerview_adapter.listViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.list, null);
        listViewHoder listViewHoder = new listViewHoder(inflate);
       listViewHoder.mOnItemClickListener=mOnItemClickListener;
        return listViewHoder;
    }

    @Override
    public void onBindViewHolder(recyclerview_adapter.listViewHoder holder, int position) {
        DataBean dataBean = list.get(position);
        holder.setData(dataBean);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class listViewHoder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        private final ImageView mIm;
        private final TextView mName;
        OnItemClickListener mOnItemClickListener;

        public listViewHoder(View itemView) {
            super(itemView);
            mIm = (ImageView) itemView.findViewById(R.id.list_icon);
            mName = (TextView) itemView.findViewById(R.id.list_name);
            itemView.setOnLongClickListener(this);

        }

        public void setData(DataBean data) {
            mIm.setBackgroundResource(data.getIcon());
            mName.setText(data.getName());
        }







        @Override
        public boolean onLongClick(View v) {
            mOnItemClickListener.ItemChickListeren(getAdapterPosition());
            return false;
        }
    }
}

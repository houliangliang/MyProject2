package com.houliangliang.bawei.myokhttp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * date: 2017/5/10
 * author:侯亮亮候亮亮
 */

//RecyclerView的适配器，注意要指定泛型，一般我们就是类名的viewholder继承viewholder(内部已经实现了复用优化机制)，这里的泛型是在自己创建的内部类
public class recyclerviewadapter extends RecyclerView.Adapter<recyclerviewadapter.ListViewHodler> {
   //上下文
    private Context mContext;
    //网络请求的Bean的集合
    private List<DataBean.ResultBean.DateBean> mList;
    public recyclerviewadapter(Context context, List<DataBean.ResultBean.DateBean> list) {
        mContext = context;
        mList = list;
    }
//这是继承RecyclerView.Adapter重写的方法，这里主要是让我们把要展示的自定义view用打气筒打冲传到创建的内部类里，
    //然后把内部类返回
    @Override
    public ListViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.list, null);
        ListViewHodler listViewHodler = new ListViewHodler(inflate);
        return listViewHodler;
    }
  //当自定义的内部类跟数据绑定回调
    @Override
    public void onBindViewHolder(ListViewHodler holder, int position) {
        //从集合里拿对应的itme的数据对象
        DataBean.ResultBean.DateBean dateBean = mList.get(position);
        //返回到自定义的内部类里
        holder.setData(dateBean);
    }
//数据的个数跟listview适配的时候一样
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class ListViewHodler extends RecyclerView.ViewHolder {

        private final TextView mTv;

        public ListViewHodler(View itemView) {
            super(itemView);
            //通过返回的view来获取控件
            mTv = (TextView) itemView.findViewById(R.id.title);
        }
        public void setData(DataBean.ResultBean.DateBean data) {
            //根据放的数据展示出来
           mTv.setText(data.getTitle());
        }
    }
}

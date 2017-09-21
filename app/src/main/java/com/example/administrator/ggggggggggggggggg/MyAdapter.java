package com.example.administrator.ggggggggggggggggg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ggggggggggggggggg.Bean.TestBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author lvhongyang.
 * @time 2017/9/21.
 * @desc:
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //声明两个集合用于接受构造方法传来的参数在本地使用
    private List<TestBean.DataBean> list;


    //声明上下文引用，用于加载布局文件
    private Context context;

    //用构造方法传入需要的参数，
    public MyAdapter(Context context, List<TestBean.DataBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //返回MyViewHolder对象，通过构造方法传入加载布局文件得到的view对象
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

////        holder.iv.setImageResource(R.mipmap.ic_launcher);
        holder.age.setText(list.get(position).getUserAge()+"岁");
        holder.job.setText(list.get(position).getOccupation());
        holder.desc.setText(list.get(position).getIntroduction());
        //用过Picasso框架对图片处理并显示到iv上
        //用with()方法初始化，,
        Picasso.with(context)
                //load()下载图片
                .load(list.get(position).getUserImg())

                //init()显示到指定控件
                .into(holder.iv);

        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }

    }



    @Override
    public int getItemCount() {

        //返回数据源大小
        return list.size();
    }

    //自定义MyViewHolder类用于复用
    class MyViewHolder extends RecyclerView.ViewHolder {
        //声明imageview对象
        private ImageView iv;
        private TextView age;
        private TextView job;
        private TextView desc;

        //构造方法中初始化imageview对象
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageview);
            age = (TextView) itemView.findViewById(R.id.age);
            job = (TextView) itemView.findViewById(R.id.job);
            desc = (TextView) itemView.findViewById(R.id.desc);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}

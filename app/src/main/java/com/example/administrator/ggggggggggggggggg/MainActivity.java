package com.example.administrator.ggggggggggggggggg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.ggggggggggggggggg.Bean.TestBean;
import com.example.administrator.ggggggggggggggggg.Utils.NetWork;
import com.example.administrator.ggggggggggggggggg.Utils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //获取的json数据中的数据集合
    private List<TestBean.DataBean> list = new ArrayList<>();

    //声明recyclerview引用
    private RecyclerView mRecyclerView;

    //声明自定义请求类
    private MyAdapter adapter;


    //用插件自动生成初始化view代码的方法
    private void assignViews() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }


    //网络请求数据的网址
    private String url = "http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        assignViews();

        IsNET();
        //开启网络下载数据的方法
        startTask();



    }

    private void IsNET() {
        boolean available = NetWork.isNetworkConnected(MainActivity.this);

        if (available) {
            Toast.makeText(MainActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
        }

    }

    private void startTask() {

        //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
        OkHttpUtils.getInstance().getBeanOfOk(this, url, TestBean.class,
                new OkHttpUtils.CallBack<TestBean>() {
                    @Override
                    public void getData(TestBean testBean) {

                        Log.i("===", "getData: " + testBean.toString());
                        if (testBean != null) {

                            //如果不为空用本地list接收
                            list.addAll(testBean.getData());

                            //数据一旦回调成功初始化数据源和适配器

                            initAdapter();
                        }
                    }
                });


    }

    private void initAdapter() {


        //创建自定义适配器对象
        adapter = new MyAdapter(this, list);


        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,list.get(position).getIntroduction().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        //设置recyclerview适配器
        mRecyclerView.setAdapter(adapter);

        //刷新适配器
        adapter.notifyDataSetChanged();

    }


}

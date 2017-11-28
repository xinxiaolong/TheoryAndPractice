package com.example.fragment.theoryandpractice.coordinatorLayoutPractice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.coordinatorLayoutPractice.adapter.MyViewPagerAdapter;
import com.example.fragment.theoryandpractice.coordinatorLayoutPractice.fragment.FragmentOne;
import com.example.fragment.theoryandpractice.coordinatorLayoutPractice.fragment.FragmentThree;
import com.example.fragment.theoryandpractice.coordinatorLayoutPractice.fragment.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LayoutInflater mInflater = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_coordinator);
        //        设定状态栏的颜色，当版本大于4.4时起作用
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //tab数据
        String tab[] = {"仿JD浏览商品","上滑隐藏头部","下滑显示头部"};
        mInflater=LayoutInflater.from(this);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.actMainVp);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(FragmentOne.newInstance(), tab[0]);//添加Fragment
        viewPagerAdapter.addFragment(FragmentTwo.newInstance(), tab[1]);
        viewPagerAdapter.addFragment(FragmentThree.newInstance(), tab[2]);
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器

        //设置TabLayout的模式,这里主要是用来显示tab展示的情况的
        //TabLayout.MODE_FIXED          各tab平分整个工具栏,如果不设置，则默认就是这个值
        //TabLayout.MODE_SCROLLABLE     适用于多tab的，也就是有滚动条的，一行显示不下这些tab可以用这个   当然了，你要是想做点特别的，像知乎里就使用的这种效果

        //设置tablayout距离上下左右的距离
        //tab_title.setPadding(20,20,20,20);

        tabLayout = (TabLayout) findViewById(R.id.actTabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(tab[0]));
        tabLayout.addTab(tabLayout.newTab().setText(tab[1]));
        tabLayout.addTab(tabLayout.newTab().setText(tab[2]));
        tabLayout.setupWithViewPager(mViewPager);

        initoper2();


        ListView listView=(ListView) findViewById(R.id.listView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }

        listView.setAdapter(adapter);
    }


    BaseAdapter adapter=new BaseAdapter() {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.include_text_view_recycler_layout, null);
            TextView textView=(TextView) convertView.findViewById(R.id.text);
            textView.setText(position+"");
            return convertView;
        }
    };


    private void initoper2() {
//        FloatingActionButton actMainDelete = (FloatingActionButton) findViewById(R.id.actMainDelete);
//        actMainDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v,"干什么呢?",Snackbar.LENGTH_LONG).setAction("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        UIUtils.showToast("取消了信息");
//                    }
//                }).show();
//            }
//        });
//        text = (TextView) findViewById(R.id.act_text);
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v,"我们都一样",Snackbar.LENGTH_LONG).setAction("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        UIUtils.showToast("取消吗");
//                    }
//                }).show();
//            }
//        });
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                UIUtils.showToast("点击了取消");
//                                startActivity(new Intent(MainActivity.this, MainActivity02.class));
//                            }
//                        }).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}

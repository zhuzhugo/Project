package com.example.zmy1.excise;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharepreferences;
    private SharedPreferences.Editor editor;
    private Button button;
    private ArrayList<View> list = new ArrayList<>();
    private int images[] = {R.mipmap.bg_guide_01,R.mipmap.bg_guide_02,R.mipmap.bg_guide_03,R.mipmap.chrysanthemum};
    private ViewPager pager;
    private Myadapter adapter;
    private Handler handler = new Handler();
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        sharepreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        final boolean flag = sharepreferences.getBoolean("key",true);
        if(flag){
            setContentView(R.layout.activity_main);

            initView();
            initData();
            initAdapter();

        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,ActivityTwo.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);

        }


    }

    private void initAdapter() {
        adapter  = new Myadapter();
        pager.setAdapter(adapter);
    }

    private void initData() {
        iv.setVisibility(View.GONE);
        for (int i = 0; i < images.length - 1; i++) {
            ImageView iv= new ImageView(this);
            iv.setImageResource(images[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(iv);
        }


        View v = LayoutInflater.from(this).inflate(R.layout.item02,null);

        list.add(v);

        Button button = (Button) v.findViewById(R.id.but_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharepreferences.edit().putBoolean("key", false).commit();
                Intent intent = new Intent(MainActivity.this,ActivityTwo.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        iv = (ImageView) findViewById(R.id.iv);
    }

    class Myadapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}

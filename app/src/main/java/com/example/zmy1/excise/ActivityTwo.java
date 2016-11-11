package com.example.zmy1.excise;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zmy1 on 2016/11/10.
 */
public class ActivityTwo extends AppCompatActivity {
    private ArrayList<Fragment> frags = new ArrayList<>();
    private RadioGroup group;
    private FrameLayout fl;
    private FragmentManager manager;
    private Fragment lastfragment;
    private long time=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        manager = getSupportFragmentManager();
        initView();
        initFragments();

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fl,frags.get(0));
        ft.commit();

        lastfragment = frags.get(0);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                int tag = Integer.parseInt(radioButton.getTag().toString());

                if(!frags.get(tag).isAdded()){
                    manager.beginTransaction().add(R.id.fl,frags.get(tag)).commit();
                }else{
                    manager.beginTransaction().show(frags.get(tag)).commit();
                }
                manager.beginTransaction().hide(lastfragment).commit();
                lastfragment = frags.get(tag);
            }
        });

    }

    private void initFragments() {
        frags.add(new Fragment_new());
        frags.add(new Fragment_xiaohua());
        frags.add(new Fragment_03());
        frags.add(new Fragment_04());
    }

    private void initView() {
        group = (RadioGroup) findViewById(R.id.group);
        fl = (FrameLayout) findViewById(R.id.fl);
    }

    @Override
    public void onBackPressed() {
        long current = System.currentTimeMillis();
        long durtion = current-time;
        if(durtion<=1000){
            finish();
        }else{
            Toast.makeText(ActivityTwo.this, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        time = current;
    }
}

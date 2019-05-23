package com.gzeinnumer.class_koding.helper.slidermateri;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

//done

public class SliderPagerAdapterMateri extends FragmentStatePagerAdapter {

//    private static final String TAG = "SliderPagerAdapter";

    private List<Fragment> mFrags;
    private List<Fragment> mFrags2;

    public SliderPagerAdapterMateri(FragmentManager fm, List<Fragment> frags, List<Fragment> frags2) {
        super(fm);
        mFrags = frags;
        mFrags2 = frags2;
    }

    @Override
    public Fragment getItem(int position) {
        int index = position % mFrags.size();
        return FragmentSliderMateri.newInstance(mFrags.get(index).getArguments().getString("params"), mFrags2.get(index).getArguments().getString("params2"));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

}
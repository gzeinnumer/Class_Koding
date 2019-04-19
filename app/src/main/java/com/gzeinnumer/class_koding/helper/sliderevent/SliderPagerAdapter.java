package com.gzeinnumer.class_koding.helper.sliderevent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by bagicode on 12/04/17.
 */

public class SliderPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "SliderPagerAdapterMateri";

    private List<Fragment> mFrags;
    private List<Fragment> mFrags2;

    public SliderPagerAdapter(FragmentManager fm, List<Fragment> frags, List<Fragment> frags2) {
        super(fm);
        mFrags = frags;
        mFrags2 = frags2;
    }

    @Override
    public Fragment getItem(int position) {
        int index = position % mFrags.size();
        return FragmentSlider.newInstance(mFrags.get(index).getArguments().getString("params"), mFrags2.get(index).getArguments().getString("params2"));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

}
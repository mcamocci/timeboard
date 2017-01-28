package com.haikarose.time.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by root on 10/23/16.
 */

public class ViewPagerItemAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;
    List<String> fragmentNames;

    public ViewPagerItemAdapter(FragmentManager fragmentManager,List<Fragment> fragmentList, List<String> fragmentsNames){
        super(fragmentManager);
        this.fragments=fragmentList;
        this.fragmentNames=fragmentsNames;
    }
    @Override
    public int getCount() {
        return fragmentNames.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentNames.get(position);
    }
}


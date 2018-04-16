package com.example.osamaabdulrehman.procom18.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.osamaabdulrehman.procom18.Fragments.CompetitionsFragment;
import com.example.osamaabdulrehman.procom18.Fragments.MoreFragment;

/**
 * Created by Osama Abdul Rehman on 3/30/2018.
 */

public class HomeActivityPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public HomeActivityPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                CompetitionsFragment tab1 = new CompetitionsFragment();
                return tab1;

            case 1:
                MoreFragment tab2 = new MoreFragment();
                return tab2;
        }

        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

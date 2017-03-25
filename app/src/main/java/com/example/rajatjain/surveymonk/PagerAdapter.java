package com.example.rajatjain.surveymonk;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rajatjain.surveymonk.My_Created;
import com.example.rajatjain.surveymonk.Surveys;
//import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String[] tabTitles= new String[]{"Surveys","My Created"};

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Surveys tab1 = new Surveys();
                return tab1;
            case 1:
                My_Created tab2 = new My_Created();
                return tab2;
           /* case 2:
                TabFragment3 tab3 = new TabFragment3();
                return tab3;*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    /*@Override
    public int getCount() {
        return mNumOfTabs;
    }*/
}

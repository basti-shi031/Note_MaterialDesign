package basti.coryphaei.com.mdtest.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import basti.coryphaei.com.mdtest.fragment.AllFragment;
import basti.coryphaei.com.mdtest.fragment.SecondFragment;
import basti.coryphaei.com.mdtest.fragment.ThirdFragment;

/**
 * Created by Bowen on 2015/10/11.
 */
public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    private AllFragment allFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;

    public TabViewPagerAdapter(FragmentManager fm) {
        super(fm);
        allFragment = new AllFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return allFragment;
            case 1:return secondFragment;
            case 2:return thirdFragment;
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Item" + position;
    }
}

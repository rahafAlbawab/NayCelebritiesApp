package com.rahafmaria.naycelebrities.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rahafmaria.naycelebrities.Fragment.CareFragment;
import com.rahafmaria.naycelebrities.Fragment.MakeUpFragment;
import com.rahafmaria.naycelebrities.Fragment.PerfumeFragment;

public class HomeViewPagerAdpater extends FragmentPagerAdapter {
    public HomeViewPagerAdpater(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MakeUpFragment();
            case 1:
                return new CareFragment();
            case 2:
                return new PerfumeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "MakeUp";
            case 1:
                return "Care";
            case 2:
                return "Perfume";
        }
        return null;
    }
}

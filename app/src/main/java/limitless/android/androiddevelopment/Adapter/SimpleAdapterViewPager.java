package limitless.android.androiddevelopment.Adapter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimpleAdapterViewPager extends FragmentPagerAdapter {
    private String[] strings;
    private Fragment[] fragments;
    public SimpleAdapterViewPager(@NonNull FragmentManager fm, String[] strings, Fragment[] fragments) {
        super(fm);
        this.strings = strings;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle args;
        if (fragments[position].getArguments() == null)
            args = new Bundle();
        else
            args = fragments[position].getArguments();
        args.putString(Intent.EXTRA_TEXT, String.valueOf(position));
        fragments[position].setArguments(args);
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (strings != null){
            return strings[position];
        }
        return super.getPageTitle(position);
    }
}

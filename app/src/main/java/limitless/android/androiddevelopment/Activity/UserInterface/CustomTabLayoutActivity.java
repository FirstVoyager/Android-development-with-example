package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Fragment.SimpleTabLayoutFragment;
import limitless.android.androiddevelopment.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textview.MaterialTextView;

public class CustomTabLayoutActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CustomAdapter customAdapter;
    private String[] titles = new String[]{
            "Setting",
            "Notification",
            "About",
            "Trash",
            "Camera",
            "Contact",
            "Group"
    };
    int[] images = new int[]{
            R.drawable.ic_settings_outline_24dp,
            R.drawable.ic_notifications_black_24dp,
            R.drawable.ic_info_outline_black_24dp,
            R.drawable.trash_can_outline,
            R.drawable.ic_camera_alt_black_24dp,
            R.drawable.account_circle_outline,
            R.drawable.account_group_outline
    };
    Fragment[] fragments = new Fragment[]{
            new SimpleTabLayoutFragment(),
            new SimpleTabLayoutFragment(),
            new SimpleTabLayoutFragment(),
            new SimpleTabLayoutFragment(),
            new SimpleTabLayoutFragment(),
            new SimpleTabLayoutFragment(),
            new SimpleTabLayoutFragment()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab_layout);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        customAdapter = new CustomAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(customAdapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < fragments.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null){
                tab.setCustomView(customAdapter.getTabView(i, titles[i], images[i], this));
            }
        }
        tabLayout.addOnTabSelectedListener(tabListener);
        viewPager.setCurrentItem(1);
        viewPager.setCurrentItem(0);
    }

    private TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            ((MaterialCardView) tab.getCustomView().findViewById(R.id.cardView))
                    .setCardBackgroundColor(Color.RED);

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            ((MaterialCardView) tab.getCustomView().findViewById(R.id.cardView))
                    .setCardBackgroundColor(getResources().getColor(R.color.colorAccent));

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class CustomAdapter extends FragmentPagerAdapter{
        private Fragment[] fragments;
        public CustomAdapter(@NonNull FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putString(Intent.EXTRA_TEXT, String.valueOf(position));
            fragments[position].setArguments(args);
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        public View getTabView(int position, String title, int image, Context context){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
            MaterialTextView tv = view.findViewById(R.id.textView_title);
            if (title == null)
                tv.setVisibility(View.GONE);
            else
                tv.setText(title);
            AppCompatImageView iv = view.findViewById(R.id.imageView_icon);
            if (image <= 0)
                iv.setVisibility(View.GONE);
            else
                iv.setImageResource(image);
            return view;
        }

    }

}

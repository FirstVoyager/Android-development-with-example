package limitless.android.androiddevelopment.Activity.UserInterface.Tablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.SimpleAdapterViewPager;
import limitless.android.androiddevelopment.Fragment.SimpleTabLayoutFragment;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

public class SimpleTabLayoutActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_tablayout);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        switch (getIntent().getIntExtra(Intent.EXTRA_TEXT, 0)){
            case 0:
                simple();
                break;
            case 1:
                onlyIcon();
                break;
            case 2:
                scrollable();
                break;
            case 3:
                textIcon();
                break;
            case 4:
                badgeView();
                break;
        }
    }

    private void badgeView() {
        setTitle(getString(R.string.t_badges));
        Fragment[] fragments = new Fragment[]{
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment()
        };
        viewPager.setAdapter(new SimpleAdapterViewPager(getSupportFragmentManager(), null, fragments));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.account_circle_outline);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_settings_outline_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_info_outline_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_notifications_black_24dp);

        BadgeDrawable bad0 = tabLayout.getTabAt(0).getOrCreateBadge();
        bad0.setVisible(true);
        bad0.setNumber(25);

        BadgeDrawable bad1 = tabLayout.getTabAt(1).getOrCreateBadge();
        bad1.setVisible(true);
        bad1.setNumber(1002);

        BadgeDrawable bad2 = tabLayout.getTabAt(2).getOrCreateBadge();
        bad2.setVisible(true);
        bad2.setNumber(36);

        BadgeDrawable bad3 = tabLayout.getTabAt(3).getOrCreateBadge();
        bad3.setVisible(true);
        bad3.setNumber(4);
    }

    private void hideInScroll() {
        setTitle(getString(R.string.t_hide_in_scroll));
        Fragment[] fragments = new Fragment[]{
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment()
        };
        viewPager.setAdapter(new SimpleAdapterViewPager(getSupportFragmentManager(), null, fragments));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.account_circle_outline);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_settings_outline_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_info_outline_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_image_black_24dp);
    }

    private void textIcon() {
        setTitle(getString(R.string.t_text_icon));
        String[] titles = new String[]{
                "Contacts",
                "Setting",
                "About",
                "Images"
        };
        Fragment[] fragments = new Fragment[]{
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment()
        };
        viewPager.setAdapter(new SimpleAdapterViewPager(getSupportFragmentManager(), titles, fragments));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.account_circle_outline);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_settings_outline_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_info_outline_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_image_black_24dp);
    }

    private void scrollable() {
        setTitle(getString(R.string.t_scrollable));
        String[] titles = new String[]{
                "Java",
                "Kotlin",
                "Python",
                "C++",
                "C#",
                "PHP",
                "JavaScript",
                "Swift"
        };
        Fragment[] fragments = new Fragment[]{
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment()
        };
        viewPager.setAdapter(new SimpleAdapterViewPager(getSupportFragmentManager(), titles, fragments));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void onlyIcon() {
        setTitle(getString(R.string.t_only_icon));
        Fragment[] fragments = new Fragment[]{
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment()
        };
        viewPager.setAdapter(new SimpleAdapterViewPager(getSupportFragmentManager(), null, fragments));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.account_circle_outline);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_settings_outline_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_info_outline_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_image_black_24dp);
    }

    private void simple() {
        setTitle(getString(R.string.text_simple));
        String[] titles = new String[]{
                "Java",
                "Kotlin",
                "Python",
                "C++"
        };
        Fragment[] fragments = new Fragment[]{
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment()
        };
        viewPager.setAdapter(new SimpleAdapterViewPager(getSupportFragmentManager(), titles, fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

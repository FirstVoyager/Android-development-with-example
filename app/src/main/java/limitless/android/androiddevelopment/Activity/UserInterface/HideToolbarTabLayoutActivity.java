package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.SimpleAdapterViewPager;
import limitless.android.androiddevelopment.Fragment.SimpleTabLayoutFragment;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class HideToolbarTabLayoutActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_toolbar_tab_layout);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        String[] titles = new String[]{
                "Google",
                "Apple",
                "Microsoft",
                "Huawei",
                "Amazom",
                "Facebook",
                "Instagram",
                "SpaceX",
                "Paypal"
        };
        Fragment[] fragments = new Fragment[]{
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment(),
                new SimpleTabLayoutFragment()
        };
        for (Fragment fragment : fragments) {
            Bundle args = new Bundle();
            args.putInt(Intent.EXTRA_KEY_EVENT, 1);
            fragment.setArguments(args);
        }
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

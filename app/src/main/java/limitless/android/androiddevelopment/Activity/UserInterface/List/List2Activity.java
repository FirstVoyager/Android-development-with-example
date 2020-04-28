package limitless.android.androiddevelopment.Activity.UserInterface.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.AdapterPhoto;
import limitless.android.androiddevelopment.Adapter.AdapterSimpleText;
import limitless.android.androiddevelopment.Adapter.HorizontalListAdapter;
import limitless.android.androiddevelopment.Adapter.SimpleList2Adapter;
import limitless.android.androiddevelopment.Data.Data;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class List2Activity extends BaseActivity {

    private static int storagePermission = 2313;
    private static int itemCount = 20;

    private ListView listView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);

        switch (getIntent().getIntExtra(Intent.EXTRA_TEXT, 0)){
            case 0:
                list1();
                break;
            case 1:
                list2();
                break;
            case 2:
                simpleSwipe();
                break;
            case 3:
                simpleSwipeDelete();
                break;
            case 4:
                horizontal();
                break;
            case 5:
                gridLayout();
                break;
            case 6:
                staggeredLayout();
                break;
        }
    }


    private void staggeredLayout() {
        if (Tools.permissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            AdapterPhoto adapterPhoto = new AdapterPhoto(this, Data.getAllPhotos(this, MediaStore.Images.Media.DATE_ADDED + " DESC"), null);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(adapterPhoto);
            recyclerView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else {
            Tools.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, storagePermission);
        }
    }

    private void gridLayout() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add("Hello world " + i);
        }
        recyclerView.setAdapter(new AdapterSimpleText(this, strings));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        recyclerView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    private void horizontal() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Assistant", R.drawable.avatar_assistant);
        hashMap.put("Astronaut", R.drawable.avatar_astronaut);
        hashMap.put("Businessman", R.drawable.avatar_businessman);
        hashMap.put("Captain", R.drawable.avatar_captain);
        hashMap.put("Cashier", R.drawable.avatar_cashier);
        hashMap.put("Chef", R.drawable.avatar_chef);
        hashMap.put("Concierge", R.drawable.avatar_concierge);
        hashMap.put("Cooker", R.drawable.avatar_cooker);
        hashMap.put("Courier", R.drawable.avatar_courier);
        hashMap.put("Detective", R.drawable.avatar_detective);
        hashMap.put("Diver", R.drawable.avatar_diver);
        HorizontalListAdapter adapter = new HorizontalListAdapter(this, hashMap);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    private void simpleSwipeDelete() {
        listView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add("Swipe for delete " + i);
        }
        final SimpleList2Adapter list1Adapter = new SimpleList2Adapter(this, strings);
        recyclerView.setAdapter(list1Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                list1Adapter.deleteItem(viewHolder.getAdapterPosition());
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (viewHolder != null) {
                    final View foregroundView = ((SimpleList2Adapter.StringViewHolder) viewHolder).bFront;
                    getDefaultUIUtil().onSelected(foregroundView);
                }
            }

            @Override
            public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((SimpleList2Adapter.StringViewHolder) viewHolder).bFront;
                getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final View foregroundView = ((SimpleList2Adapter.StringViewHolder) viewHolder).bFront;
                getDefaultUIUtil().clearView(foregroundView);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (viewHolder != null){
                    final View foregroundView = ((SimpleList2Adapter.StringViewHolder) viewHolder).bFront;
                    getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    private void simpleSwipe() {
        listView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add("Swipe Right or Left " + i);
        }
        final SimpleList2Adapter list1Adapter = new SimpleList2Adapter(this, strings);
        recyclerView.setAdapter(list1Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback( 0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                list1Adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    private void list2() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add("Single Item " + i);
        }
        SimpleList2Adapter list1Adapter = new SimpleList2Adapter(this, strings);
        recyclerView.setAdapter(list1Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    private void list1() {
        final String[] items = new String[itemCount];
        for (int i = 0; i < itemCount; i++) {
            items[i] = "Item " + i;
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tools.toast(List2Activity.this, items[position]);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}

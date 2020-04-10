package limitless.android.androiddevelopment.Activity.CodeMore.GoogleMap;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Dialog.StyleMapDialog;
import limitless.android.androiddevelopment.Interface.Listener;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;

public class StyledMapActivity extends BaseActivity implements OnMapReadyCallback, View.OnClickListener {

    private int currentStyle = 0;
    private GoogleMap googleMap;
    private String[] styles = new String[]{
            "Standard",
            "Silver",
            "Retro",
            "Dark",
            "Night",
            "Aubergine"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styled_map);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.fab).setOnClickListener(this);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab){
            if (googleMap == null){
                Tools.toast(this, R.string.map_is_loading);
            }
            StyleMapDialog dialog = new StyleMapDialog(new Listener<Integer>() {
                @Override
                public void data(Integer integer) {
                    currentStyle = integer;
                    updateStyle();
                }
            });
            Tools.showDialog(getSupportFragmentManager(), dialog);
        }
    }

    private void updateStyle() {
        String style = null;
        switch (currentStyle){
            case 0:
                style = "[]";
                break;
            case 1:
                style = Tools.assetToString(getAssets(), "MapStyle/silver.json");
                break;
            case 2:
                style = Tools.assetToString(getAssets(), "MapStyle/retro.json");
                break;
            case 3:
                style = Tools.assetToString(getAssets(), "MapStyle/dark.json");
                break;
            case 4:
                style = Tools.assetToString(getAssets(), "MapStyle/night.json");
                break;
            case 5:
                style = Tools.assetToString(getAssets(), "MapStyle/aubergine.json");
                break;

        }
        googleMap.setMapStyle(new MapStyleOptions(style));
    }
}

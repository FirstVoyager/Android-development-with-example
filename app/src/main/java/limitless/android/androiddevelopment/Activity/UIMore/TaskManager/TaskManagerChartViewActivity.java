package limitless.android.androiddevelopment.Activity.UIMore.TaskManager;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class TaskManagerChartViewActivity extends BaseActivity implements View.OnClickListener {

    private AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager_chart_view);
        inti();
    }

    private void inti() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Tools.setSystemBarLight(this);
        findViewById(R.id.linearLayout_1).setOnClickListener(this);
        findViewById(R.id.linearLayout_2).setOnClickListener(this);
        findViewById(R.id.linearLayout_3).setOnClickListener(this);
        findViewById(R.id.linearLayout_4).setOnClickListener(this);

        anyChartView = findViewById(R.id.anyChartView);
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        dataEntries.add(new ValueDataEntry("Travel", 20));
        dataEntries.add(new ValueDataEntry("Music", 30));
        dataEntries.add(new ValueDataEntry("Food", 10));
        dataEntries.add(new ValueDataEntry("Work", 60));
        pie.data(dataEntries);
        pie.title("Your tasks in chart view");
        pie.animation(true);
        anyChartView.setChart(pie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem setting = menu.add("Setting");
        setting.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        setting.setIcon(R.drawable.ic_settings_outline_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}

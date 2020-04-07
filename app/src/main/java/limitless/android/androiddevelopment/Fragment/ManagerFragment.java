package limitless.android.androiddevelopment.Fragment;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Adapter.AdapterSimpleText;
import limitless.android.androiddevelopment.R;

public class ManagerFragment extends Fragment {


    public ManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lifecycle, container, false);
        AppCompatTextView tv = view.findViewById(R.id.textView);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:SS", Locale.getDefault());
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        tv.setText(dateStr);
        view.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frameLayout, new ManagerFragment())
                        .addToBackStack("fuck" + new Random().nextInt(100)).commit();
            }
        });
        ((MaterialToolbar) view.findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

}

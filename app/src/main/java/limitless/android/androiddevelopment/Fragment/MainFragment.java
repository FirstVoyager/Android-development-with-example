package limitless.android.androiddevelopment.Fragment;


import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.MainActivity;
import limitless.android.androiddevelopment.Adapter.AdapterMain;
import limitless.android.androiddevelopment.Data.MainData;
import limitless.android.androiddevelopment.R;

public class MainFragment extends Fragment {

    public static String fragmentId = "fragmentId";
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_main);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getArguments() == null || getArguments().getInt(fragmentId, 0) < 0)
            return;
        switch (getArguments().getInt(fragmentId, 0)){
            case R.id.menu_main_basic:
                recyclerView.setAdapter(new AdapterMain(getContext(), MainData.components()));
                break;
            case R.id.menu_main_ui:
                recyclerView.setAdapter(new AdapterMain(getContext(), MainData.userInterface()));
                break;
            case R.id.menu_main_ui_more:
                recyclerView.setAdapter(new AdapterMain(getContext(), MainData.uiMore()));
                break;
            case R.id.menu_main_code_more:
                recyclerView.setAdapter(new AdapterMain(getContext(), MainData.codeMore()));
                break;
        }
    }

}

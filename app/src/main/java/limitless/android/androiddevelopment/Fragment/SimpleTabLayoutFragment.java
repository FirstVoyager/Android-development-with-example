package limitless.android.androiddevelopment.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Adapter.AdapterSimpleText;
import limitless.android.androiddevelopment.R;

public class SimpleTabLayoutFragment extends Fragment {

    private MaterialTextView textView;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_tab_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        textView = view.findViewById(R.id.textView);
        recyclerView = view.findViewById(R.id.recyclerView);
        if (getArguments().getInt(Intent.EXTRA_KEY_EVENT, 0) == 0){
            textView.setText("Tab : ");
            textView.append(getArguments().getString(Intent.EXTRA_TEXT));
            recyclerView.setVisibility(View.GONE);
        }else {
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                strings.add("This is simple text " + i);
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new AdapterSimpleText(getContext(), strings));
            textView.setVisibility(View.GONE);
        }
    }

}

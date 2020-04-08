package limitless.android.androiddevelopment.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Adapter.ProjectAdapter;
import limitless.android.androiddevelopment.Data.MainData;
import limitless.android.androiddevelopment.R;

public class ProjectsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private ProjectAdapter projectAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        projectAdapter = new ProjectAdapter(getContext(), MainData.projectList());

        recyclerView.setAdapter(projectAdapter);
    }


    @Override
    public String name() {
        return "ProjectsFragment";
    }
}

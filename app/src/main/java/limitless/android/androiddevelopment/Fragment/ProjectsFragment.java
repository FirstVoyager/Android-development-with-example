package limitless.android.androiddevelopment.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import limitless.android.androiddevelopment.R;

public class ProjectsFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

    }


    @Override
    public String name() {
        return "ProjectsFragment";
    }
}

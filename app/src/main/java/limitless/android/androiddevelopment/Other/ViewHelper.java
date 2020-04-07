package limitless.android.androiddevelopment.Other;

import android.view.ViewGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class ViewHelper {

    public static CoordinatorLayout.LayoutParams coorParams(){
        return new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

}

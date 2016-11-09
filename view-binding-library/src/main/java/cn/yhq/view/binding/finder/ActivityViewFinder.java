package cn.yhq.view.binding.finder;

import android.app.Activity;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public class ActivityViewFinder implements IViewFinder {
    private Activity activity;

    public ActivityViewFinder(Activity activity) {
        this.activity = activity;
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return (T) activity.findViewById(id);
    }

}

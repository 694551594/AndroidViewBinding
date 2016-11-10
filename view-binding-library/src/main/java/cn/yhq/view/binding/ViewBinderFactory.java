package cn.yhq.view.binding;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ViewBinderFactory {
    public static ViewBinder create(Activity activity) {
        return new ViewBinder(activity);
    }

    public static ViewBinder create(View view) {
        return new ViewBinder(view);
    }

    public static ViewBinder create(Context context, SparseArray<View> views) {
        return new ViewBinder(context, views);
    }
}

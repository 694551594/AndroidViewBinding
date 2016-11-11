package cn.yhq.view.binding;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class DataBinderFactory {
    public static DataBinder create(Activity activity) {
        return new DataBinder(activity);
    }

    public static DataBinder create(View view) {
        return new DataBinder(view);
    }

    public static DataBinder create(Context context, SparseArray<View> views) {
        return new DataBinder(context, views);
    }
}

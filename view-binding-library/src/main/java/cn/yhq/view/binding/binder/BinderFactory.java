package cn.yhq.view.binding.binder;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class BinderFactory {

    public static BinderProvider create(Activity activity) {
        return new BinderProvider(activity);
    }

    public static BinderProvider create(View view) {
        return new BinderProvider(view);
    }

    public static BinderProvider create(Context context, SparseArray<View> views) {
        return new BinderProvider(context, views);
    }

}

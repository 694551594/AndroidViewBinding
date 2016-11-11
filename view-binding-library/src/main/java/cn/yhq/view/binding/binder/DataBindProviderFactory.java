package cn.yhq.view.binding.binder;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class DataBindProviderFactory {

    public static DataBindProvider create(Activity activity) {
        return new DataBindProvider(activity);
    }

    public static DataBindProvider create(View view) {
        return new DataBindProvider(view);
    }

    public static DataBindProvider create(Context context, SparseArray<View> views) {
        return new DataBindProvider(context, views);
    }

}

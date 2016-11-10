package cn.yhq.view.binding.binder;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class BinderProvider {
    private ResBinder resBinder = new ResBinder();
    private ExpressBinder expressBinder = new ExpressBinder();
    private ValueBinder valueBinder = new ValueBinder();

    private final IBinder[] binders = {
            resBinder,
            expressBinder,
            valueBinder
    };

    BinderProvider(Activity activity) {
        resBinder.from(activity);
        expressBinder.from(activity);
        valueBinder.from(activity);
    }

    BinderProvider(View view) {
        resBinder.from(view);
        expressBinder.from(view);
        valueBinder.from(view);
    }

    BinderProvider(Context context, SparseArray<View> views) {
        resBinder.from(context, views);
        expressBinder.from(context, views);
        valueBinder.from(context, views);
    }

    public void bind(int id, BindType type, Object value) {
        for (IBinder binder : binders) {
            if (binder.isHandle(value)) {
                binder.onBind(id, type, value);
                return;
            }
        }
    }

    public void put(String name, Object data) {
        for (IBinder binder : binders) {
            binder.put(name, data);
        }
    }

}

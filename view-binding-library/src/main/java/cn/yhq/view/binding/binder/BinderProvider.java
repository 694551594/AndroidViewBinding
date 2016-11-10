package cn.yhq.view.binding.binder;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class BinderProvider {
    private final static IBinder[] binders = {
            new ResBinder(),
            new ExpressBinder(),
            new ValueBinder()
    };

    BinderProvider(Activity activity) {
        for (IBinder binder : binders) {
            binder.attach(activity, activity);
        }
    }

    BinderProvider(View view) {
        for (IBinder binder : binders) {
            binder.attach(view.getContext(), view);
        }
    }

    BinderProvider(Context context, SparseArray<View> views) {
        for (IBinder binder : binders) {
            binder.attach(context, views);
        }
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
            binder.onPut(name, data);
        }
    }

}

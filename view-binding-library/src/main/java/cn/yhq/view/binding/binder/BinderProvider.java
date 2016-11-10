package cn.yhq.view.binding.binder;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class BinderProvider {
    private final ViewRender viewRender = new ViewRender();
    private final IBinder[] binders = {
            new ResBinder(viewRender),
            new ExpressBinder(viewRender),
            new ValueBinder(viewRender)
    };

    BinderProvider(Activity activity) {
        viewRender.attach(activity, activity);
    }

    BinderProvider(View view) {
        viewRender.attach(view.getContext(), view);
    }

    BinderProvider(Context context, SparseArray<View> views) {
        viewRender.attach(context, views);
    }

    public ViewRender getViewRender() {
        return viewRender;
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

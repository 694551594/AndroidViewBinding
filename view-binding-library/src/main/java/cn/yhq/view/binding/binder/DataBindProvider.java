package cn.yhq.view.binding.binder;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class DataBindProvider {
    private final ViewRender viewRender = new ViewRender();
    private final IDataBinder[] binders = {
            new ResBinder(viewRender),
            new ExpressBinder(viewRender),
            new ValueBinder(viewRender)
    };

    DataBindProvider(Activity activity) {
        viewRender.attach(activity, activity);
    }

    DataBindProvider(View view) {
        viewRender.attach(view.getContext(), view);
    }

    DataBindProvider(Context context, SparseArray<View> views) {
        viewRender.attach(context, views);
    }

    public ViewRender getViewRender() {
        return viewRender;
    }

    public void bind(int id, BindType type, Object value) {
        for (IDataBinder binder : binders) {
            if (binder.isHandle(value)) {
                binder.onBind(id, type, value);
                return;
            }
        }
    }

    public Object put(String name, Object data) {
        for (IDataBinder binder : binders) {
            data = binder.onPut(name, data);
        }
        return data;
    }

}

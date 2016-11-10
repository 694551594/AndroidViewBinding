package cn.yhq.view.binding.binder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;

import java.io.File;

import cn.yhq.view.binding.provider.ViewFactory;
import cn.yhq.view.binding.finder.ActivityViewFinder;
import cn.yhq.view.binding.finder.ViewFinder;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class BaseBinder {
    private Context context;
    private ViewFactory viewFactory;

    protected void from(Activity activity) {
        this.context = activity;
        viewFactory = new ViewFactory(new ActivityViewFinder(activity));
    }

    protected void from(View view) {
        this.context = view.getContext();
        viewFactory = new ViewFactory(new ViewFinder(view));
    }

    protected void from(Context context, SparseArray<View> views) {
        this.context = context;
        viewFactory = new ViewFactory(views, null);
    }

    public void put(String name, Object data) {
    }

    public Context getContext() {
        return context;
    }

    public <T extends View> T getView(int id) {
        return viewFactory.getView(id);
    }

    public void setVisibility(int id, int visibility) {
        ViewFactory.setVisibility(getView(id), visibility);
    }

    public void setText(int id, CharSequence data) {
        ViewFactory.setText(getView(id), data);
    }

    public void setText(int id, int resId) {
        ViewFactory.setText(getView(id), resId);
    }

    public void setCheck(int id, boolean checked) {
        ViewFactory.setCheck(getView(id), checked);
    }

    public void setImage(int id, String url) {
        ViewFactory.setImage(getView(id), url);
    }

    public void setImage(int id, Bitmap bitmap) {
        ViewFactory.setImage(getView(id), bitmap);
    }

    public void setImage(int id, Drawable drawable) {
        ViewFactory.setImage(getView(id), drawable);
    }

    public void setImage(int id, int resId) {
        ViewFactory.setImage(getView(id), resId);
    }

    public void setImage(int id, File file) {
        ViewFactory.setImage(getView(id), file);
    }

}

package cn.yhq.view.binding;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;

import java.io.File;

import cn.yhq.view.binding.finder.ActivityViewFinder;
import cn.yhq.view.binding.finder.ViewFinder;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public class ViewBinder<T> implements IViewBinder<T> {
    private ViewFactory viewFactory;
    private T data;
    private IViewBinder<T> viewBinder;

    public ViewBinder<T> bind(T data) {
        bind(data, null);
        return this;
    }

    public ViewBinder<T> bind(T data, IViewBinder<T> viewBinder) {
        this.viewBinder = viewBinder;
        this.data = data;
        if (this.data instanceof IPropertyChanged) {
            ((IPropertyChanged<T>)data).setViewBinder(this);
        }
        this.refresh();
        return this;
    }

    @Override
    public void onBinding(ViewBinder<T> viewBinder, T data) {
        if (this.viewBinder != null) {
            this.viewBinder.onBinding(viewBinder, data);
        }
    }

    public void refresh() {
        this.onBinding(this, data);
    }

    public ViewBinder(Activity activity) {
        viewFactory = new ViewFactory(new ActivityViewFinder(activity));
    }

    public ViewBinder(View view) {
        viewFactory = new ViewFactory(new ViewFinder(view));
    }

    public ViewBinder(SparseArray<View> views) {
        viewFactory = new ViewFactory(views, null);
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

package cn.yhq.view.binding.binder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import java.io.File;

import cn.yhq.view.binding.adapter.ViewBindAdapterFactory;
import cn.yhq.view.binding.finder.ActivityViewFinder;
import cn.yhq.view.binding.finder.ViewFinder;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ViewRender {
    private Context context;
    private ViewBindAdapterFactory viewFactory;

    public void attach(Context context, Object target) {
        if (target instanceof Activity) {
            attach1((Activity) target);
        } else if (target instanceof android.view.View) {
            attach2((android.view.View) target);
        } else {
            attach3(context, (SparseArray<android.view.View>) target);
        }
    }

    private void attach1(Activity activity) {
        this.context = activity;
        viewFactory = new ViewBindAdapterFactory(new ActivityViewFinder(activity));
    }

    private void attach2(android.view.View view) {
        this.context = view.getContext();
        viewFactory = new ViewBindAdapterFactory(new ViewFinder(view));
    }

    private void attach3(Context context, SparseArray<android.view.View> views) {
        this.context = context;
        viewFactory = new ViewBindAdapterFactory(views, null);
    }

    public Context getContext() {
        return context;
    }

    public <T extends android.view.View> T getView(int id) {
        return viewFactory.getView(id);
    }

    public void setVisibility(int id, int visibility) {
        ViewBindAdapterFactory.setVisibility(getView(id), visibility);
    }

    public void setText(int id, CharSequence data) {
        ViewBindAdapterFactory.setText(getView(id), data);
    }

    public void setText(int id, int resId) {
        ViewBindAdapterFactory.setText(getView(id), resId);
    }

    public void setCheck(int id, boolean checked) {
        ViewBindAdapterFactory.setCheck(getView(id), checked);
    }

    public void setImage(int id, String url) {
        ViewBindAdapterFactory.setImage(getView(id), url);
    }

    public void setImage(int id, Bitmap bitmap) {
        ViewBindAdapterFactory.setImage(getView(id), bitmap);
    }

    public void setImage(int id, Drawable drawable) {
        ViewBindAdapterFactory.setImage(getView(id), drawable);
    }

    public void setImage(int id, int resId) {
        ViewBindAdapterFactory.setImage(getView(id), resId);
    }

    public void setImage(int id, File file) {
        ViewBindAdapterFactory.setImage(getView(id), file);
    }

    public void setOnItemLongClickListener(int id, AdapterView.OnItemLongClickListener listener) {
        ViewBindAdapterFactory.setOnItemLongClickListener(getView(id), listener);
    }

    public void setOnItemClickListener(int id, AdapterView.OnItemClickListener listener) {
        ViewBindAdapterFactory.setOnItemClickListener(getView(id), listener);
    }

    public void setOnLongClickListener(int id, View.OnLongClickListener listener) {
        ViewBindAdapterFactory.setOnLongClickListener(getView(id), listener);
    }

    public void setOnClickListener(int id, View.OnClickListener listener) {
        ViewBindAdapterFactory.setOnClickListener(getView(id), listener);
    }

    public void setTag(int id, Object tag) {
        ViewBindAdapterFactory.setTag(getView(id), tag);
    }

    public void setTag(int id, int key, Object tag) {
        ViewBindAdapterFactory.setTag(getView(id), key, tag);
    }

    public void setOnCheckedChangeListener(int id, CompoundButton.OnCheckedChangeListener listener) {
        ViewBindAdapterFactory.setOnCheckedChangeListener(getView(id), listener);
    }
}

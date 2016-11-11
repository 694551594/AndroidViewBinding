package cn.yhq.view.binding.adapter.impl;

import android.view.View;

import cn.yhq.view.binding.adapter.IViewBindAdapter;

/**
 * Created by Yanghuiqiang on 2016/11/11.
 */

public class ViewBindAdapter implements IViewBindAdapter {

    @Override
    public void setTag(View v, Object tag) {
        v.setTag(tag);
    }

    @Override
    public void setTag(View v, int key, Object tag) {
        v.setTag(key, tag);
    }

    @Override
    public void setOnClickListener(View v, View.OnClickListener listener) {
        v.setOnClickListener(listener);
    }

    @Override
    public void setOnLongClickListener(View v, View.OnLongClickListener listener) {
        v.setOnLongClickListener(listener);
    }

    @Override
    public void setVisibility(View v, int visibility) {
        v.setVisibility(visibility);
    }
}

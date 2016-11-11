package cn.yhq.view.binding.adapter;

import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/11.
 */

public interface IViewBindAdapter {
    void setTag(View v, Object tag);
    void setTag(View v, int key, Object tag);
    void setOnClickListener(View v, View.OnClickListener listener);
    void setOnLongClickListener(View v, View.OnLongClickListener listener);
    void setVisibility(View v, int visibility);
}

package cn.yhq.view.binding.adapter.impl;

import android.view.View;
import android.widget.AdapterView;

import cn.yhq.view.binding.adapter.IAdapterViewBindAdapter;

/**
 * Created by Yanghuiqiang on 2016/11/11.
 */

public class AdapterViewBindAdapter extends ViewBindAdapter implements IAdapterViewBindAdapter {

    @Override
    public void setOnItemClickListener(View v, AdapterView.OnItemClickListener listener) {
        AdapterView<?> adapterView = (AdapterView<?>) v;
        adapterView.setOnItemClickListener(listener);
    }

    @Override
    public void setOnItemLongClickListener(View v, AdapterView.OnItemLongClickListener listener) {
        AdapterView<?> adapterView = (AdapterView<?>) v;
        adapterView.setOnItemLongClickListener(listener);
    }
}

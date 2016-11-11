package cn.yhq.view.binding.adapter;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Yanghuiqiang on 2016/11/11.
 */

public interface IAdapterViewBindAdapter {
    void setOnItemClickListener(View v, AdapterView.OnItemClickListener listener);
    void setOnItemLongClickListener(View v, AdapterView.OnItemLongClickListener listener);
}

package cn.yhq.view.binding.adapter;

import android.view.View;
import android.widget.CompoundButton;

public interface ICheckBoxBindAdapter {
  void setChecked(View v, boolean checked);
  void setOnCheckedChangeListener(View v, CompoundButton.OnCheckedChangeListener listener);
}

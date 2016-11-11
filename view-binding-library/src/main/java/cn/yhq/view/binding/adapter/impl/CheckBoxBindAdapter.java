package cn.yhq.view.binding.adapter.impl;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import cn.yhq.view.binding.adapter.ICheckBoxBindAdapter;

public class CheckBoxBindAdapter extends ViewBindAdapter implements ICheckBoxBindAdapter {

  @Override
  public void setChecked(View v, boolean checked) {
    CheckBox cb = (CheckBox) v;
    cb.setChecked(checked);

  }

  @Override
  public void setOnCheckedChangeListener(View v, CompoundButton.OnCheckedChangeListener listener) {
    CheckBox cb = (CheckBox) v;
    cb.setOnCheckedChangeListener(listener);
  }

}

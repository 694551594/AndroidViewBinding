package cn.yhq.view.binding.provider.impl;

import android.view.View;
import android.widget.CheckBox;

import cn.yhq.view.binding.provider.ICheckBoxBinding;

public class CheckBoxBinding implements ICheckBoxBinding {

  @Override
  public void setChecked(View v, boolean checked) {
    CheckBox cb = (CheckBox) v;
    cb.setChecked(checked);

  }

}

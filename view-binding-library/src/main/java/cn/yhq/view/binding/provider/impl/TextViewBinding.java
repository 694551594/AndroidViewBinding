package cn.yhq.view.binding.provider.impl;

import android.view.View;
import android.widget.TextView;

import cn.yhq.view.binding.provider.ITextViewBinding;

public class TextViewBinding implements ITextViewBinding {

  @Override
  public void setText(View v, CharSequence text) {
    TextView tv = (TextView) v;
    tv.setText(text);
  }

  @Override
  public void setText(View v, int resId) {
    TextView tv = (TextView) v;
    tv.setText(resId);
  }

}

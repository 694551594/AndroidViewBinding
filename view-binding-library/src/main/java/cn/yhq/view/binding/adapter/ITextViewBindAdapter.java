package cn.yhq.view.binding.adapter;

import android.view.View;

public interface ITextViewBindAdapter {
  void setText(View v, CharSequence text);

  void setText(View v, int resId);
}

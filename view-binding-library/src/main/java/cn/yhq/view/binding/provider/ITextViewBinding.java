package cn.yhq.view.binding.provider;

import android.view.View;

public interface ITextViewBinding {
  void setText(View v, CharSequence text);

  void setText(View v, int resId);
}

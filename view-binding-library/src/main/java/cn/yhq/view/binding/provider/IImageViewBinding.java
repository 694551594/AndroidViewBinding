package cn.yhq.view.binding.provider;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.File;

public interface IImageViewBinding {
  void setImage(View v, String url);

  void setImage(View v, Bitmap bitmap);

  void setImage(View v, Drawable drawable);

  void setImage(View v, int resId);

  void setImage(View v, File file);
}

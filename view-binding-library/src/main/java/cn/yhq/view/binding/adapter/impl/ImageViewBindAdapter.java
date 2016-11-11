package cn.yhq.view.binding.adapter.impl;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import cn.yhq.view.binding.adapter.IImageViewBindAdapter;

public class ImageViewBindAdapter extends ViewBindAdapter implements IImageViewBindAdapter {

  @Override
  public void setImage(View v, String url) {

  }

  @Override
  public void setImage(View v, Bitmap bitmap) {
    ImageView iv = (ImageView) v;
    iv.setImageBitmap(bitmap);
  }

  @Override
  public void setImage(View v, Drawable drawable) {
    ImageView iv = (ImageView) v;
    iv.setImageDrawable(drawable);
  }

  @Override
  public void setImage(View v, int resId) {
    ImageView iv = (ImageView) v;
    iv.setImageResource(resId);
  }

  @Override
  public void setImage(View v, File file) {
    // TODO Auto-generated method stub

  }

}

package cn.yhq.view.binding;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.yhq.view.binding.provider.ICheckBoxBinding;
import cn.yhq.view.binding.provider.IImageViewBinding;
import cn.yhq.view.binding.provider.ITextViewBinding;
import cn.yhq.view.binding.provider.impl.CheckBoxBinding;
import cn.yhq.view.binding.provider.impl.ImageViewBinding;
import cn.yhq.view.binding.provider.impl.TextViewBinding;


public final class ViewBinder {
  private static Map<Class<?>, ITextViewBinding> mTextViewBindings = new HashMap<>();
  private static Map<Class<?>, IImageViewBinding> mImageViewBindings = new HashMap<>();
  private static Map<Class<?>, ICheckBoxBinding> mCheckBoxBindings = new HashMap<>();

  static {
    register(CheckBox.class, new CheckBoxBinding());
    register(TextView.class, new TextViewBinding());
    register(Button.class, new TextViewBinding());
    register(EditText.class, new TextViewBinding());
    register(ImageView.class, new ImageViewBinding());
  }

  @SuppressWarnings("unchecked")
  public static <T extends View> T findViewById(View v, int id) {
    return (T) v.findViewById(id);
  }

  @SuppressWarnings("unchecked")
  public static <T extends View> T findViewById(Activity activity, int id) {
    return (T) activity.findViewById(id);
  }

  public static void setVisibility(View v, int visibility) {
    v.setVisibility(visibility);
  }

  public static void register(Class<?> viewClass, ITextViewBinding binding) {
    mTextViewBindings.put(viewClass, binding);
  }

  public static void register(Class<?> viewClass, IImageViewBinding provider) {
    mImageViewBindings.put(viewClass, provider);
  }

  public static void register(Class<?> viewClass, ICheckBoxBinding provider) {
    mCheckBoxBindings.put(viewClass, provider);
  }

  public static void bindTextData(View v, CharSequence data) {
    ITextViewBinding provider = mTextViewBindings.get(v.getClass());
    if (provider == null) {
      return;
    }
    provider.setText(v, data);
  }

  public static void bindTextData(View v, int resId) {
    ITextViewBinding provider = mTextViewBindings.get(v.getClass());
    if (provider == null) {
      return;
    }
    provider.setText(v, resId);
  }

  public static void bindCheckData(View v, boolean checked) {
    ICheckBoxBinding provider = mCheckBoxBindings.get(v.getClass());
    if (provider == null) {
      return;
    }
    provider.setChecked(v, checked);
  }

  public static void bindImageData(View v, String url) {
    IImageViewBinding provider = mImageViewBindings.get(v.getClass());
    if (provider == null) {
      return;
    }
    provider.setImage(v, url);
  }

  public static void bindImageData(View v, Bitmap bitmap) {
    IImageViewBinding provider = mImageViewBindings.get(v.getClass());
    if (provider == null) {
      return;
    }
    provider.setImage(v, bitmap);
  }

  public static void bindImageData(View v, Drawable drawable) {
    IImageViewBinding provider = mImageViewBindings.get(v.getClass());
    if (provider == null) {
      return;
    }
    provider.setImage(v, drawable);
  }

  public static void bindImageData(View v, int resId) {
    IImageViewBinding provider = mImageViewBindings.get(v.getClass());
    if (provider == null) {
      return;
    }
    provider.setImage(v, resId);
  }

  public static void bindImageData(View v, File file) {
    IImageViewBinding provider = mImageViewBindings.get(v.getClass());
    if (provider == null) {
      return;
    }
    provider.setImage(v, file);
  }

  public static void bindTextData(View v, int resId, CharSequence data) {
    bindTextData(v.findViewById(resId), data);
  }

  public static void bindTextData(View v, int resId, int data) {
    bindTextData(v.findViewById(resId), data);
  }

  public static void bindCheckData(View v, int resId, boolean checked) {
    bindCheckData(v.findViewById(resId), checked);
  }

  public static void bindImageData(View v, int resId, String url) {
    bindImageData(v.findViewById(resId), url);
  }

  public static void bindImageData(View v, int resId, Bitmap bitmap) {
    bindImageData(v.findViewById(resId), bitmap);
  }

  public static void bindImageData(View v, int resId, Drawable drawable) {
    bindImageData(v.findViewById(resId), drawable);
  }

  public static void bindImageData(View v, int resId, int data) {
    bindImageData(v.findViewById(resId), data);
  }

  public static void bindImageData(View v, int resId, File file) {
    bindImageData(v.findViewById(resId), file);
  }
}

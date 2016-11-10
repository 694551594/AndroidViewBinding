package cn.yhq.view.binding.provider;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.yhq.view.binding.finder.IViewFinder;
import cn.yhq.view.binding.provider.impl.ButtonBinding;
import cn.yhq.view.binding.provider.impl.CheckBoxBinding;
import cn.yhq.view.binding.provider.impl.ImageViewBinding;
import cn.yhq.view.binding.provider.impl.TextViewBinding;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public class ViewFactory {
    private static Map<Class<?>, ITextViewBinding> mTextViewBindings = new HashMap<>();
    private static Map<Class<?>, IImageViewBinding> mImageViewBindings = new HashMap<>();
    private static Map<Class<?>, ICheckBoxBinding> mCheckBoxBindings = new HashMap<>();

    static {
        register(CheckBox.class, new CheckBoxBinding());
        register(TextView.class, new TextViewBinding());
        register(Button.class, new TextViewBinding());
        register(EditText.class, new TextViewBinding());
        register(ImageView.class, new ImageViewBinding());
        register(Button.class, new ButtonBinding());

        register(AppCompatCheckBox.class, new CheckBoxBinding());
        register(AppCompatTextView.class, new TextViewBinding());
        register(AppCompatButton.class, new TextViewBinding());
        register(AppCompatEditText.class, new TextViewBinding());
        register(AppCompatImageView.class, new ImageViewBinding());
        register(AppCompatButton.class, new ButtonBinding());
    }

    private SparseArray<View> views = new SparseArray<>();
    private IViewFinder viewFinder;

    public ViewFactory(IViewFinder viewFinder) {
        this.viewFinder = viewFinder;
    }

    public ViewFactory(SparseArray<View> views, IViewFinder viewFinder) {
        this.views = views;
        this.viewFinder = viewFinder;
    }

    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            if (this.viewFinder != null) {
                view = viewFinder.findViewById(id);
                if (view != null) {
                    views.put(view.getId(), view);
                }
            }
        }
        return (T) view;
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

    public static void setVisibility(View v, int visibility) {
        v.setVisibility(visibility);
    }

    public static void setText(View v, CharSequence data) {
        ITextViewBinding provider = mTextViewBindings.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setText(v, data);
    }

    public static void setText(View v, int resId) {
        ITextViewBinding provider = mTextViewBindings.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setText(v, resId);
    }

    public static void setCheck(View v, boolean checked) {
        ICheckBoxBinding provider = mCheckBoxBindings.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setChecked(v, checked);
    }

    public static void setImage(View v, String url) {
        IImageViewBinding provider = mImageViewBindings.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, url);
    }

    public static void setImage(View v, Bitmap bitmap) {
        IImageViewBinding provider = mImageViewBindings.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, bitmap);
    }

    public static void setImage(View v, Drawable drawable) {
        IImageViewBinding provider = mImageViewBindings.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, drawable);
    }

    public static void setImage(View v, int resId) {
        IImageViewBinding provider = mImageViewBindings.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, resId);
    }

    public static void setImage(View v, File file) {
        IImageViewBinding provider = mImageViewBindings.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, file);
    }
}

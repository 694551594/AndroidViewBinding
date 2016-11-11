package cn.yhq.view.binding.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.yhq.view.binding.adapter.impl.AdapterViewBindAdapter;
import cn.yhq.view.binding.adapter.impl.CheckBoxBindAdapter;
import cn.yhq.view.binding.adapter.impl.ImageViewBindAdapter;
import cn.yhq.view.binding.adapter.impl.TextViewBindAdapter;
import cn.yhq.view.binding.finder.IViewFinder;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public class ViewBindAdapterFactory {
    private static Map mViewBindAdapter = new HashMap<>();

    static {
        register(new CheckBoxBindAdapter(), new Class[]{
                CheckBox.class,
                AppCompatCheckBox.class
        });
        register(new TextViewBindAdapter(), new Class[]{
                TextView.class,
                AppCompatTextView.class,
                Button.class,
                AppCompatButton.class,
                EditText.class,
                AppCompatEditText.class
        });
        register(new ImageViewBindAdapter(), new Class[]{
                ImageView.class,
                AppCompatImageView.class
        });
        register(new AdapterViewBindAdapter(), new Class[]{
                ListView.class,
                android.support.v7.widget.ListViewCompat.class,
                android.support.v4.widget.ListViewCompat.class,
                ExpandableListView.class,
                GridView.class,
                Spinner.class,
                AppCompatSpinner.class
        });
    }

    private SparseArray<View> views = new SparseArray<>();
    private IViewFinder viewFinder;

    public ViewBindAdapterFactory(IViewFinder viewFinder) {
        this.viewFinder = viewFinder;
    }

    public ViewBindAdapterFactory(SparseArray<View> views, IViewFinder viewFinder) {
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

    public static <V extends View, T extends IViewBindAdapter>
    void register(T viewBindAdapter, Class<T>[] viewClass) {
        for (Class<T> clazz : viewClass) {
            mViewBindAdapter.put(clazz, viewBindAdapter);
        }
    }

    public static void setVisibility(View v, int visibility) {
        IViewBindAdapter provider = (IViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setVisibility(v, visibility);
    }

    public static void setText(View v, CharSequence data) {
        ITextViewBindAdapter provider = (ITextViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setText(v, data);
    }

    public static void setText(View v, int resId) {
        ITextViewBindAdapter provider = (ITextViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setText(v, resId);
    }

    public static void setCheck(View v, boolean checked) {
        ICheckBoxBindAdapter provider = (ICheckBoxBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setChecked(v, checked);
    }

    public static void setImage(View v, String url) {
        IImageViewBindAdapter provider = (IImageViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, url);
    }

    public static void setImage(View v, Bitmap bitmap) {
        IImageViewBindAdapter provider = (IImageViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, bitmap);
    }

    public static void setImage(View v, Drawable drawable) {
        IImageViewBindAdapter provider = (IImageViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, drawable);
    }

    public static void setImage(View v, int resId) {
        IImageViewBindAdapter provider = (IImageViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, resId);
    }

    public static void setImage(View v, File file) {
        IImageViewBindAdapter provider = (IImageViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setImage(v, file);
    }

    public static void setOnItemLongClickListener(View v, AdapterView.OnItemLongClickListener onItemLongClickListener) {
        IAdapterViewBindAdapter provider = (IAdapterViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setOnItemLongClickListener(v, onItemLongClickListener);
    }

    public static void setOnItemClickListener(View v, AdapterView.OnItemClickListener onItemClickListener) {
        IAdapterViewBindAdapter provider = (IAdapterViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setOnItemClickListener(v, onItemClickListener);
    }

    public static void setOnLongClickListener(View v, View.OnLongClickListener onLongClickListener) {
        IViewBindAdapter provider = (IViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setOnLongClickListener(v, onLongClickListener);
    }

    public static void setOnClickListener(View v, View.OnClickListener onClickListener) {
        IViewBindAdapter provider = (IViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setOnClickListener(v, onClickListener);
    }

    public static void setTag(View v, Object tag) {
        IViewBindAdapter provider = (IViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setTag(v, tag);
    }

    public static void setTag(View v, int key, Object tag) {
        IViewBindAdapter provider = (IViewBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setTag(v, key, tag);
    }

    public static void setOnCheckedChangeListener(View v, CompoundButton.OnCheckedChangeListener listener) {
        ICheckBoxBindAdapter provider = (ICheckBoxBindAdapter) mViewBindAdapter.get(v.getClass());
        if (provider == null) {
            return;
        }
        provider.setOnCheckedChangeListener(v, listener);
    }

}

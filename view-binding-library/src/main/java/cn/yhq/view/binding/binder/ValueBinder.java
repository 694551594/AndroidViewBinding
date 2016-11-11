package cn.yhq.view.binding.binder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import java.io.File;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ValueBinder extends BaseDataBinder implements IDataBinder {

    public ValueBinder(ViewRender viewRender) {
        super(viewRender);
    }

    @Override
    public void onBind(int id, BindType type, Object value) {
        switch (type) {
            case TEXT:
                viewRender.setText(id, value.toString());
                break;
            case CHECKED:
                viewRender.setCheck(id, (Boolean) value);
                break;
            case IMAGE_FILE:
                viewRender.setImage(id, (File) value);
                break;
            case IMAGE_DRAWABLE:
                viewRender.setImage(id, (Drawable) value);
                break;
            case IMAGE_BITMAP:
                viewRender.setImage(id, (Bitmap) value);
                break;
            case TAG:
                viewRender.setTag(id, value);
                break;
            case LISTENER_CLICK:
                viewRender.setOnClickListener(id, (View.OnClickListener) value);
                break;
            case LISTENER_LONG_CLICK:
                viewRender.setOnLongClickListener(id, (View.OnLongClickListener) value);
                break;
            case LISTENER_ITEM_CLICK:
                viewRender.setOnItemClickListener(id, (AdapterView.OnItemClickListener) value);
                break;
            case LISTENER_ITEM_LONG_CLICK:
                viewRender.setOnItemLongClickListener(id, (AdapterView.OnItemLongClickListener) value);
                break;
            case LISTENER_CHECKED_CHANGE:
                viewRender.setOnCheckedChangeListener(id, (CompoundButton.OnCheckedChangeListener) value);
                break;
            case VISIBILITY:
                viewRender.setVisibility(id, (Integer) value);
                break;
            case IMAGE_URL:
                viewRender.setImage(id, value.toString());
                break;
            case IMAGE_RESID:
                viewRender.setImage(id, (Integer) value);
                break;
        }
    }

    @Override
    public Object onPut(String name, Object data) {
        return data;
    }

    @Override
    public boolean isHandle(Object value) {
        return true;
    }
}

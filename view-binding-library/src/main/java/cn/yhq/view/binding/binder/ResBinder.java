package cn.yhq.view.binding.binder;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ResBinder extends BaseDataBinder implements IDataBinder {

    public ResBinder(ViewRender viewRender) {
        super(viewRender);
    }

    private int getResId(Object value) {
        if (value instanceof Integer) {
            return (int) value;
        } else {
            try {
                Resources res = viewRender.getContext().getResources();
                String packageName = viewRender.getContext().getPackageName();
                return res.getIdentifier(packageName + value.toString().replace("@", ":"), null, null);
            } catch (Exception e) {
                return 0;
            }
        }
    }

    private boolean getBoolean(int id) {
        return viewRender.getContext().getResources().getBoolean(id);
    }

    private String getString(int id) {
        return viewRender.getContext().getResources().getString(id);
    }

    private Drawable getDrawable(int id) {
        return viewRender.getContext().getResources().getDrawable(id);
    }

    private int getInt(int id) {
        return viewRender.getContext().getResources().getInteger(id);
    }

    @Override
    public void onBind(int id, BindType type, Object value) {
        int resId = getResId(value);
        switch (type) {
            case TEXT:
                viewRender.setText(id, resId);
                break;
            case CHECKED:
                viewRender.setCheck(id, this.getBoolean(resId));
                break;
            case IMAGE_FILE:
                break;
            case IMAGE_DRAWABLE:
                viewRender.setImage(id, this.getDrawable(resId));
                break;
            case IMAGE_BITMAP:
                break;
            case TAG:
                break;
            case LISTENER_CLICK:
                break;
            case LISTENER_LONG_CLICK:
                break;
            case LISTENER_ITEM_CLICK:
                break;
            case LISTENER_ITEM_LONG_CLICK:
                break;
            case LISTENER_CHECKED_CHANGE:
                break;
            case VISIBILITY:
                viewRender.setVisibility(id, this.getInt(resId));
                break;
            case IMAGE_URL:
                viewRender.setImage(id, this.getString(resId));
                break;
            case IMAGE_RESID:
                viewRender.setImage(id, resId);
                break;
        }
    }

    @Override
    public void onPut(String name, Object data) {

    }

    @Override
    public boolean isHandle(Object value) {
        return (value instanceof String &&
                (this.getResId(value) >>> 24) >= 2)
                || (value instanceof Integer
                && ((int) value >>> 24) >= 2);
    }

}

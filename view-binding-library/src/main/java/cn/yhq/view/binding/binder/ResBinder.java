package cn.yhq.view.binding.binder;

import android.content.res.Resources;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ResBinder implements IBinder {
    private ViewRender viewRender;

    public ResBinder(ViewRender viewRender) {
        this.viewRender = viewRender;
    }

    private int getResId(Object value) {
        if (value instanceof Integer) {
            return (int) value;
        } else {
            Resources res = viewRender.getContext().getResources();
            String packageName = viewRender.getContext().getPackageName();
            return res.getIdentifier(packageName + value.toString().replace("@", ":"), null, null);
        }
    }

    private boolean getBoolean(int id) {
        return viewRender.getContext().getResources().getBoolean(id);
    }

    private String getString(int id) {
        return viewRender.getContext().getResources().getString(id);
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
        return (value instanceof String && value.toString().indexOf("@") == 0
                || value.toString().indexOf("android:@") == 0)
                || (value instanceof Integer
                && ((int) value >>> 24) >= 2);
    }

}

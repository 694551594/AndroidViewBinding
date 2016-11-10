package cn.yhq.view.binding.binder;

import android.content.res.Resources;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ResBinder extends BaseBinder implements IBinder {

    private int getResId(Object value) {
        if (value instanceof Integer) {
            return (int) value;
        } else {
            Resources res = this.getContext().getResources();
            String packageName = this.getContext().getPackageName();
            return res.getIdentifier(packageName + value.toString().replace("@", ":"), null, null);
        }
    }

    private boolean getBoolean(int id) {
        return this.getContext().getResources().getBoolean(id);
    }

    private String getString(int id) {
        return this.getContext().getResources().getString(id);
    }

    private int getInt(int id) {
        return this.getContext().getResources().getInteger(id);
    }

    @Override
    public void onBind(int id, BindType type, Object value) {
        int resId = getResId(value);
        switch (type) {
            case TEXT:
                setText(id, resId);
                break;
            case CHECKED:
                setCheck(id, this.getBoolean(resId));
                break;
            case VISIBILITY:
                setVisibility(id, this.getInt(resId));
                break;
            case IMAGE_URL:
                setImage(id, this.getString(resId));
                break;
            case IMAGE_RESID:
                setImage(id, resId);
                break;
        }
    }

    @Override
    public boolean isHandle(Object value) {
        return (value instanceof String && value.toString().indexOf("@") == 0
                || value.toString().indexOf("android:@") == 0)
                || (value instanceof Integer
                && ((int) value >>> 24) >= 2);
    }
}

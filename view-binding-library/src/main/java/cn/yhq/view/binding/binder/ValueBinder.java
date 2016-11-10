package cn.yhq.view.binding.binder;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ValueBinder extends BaseBinder implements IBinder {

    @Override
    public void onBind(int id, BindType type, Object value) {
        switch (type) {
            case TEXT:
                setText(id, value.toString());
                break;
            case CHECKED:
                setCheck(id, (Boolean) value);
                break;
            case VISIBILITY:
                setVisibility(id, (Integer) value);
                break;
            case IMAGE_URL:
                setImage(id, value.toString());
                break;
            case IMAGE_RESID:
                setImage(id, (Integer) value);
                break;
        }
    }

    @Override
    public boolean isHandle(Object value) {
        return true;
    }
}

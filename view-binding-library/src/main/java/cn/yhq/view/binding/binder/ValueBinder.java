package cn.yhq.view.binding.binder;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ValueBinder extends BaseBinder implements IBinder {

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
    public void onPut(String name, Object data) {

    }

    @Override
    public boolean isHandle(Object value) {
        return true;
    }
}

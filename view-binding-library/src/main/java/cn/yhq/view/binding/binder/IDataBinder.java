package cn.yhq.view.binding.binder;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public interface IDataBinder {
    void onBind(int id, BindType type, Object value);

    Object onPut(String name, Object data);

    boolean isHandle(Object value);
}

package cn.yhq.view.binding.binder;

/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public interface IBinder {
    void onBind(int id, BindType type, Object value);

    void onPut(String name, Object data);

    boolean isHandle(Object value);
}

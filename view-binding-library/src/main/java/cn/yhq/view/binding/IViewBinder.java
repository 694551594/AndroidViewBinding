package cn.yhq.view.binding;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public interface IViewBinder<T> {

    void onBinding(ViewBinder<T> viewBinder, T data);

}

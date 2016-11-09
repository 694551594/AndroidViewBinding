package cn.yhq.view.binding;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public abstract class IPropertyChanged<T> {
    private ViewBinder<T> viewBinder;

    void setViewBinder(ViewBinder<T> viewBinder) {
        this.viewBinder = viewBinder;
    }

    protected void notifyPropertyChanged() {
        if (viewBinder != null) {
            this.viewBinder.refresh();
        }
    }

}

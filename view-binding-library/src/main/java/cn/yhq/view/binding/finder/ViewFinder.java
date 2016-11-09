package cn.yhq.view.binding.finder;

import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public class ViewFinder implements IViewFinder {
    private View view;

    public ViewFinder(View view) {
        this.view = view;
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return (T) view.findViewById(id);
    }
}

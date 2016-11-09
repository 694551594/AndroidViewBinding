package cn.yhq.view.binding.finder;

import android.view.View;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public interface IViewFinder {

    <T extends View> T findViewById(int id);
}

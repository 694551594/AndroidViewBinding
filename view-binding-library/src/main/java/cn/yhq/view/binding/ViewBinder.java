package cn.yhq.view.binding;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.yhq.view.binding.finder.ActivityViewFinder;
import cn.yhq.view.binding.finder.ViewFinder;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public class ViewBinder {
    private ViewFactory viewFactory;
    private Map<String, PropertyChangeSupport> propertyChangeSupports = new HashMap<>();
    private Map<String, Map<String, PropertyChangeListener>> listeners = new HashMap<>();
    private JexlContext jexlContext = new MapContext();
    private JexlEngine jexlEngine = new JexlBuilder().create();

    public <T extends PropertyChangeSupport> ViewBinder put(T propertyChangeSupport) {
        return put(propertyChangeSupport.getClass().getSimpleName(), propertyChangeSupport);
    }

    public <T extends PropertyChangeSupport> ViewBinder put(String name, T propertyChangeSupport) {
        this.propertyChangeSupports.put(name.toLowerCase(Locale.getDefault()), propertyChangeSupport);
        this.jexlContext.set(name.toLowerCase(Locale.getDefault()), propertyChangeSupport);
        return this;
    }

    public ViewBinder execute() {
        for (Map.Entry<String, Map<String, PropertyChangeListener>> entry1 : listeners.entrySet()) {
            PropertyChangeSupport propertyChangeSupport = propertyChangeSupports.get(entry1.getKey());
            for (Map.Entry<String, PropertyChangeListener> entry2 : entry1.getValue().entrySet()) {
                propertyChangeSupport.addPropertyChangeListener(entry2.getKey(), entry2.getValue());
            }
            propertyChangeSupport.fireChangeAll();
        }
        return this;
    }

    public ViewBinder bind(final int id, final BindType type, final String express) {
        String dataName = express.substring(0, express.indexOf("."));
        String propertyName = express.substring(express.indexOf(".") + 1, express.length());
        Map<String, PropertyChangeListener> mapper = this.listeners.get(dataName);
        if (mapper == null) {
            mapper = new HashMap<>();
            this.listeners.put(dataName, mapper);
        }
        mapper.put(propertyName, new PropertyChangeListener() {
            @Override
            public void propertyChanged() {
                JexlExpression jexlExpression = jexlEngine.createExpression(express);
                Object newValue = jexlExpression.evaluate(jexlContext);
                switch (type) {
                    case TEXT:
                        setText(id, newValue.toString());
                        break;
                    case CHECKED:
                        setCheck(id, (Boolean) newValue);
                        break;
                    case VISIBILITY:
                        setVisibility(id, (Integer) newValue);
                        break;
                    case IMAGE_URL:
                        setImage(id, newValue.toString());
                        break;
                    case IMAGE_RESID:
                        setImage(id, (Integer) newValue);
                        break;
                }
            }
        });
        return this;
    }

    public ViewBinder(Activity activity) {
        viewFactory = new ViewFactory(new ActivityViewFinder(activity));
    }

    public ViewBinder(View view) {
        viewFactory = new ViewFactory(new ViewFinder(view));
    }

    public ViewBinder(SparseArray<View> views) {
        viewFactory = new ViewFactory(views, null);
    }

    public <T extends View> T getView(int id) {
        return viewFactory.getView(id);
    }

    public void setVisibility(int id, int visibility) {
        ViewFactory.setVisibility(getView(id), visibility);
    }

    public void setText(int id, CharSequence data) {
        ViewFactory.setText(getView(id), data);
    }

    public void setText(int id, int resId) {
        ViewFactory.setText(getView(id), resId);
    }

    public void setCheck(int id, boolean checked) {
        ViewFactory.setCheck(getView(id), checked);
    }

    public void setImage(int id, String url) {
        ViewFactory.setImage(getView(id), url);
    }

    public void setImage(int id, Bitmap bitmap) {
        ViewFactory.setImage(getView(id), bitmap);
    }

    public void setImage(int id, Drawable drawable) {
        ViewFactory.setImage(getView(id), drawable);
    }

    public void setImage(int id, int resId) {
        ViewFactory.setImage(getView(id), resId);
    }

    public void setImage(int id, File file) {
        ViewFactory.setImage(getView(id), file);
    }

}

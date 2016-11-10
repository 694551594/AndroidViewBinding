package cn.yhq.view.binding;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.yhq.view.binding.binder.BindType;
import cn.yhq.view.binding.binder.BinderFactory;
import cn.yhq.view.binding.binder.BinderProvider;
import cn.yhq.view.binding.binder.ExpressBinder;
import cn.yhq.view.binding.property.PropertyChangeListener;
import cn.yhq.view.binding.property.PropertyChangeSupport;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public final class ViewBinder {
    private BinderProvider binderProvider;
    private Map<String, PropertyChangeSupport> propertyChangeSupports = new HashMap<>();
    private Map<String, Map<String, PropertyChangeListener>> listeners = new HashMap<>();

    public <T extends PropertyChangeSupport> ViewBinder put(T propertyChangeSupport) {
        return put(propertyChangeSupport.getClass().getSimpleName(), propertyChangeSupport);
    }

    public <T extends PropertyChangeSupport> ViewBinder put(String name, T propertyChangeSupport) {
        this.propertyChangeSupports.put(name.toLowerCase(Locale.getDefault()), propertyChangeSupport);
        this.binderProvider.put(name.toLowerCase(Locale.getDefault()), propertyChangeSupport);
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

    public ViewBinder bind(final int id, final BindType type, final String dataName, final String propertyName, final Object value) {
        Map<String, PropertyChangeListener> mapper = this.listeners.get(dataName);
        if (mapper == null) {
            mapper = new HashMap<>();
            this.listeners.put(dataName, mapper);
        }
        mapper.put(propertyName, new PropertyChangeListener() {
            @Override
            public void propertyChanged() {
                binderProvider.bind(id, type, value);
            }
        });
        return this;
    }

    public ViewBinder bind(int id, BindType type, String value) {
        String express = ExpressBinder.getExpress(value);
        if (express != null) {
            String dataName = express.substring(0, express.indexOf("."));
            String propertyName = express.substring(express.indexOf(".") + 1, express.length());
            return bind(id, type, dataName, propertyName, value);
        } else {
            binderProvider.bind(id, type, value);
            return this;
        }

    }

    public ViewBinder(Activity activity) {
        binderProvider = BinderFactory.create(activity);
    }

    public ViewBinder(View view) {
        binderProvider = BinderFactory.create(view);
    }

    public ViewBinder(Context context, SparseArray<View> views) {
        binderProvider = BinderFactory.create(context, views);
    }

}

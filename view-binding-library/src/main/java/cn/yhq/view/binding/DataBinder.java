package cn.yhq.view.binding;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.yhq.view.binding.binder.BindType;
import cn.yhq.view.binding.binder.DataBindProvider;
import cn.yhq.view.binding.binder.DataBindProviderFactory;
import cn.yhq.view.binding.binder.ExpressBinder;
import cn.yhq.view.binding.binder.ViewRender;
import cn.yhq.view.binding.property.PropertyChangeListener;
import cn.yhq.view.binding.property.PropertyChangeSupport;

/**
 * Created by Yanghuiqiang on 2016/11/9.
 */

public final class DataBinder {
    private DataBindProvider dataBindProvider;
    private Map<String, PropertyChangeSupport> propertyChangeSupports = new HashMap<>();
    private Map<String, Map<String, PropertyChangeListener>> listeners = new HashMap<>();

    public <T extends PropertyChangeSupport> DataBinder put(T propertyChangeSupport) {
        return put(propertyChangeSupport.getClass().getSimpleName().toLowerCase(Locale.getDefault()), propertyChangeSupport);
    }

    public DataBinder put(String name, Object data) {
        if (data instanceof PropertyChangeSupport) {
            this.propertyChangeSupports.put(name, (PropertyChangeSupport) data);
        }
        this.dataBindProvider.put(name, data);
        return this;
    }

    public DataBinder execute() {
        for (Map.Entry<String, Map<String, PropertyChangeListener>> entry1 : listeners.entrySet()) {
            PropertyChangeSupport propertyChangeSupport = propertyChangeSupports.get(entry1.getKey());
            if (propertyChangeSupport == null) {
                continue;
            }
            for (Map.Entry<String, PropertyChangeListener> entry2 : entry1.getValue().entrySet()) {
                propertyChangeSupport.addPropertyChangeListener(entry2.getKey(), entry2.getValue());
            }
            propertyChangeSupport.fireChangeAll();
        }
        return this;
    }

    public DataBinder bind(final int id, final BindType type, final String dataName, final String propertyName, final Object value) {
        Map<String, PropertyChangeListener> mapper = this.listeners.get(dataName);
        if (mapper == null) {
            mapper = new HashMap<>();
            this.listeners.put(dataName, mapper);
        }
        mapper.put(propertyName, new PropertyChangeListener() {
            @Override
            public void propertyChanged() {
                dataBindProvider.bind(id, type, value);
            }
        });
        return this;
    }

    public DataBinder bind(int id, BindType type, Object value) {
        if (value instanceof String) {
            String express = ExpressBinder.getExpress((String) value);
            if (express != null) {
                if (express.indexOf(".") != -1) {
                    String dataName = express.substring(0, express.indexOf("."));
                    String propertyName = express.substring(express.indexOf(".") + 1, express.length());
                    return bind(id, type, dataName, propertyName, value);
                }
            }
        }
        dataBindProvider.bind(id, type, value);
        return this;
    }

    public ViewRender getViewRender() {
        return dataBindProvider.getViewRender();
    }

    public DataBinder(Activity activity) {
        dataBindProvider = DataBindProviderFactory.create(activity);
    }

    public DataBinder(View view) {
        dataBindProvider = DataBindProviderFactory.create(view);
    }

    public DataBinder(Context context, SparseArray<View> views) {
        dataBindProvider = DataBindProviderFactory.create(context, views);
    }

}

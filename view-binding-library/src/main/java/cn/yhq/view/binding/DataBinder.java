package cn.yhq.view.binding;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private Map<String, Object> datas = new HashMap<>();
    private Map<String, BindInfo> bindInfos = new LinkedHashMap<>();
    private Map<String, PropertyChangeSupport> propertyChangeSupports = new HashMap<>();
    private Map<String, Map<String, PropertyChangeListener>> listeners = new HashMap<>();


    public static class BindInfo {
        public int id;
        public BindType bindType;
        public String dataName;
        public String propertyName;
        public Object value;

        BindInfo(final int id, final BindType type, final String dataName, final String propertyName, final Object value) {
            this.id = id;
            this.bindType = type;
            this.dataName = dataName;
            this.propertyName = propertyName;
            this.value = value;
        }
    }

    public DataBinder put(String name, Object data) {
        datas.put(name, data);
        return this;
    }

    public DataBinder execute() {
        for (Map.Entry<String, Object> entry : datas.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            Object data = this.dataBindProvider.put(name, value);
            if (data instanceof PropertyChangeSupport) {
                this.propertyChangeSupports.put(name, (PropertyChangeSupport) data);
            }

        }
        for (Map.Entry<String, BindInfo> entry : bindInfos.entrySet()) {
            String name = entry.getKey();
            BindInfo bindInfo = entry.getValue();
            if (!datas.containsKey(name)) {
                String value = name;
                Object data = this.dataBindProvider.put(name, "${" + value + "}");
                if (data instanceof PropertyChangeSupport) {
                    this.propertyChangeSupports.put(name, (PropertyChangeSupport) data);
                }
            }
            bind(bindInfo.id, bindInfo.bindType, bindInfo.dataName, bindInfo.propertyName, bindInfo.value);
        }

        for (Map.Entry<String, Map<String, PropertyChangeListener>> entry1 : listeners.entrySet()) {
            PropertyChangeSupport propertyChangeSupport = propertyChangeSupports.get(entry1.getKey());
            if (propertyChangeSupport == null) {
                propertyChangeSupport = new PropertyChangeSupport();
                this.propertyChangeSupports.put(entry1.getKey(), propertyChangeSupport);
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
                    String propertyName = express.substring(express.lastIndexOf(".") + 1, express.length());
                    String dataName = express.substring(0, express.lastIndexOf("."));
                    bindInfos.put(dataName, new BindInfo(id, type, dataName, propertyName, value));
                    return this;
                } else {
                    bindInfos.put(express, new BindInfo(id, type, express, express, value));
                    return this;
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

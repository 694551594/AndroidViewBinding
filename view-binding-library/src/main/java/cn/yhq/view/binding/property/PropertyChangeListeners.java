package cn.yhq.view.binding.property;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/9.
 */

public class PropertyChangeListeners {
    private final Set<PropertyChangeListener> listeners;

    public PropertyChangeListeners() {
        this.listeners = new LinkedHashSet<>();
    }

    public void add(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    public boolean remove(PropertyChangeListener listener) {
        return listeners.remove(listener);
    }

    public boolean contains(PropertyChangeListener listener) {
        return listeners.contains(listener);
    }

    public void firePropertyChange() {
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChanged();
        }
    }
}

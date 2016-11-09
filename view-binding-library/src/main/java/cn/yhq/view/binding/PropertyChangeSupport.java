package cn.yhq.view.binding;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 */

public class PropertyChangeSupport {
    private final Map<String, PropertyChangeListeners> listenerMap;

    public PropertyChangeSupport() {
        listenerMap = new HashMap<>();
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (!listenerMap.containsKey(propertyName)) {
            listenerMap.put(propertyName, new PropertyChangeListeners());
        }

        PropertyChangeListeners listeners = listenerMap.get(propertyName);
        listeners.add(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (listenerMap.containsKey(propertyName)) {
            PropertyChangeListeners listeners = listenerMap.get(propertyName);
            listeners.remove(listener);
        }
    }

    public void firePropertyChange(String propertyName, Object newValue) {
        PropertyChangeListeners propertyChangeListeners = listenerMap.get(propertyName);
        if (propertyChangeListeners != null) {
            propertyChangeListeners.firePropertyChange();
        }
    }

    public void fireChangeAll() {
        for (PropertyChangeListeners propertyChangeListeners : listenerMap.values()) {
            propertyChangeListeners.firePropertyChange();
        }
    }
}

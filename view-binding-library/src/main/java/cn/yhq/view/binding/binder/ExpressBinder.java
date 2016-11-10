package cn.yhq.view.binding.binder;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JxltEngine;
import org.apache.commons.jexl3.MapContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ExpressBinder implements IBinder {
    private JexlContext jexlContext = new MapContext();
    private JexlEngine jexlEngine = new JexlBuilder().create();
    private JxltEngine jxltEngine = jexlEngine.createJxltEngine();
    private ViewRender viewRender;

    public ExpressBinder(ViewRender viewRender) {
        this.viewRender = viewRender;
    }

    @Override
    public void onPut(String name, Object data) {
        this.jexlContext.set(name, data);
    }

    @Override
    public void onBind(int id, BindType type, Object value) {
        JxltEngine.Expression expression = jxltEngine.createExpression(value.toString());
        Object newValue = expression.evaluate(jexlContext);
        switch (type) {
            case TEXT:
                viewRender.setText(id, newValue.toString());
                break;
            case CHECKED:
                viewRender.setCheck(id, (Boolean) newValue);
                break;
            case VISIBILITY:
                viewRender.setVisibility(id, (Integer) newValue);
                break;
            case IMAGE_URL:
                viewRender.setImage(id, newValue.toString());
                break;
            case IMAGE_RESID:
                viewRender.setImage(id, (Integer) newValue);
                break;
        }
    }

    @Override
    public boolean isHandle(Object value) {
        return getExpress(value.toString()) != null;
    }

    public static String getExpress(String express) {
        Pattern p = Pattern.compile("\\$\\{(.*)?\\}");
        Matcher m = p.matcher(express);
        while(m.find()){
            return m.group(1);
        }
        return null;
    }
}

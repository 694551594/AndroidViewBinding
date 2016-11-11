package cn.yhq.view.binding.binder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JxltEngine;
import org.apache.commons.jexl3.MapContext;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Yanghuiqiang on 2016/11/10.
 */

public class ExpressBinder extends BaseDataBinder implements IDataBinder {
    private JexlContext jexlContext = new MapContext();
    private JexlEngine jexlEngine = new JexlBuilder().create();
    private JxltEngine jxltEngine = jexlEngine.createJxltEngine();

    public ExpressBinder(ViewRender viewRender) {
        super(viewRender);
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
            case IMAGE_FILE:
                viewRender.setImage(id, (File) newValue);
                break;
            case IMAGE_DRAWABLE:
                viewRender.setImage(id, (Drawable) newValue);
                break;
            case IMAGE_BITMAP:
                viewRender.setImage(id, (Bitmap) newValue);
                break;
            case TAG:
                viewRender.setTag(id, newValue);
                break;
            case LISTENER_CLICK:
                viewRender.setOnClickListener(id, (View.OnClickListener) newValue);
                break;
            case LISTENER_LONG_CLICK:
                viewRender.setOnLongClickListener(id, (View.OnLongClickListener) newValue);
                break;
            case LISTENER_ITEM_CLICK:
                viewRender.setOnItemClickListener(id, (AdapterView.OnItemClickListener) newValue);
                break;
            case LISTENER_ITEM_LONG_CLICK:
                viewRender.setOnItemLongClickListener(id, (AdapterView.OnItemLongClickListener) newValue);
                break;
            case LISTENER_CHECKED_CHANGE:
                viewRender.setOnCheckedChangeListener(id, (CompoundButton.OnCheckedChangeListener) newValue);
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

package cn.yhq.view.binding.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.yhq.base.BaseActivity;
import cn.yhq.view.binding.BindType;
import cn.yhq.view.binding.PropertyChangeSupport;
import cn.yhq.view.binding.ViewBinder;

public class MainActivity extends BaseActivity {
    private User user;
    private int index;

    static {
        CustomActivityOnCrash.setShowErrorDetails(true);
    }

    public static class User extends PropertyChangeSupport {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
            this.firePropertyChange("username", username);
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
            this.firePropertyChange("password", password);
        }
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        this.user = new User();
        this.user.setPassword("我是密码");
        this.user.setUsername("我是用户名");

        ViewBinder viewBinder = new ViewBinder(this)
                .setData(user)
                .bind(R.id.textView1, BindType.TEXT, "user.username")
                .bind(R.id.textView2, BindType.TEXT, "user.password")
                .execute();

        Button button = viewBinder.getView(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                user.setUsername("用户名改变了" + index);
                user.setPassword("用户密码改变了" + index);
            }
        });
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }


}

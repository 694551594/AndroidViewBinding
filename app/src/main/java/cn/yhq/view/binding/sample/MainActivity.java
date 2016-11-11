package cn.yhq.view.binding.sample;

import android.os.Bundle;
import android.view.View;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.yhq.base.BaseActivity;
import cn.yhq.view.binding.DataBinderFactory;
import cn.yhq.view.binding.binder.BindType;
import cn.yhq.view.binding.property.PropertyChangeSupport;

public class MainActivity extends BaseActivity {
    private User user;
    private User _user;
    private User _user2;
    private int index;

    static {
        CustomActivityOnCrash.setShowErrorDetails(true);
    }

    public static class User extends PropertyChangeSupport {
        private String username;
        private String password;
        private User user;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
            this.firePropertyChange("username");
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
            this.firePropertyChange("password");
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
            this.firePropertyChange("user");
        }
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        this.user = new User();
        this.user.setPassword("我是用户1密码");
        this.user.setUsername("我是用户1用户名");

        this._user = new User();
        this._user.setPassword("我是用户2密码");
        this._user.setUsername("我是用户2用户名");

        this._user2 = new User();
        this._user2.setPassword("我是用户3密码");
        this._user2.setUsername("我是用户3用户名");
        this._user.setUser(_user2);
        this.user.setUser(_user);

        DataBinderFactory.create(this)
                .put("user", user)
                .put("click", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        index++;
                        user.setUsername("用户1用户名改变了" + index);
                        _user2.setPassword("用户3用户密码改变了" + index);
                    }
                })
                .bind(R.id.textView1, BindType.TEXT, "${user.username}")
                .bind(R.id.textView2, BindType.TEXT, "${user.user.user.password}")
                .bind(R.id.button, BindType.TEXT, "改变值")
                .bind(R.id.button, BindType.LISTENER_CLICK, "${click}")
                .execute();
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }


}

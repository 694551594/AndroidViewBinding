package cn.yhq.view.binding.sample;

import android.os.Bundle;
import android.view.View;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.yhq.base.BaseActivity;
import cn.yhq.view.binding.DataBinderFactory;
import cn.yhq.view.binding.binder.BindType;
import cn.yhq.view.binding.property.PropertyChangeSupport;

public class MainActivity extends BaseActivity {
    private User user1;
    private User user2;
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

        this.user1 = new User();
        this.user1.setPassword("我是用户1密码");
        this.user1.setUsername("我是用户1用户名");

        this.user2 = new User();
        this.user2.setPassword("我是用户2密码");
        this.user2.setUsername("我是用户2用户名");

        this.user1.setUser(user1);

        DataBinderFactory.create(this)
                .put("user1", user1)
                .put("user2", user2)
                .put("click", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        index++;
                        user1.setUsername("用户1用户名改变了" + index);
                        user2.setPassword("用户2用户密码改变了" + index);
                    }
                })
                .bind(R.id.textView1, BindType.TEXT, "${user1.username}")
                .bind(R.id.textView2, BindType.TEXT, "${user1.user.password}")
                .bind(R.id.button, BindType.TEXT, "改变值")
                .bind(R.id.button, BindType.LISTENER_CLICK, "${click}")
                .execute();
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }


}

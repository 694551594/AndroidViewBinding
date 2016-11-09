package cn.yhq.view.binding.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.yhq.base.BaseActivity;
import cn.yhq.view.binding.IPropertyChanged;
import cn.yhq.view.binding.IViewBinder;
import cn.yhq.view.binding.ViewBinder;

public class MainActivity extends BaseActivity {
    private User user;
    private int index;
    private ViewBinder<User> viewBinder;

    static {
        CustomActivityOnCrash.setShowErrorDetails(true);
    }

    public static class User extends IPropertyChanged<User> {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
            this.notifyPropertyChanged();
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
            this.notifyPropertyChanged();
        }
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        this.user = new User();
        this.user.setPassword("我是密码");
        this.user.setUsername("我是用户名");

//        this.viewBinder = new ViewBinder<User>(this) {
//            @Override
//            public void onBinding(ViewBinder<User> viewBinder, User data) {
//                this.setText(R.id.textView1, data.getUsername());
//                this.setText(R.id.textView2, data.getPassword());
//            }
//        }.bind(user);

        viewBinder = new ViewBinder<User>(this).bind(user, new IViewBinder<User>() {
            @Override
            public void onBinding(final ViewBinder<User> viewBinder, User data) {
                viewBinder.setText(R.id.textView1, data.getUsername());
                viewBinder.setText(R.id.textView2, data.getPassword());
            }
        });

        Button button = viewBinder.getView(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername("用户名改变了" + index++);
                user.setPassword("用户密码改变了" + index++);
                // viewBinder.refresh();
            }
        });
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }


}

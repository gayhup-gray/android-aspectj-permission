package net.androidwing.aspectj;

import android.content.Context;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;

/**
 * Created by hash on 2017/7/4.
 */

public class CheckPermission {
    private boolean isLogin;
    private boolean isAuth;
    private boolean isPass;
    private final Context context;

    private CheckPermission(Builder builder) {
        isLogin = builder.isLogin;
        isAuth = builder.isAuth;
        isPass = builder.isPass;
        context = builder.context;
    }

    public boolean check() {
        return isPass;
    }

    public static final class Builder {
        private boolean isLogin;
        private boolean isAuth;
        private boolean isPass = true;
        private final Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder isLogin(boolean isLogin) {
            this.isLogin = isLogin;
            if (!(isLogin && isPass)) {
                alertLogin();
                isPass = false;
            }
            return this;
        }

        private void alertLogin() {
            new AlertView.Builder().setContext(context)
                    .setStyle(AlertView.Style.Alert)
                    .setTitle("请登录!")
                    .setCancelText("取消")
                    .setDestructive("确定")
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(Object o, int position) {
                            if (position == -1) {

                            }
                        }
                    })
                    .build()
                    .show();
        }

        public Builder isAuth(boolean isAuth) {
            this.isAuth = isAuth;
            if (!(isAuth && isPass)) {
                alertAuth();
                isPass = false;
            }
            return this;
        }

        private void alertAuth() {
            new AlertView.Builder().setContext(context)
                    .setStyle(AlertView.Style.Alert)
                    .setTitle("请完成实名认证!")
                    .setCancelText("取消")
                    .setDestructive("确定")
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(Object o, int position) {
                            if (position == -1) {

                            }
                        }
                    })
                    .build()
                    .show();
        }


        public CheckPermission build() {
            return new CheckPermission(this);
        }
    }
}

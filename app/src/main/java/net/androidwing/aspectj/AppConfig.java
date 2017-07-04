package net.androidwing.aspectj;

/**
 * Created by hash on 2017/7/4.
 */

class AppConfig {
    private boolean isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    private boolean isAuth = false;
    private static final AppConfig ourInstance = new AppConfig();

    static AppConfig getInstance() {
        return ourInstance;
    }

    private AppConfig() {
    }
}

package net.androidwing.aspectj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * Hello World!
     */
    private TextView mTextView;
    /**
     * login
     */
    private Button mLogin;
    /**
     * auth
     */
    private Button mAuth;
    /**
     * 2main2
     */
    private Button m2main2;
    /**
     * 2haha
     */
    private Button m2haha;


    @Override
    protected void initSubTitle() {
        setTitle(getString(R.string.main));
        setBackArrow();
        App.setInstanceRef(this);
    }

    @Override
    protected void initSubView() {
        initView();
    }

    @Override
    protected int initSubLayout() {
        return R.layout.activity_main;
    }


    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mAuth = (Button) findViewById(R.id.auth);
        mAuth.setOnClickListener(this);
        m2main2 = (Button) findViewById(R.id.main2);
        m2main2.setOnClickListener(this);
        m2haha = (Button) findViewById(R.id.haha);
        m2haha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                justLogin();
                break;
            case R.id.auth:
                justAuth();
                break;
            case R.id.main2:
                Main2Activity.start(this);
                break;
            case R.id.haha:
                HaHaActivity.start(this);
                break;
        }
    }

    private void justAuth() {
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.Alert)
                .setTitle("AUTH")
                .setCancelText("取消")
                .setDestructive("确定")
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == -1) {
                            AppConfig.getInstance().setAuth(false);
                        } else {
                            AppConfig.getInstance().setAuth(true);
                        }
                    }
                })
                .build()
                .show();
    }

    private void justLogin() {
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.Alert)
                .setTitle("LOGIN")
                .setCancelText("取消")
                .setDestructive("确定")
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == -1) {
                            AppConfig.getInstance().setLogin(false);
                        } else {
                            AppConfig.getInstance().setLogin(true);
                        }
                    }
                })
                .build()
                .show();
    }
}

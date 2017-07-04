package net.androidwing.aspectj;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * title
     */
    private TextView mTitle;
    private Toolbar mToolbar;
    private FrameLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
        if (initSubLayout() > 0) {
            LayoutInflater.from(this).inflate(initSubLayout(), mRootView);
        }
        initSubTitle();
        initSubView();
    }

    protected abstract void initSubTitle();

    protected abstract void initSubView();

    protected abstract int initSubLayout();

    private void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRootView = (FrameLayout) findViewById(R.id.rootView);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
//    protected  abstract  void

    /**
     * 设置左上角back按钮
     */
    public void setBackArrow() {
        final Drawable upArrow = getDrawable(R.mipmap.back); //给ToolBar设置左侧的图标
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮的点击事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置标题 * * @param resId
     */
    public void setTitle(int resId) {
        mTitle.setText(resId);
    }

    /**
     * 设置标题 * * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
    }
}

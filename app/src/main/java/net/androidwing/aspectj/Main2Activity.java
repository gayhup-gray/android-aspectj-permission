package net.androidwing.aspectj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends BaseActivity {


    @Override
    protected void initSubTitle() {
        setTitle(getString(R.string.only));
        setBackArrow();
    }

    @Override
    protected void initSubView() {

    }

    @Override
    protected int initSubLayout() {
        return R.layout.activity_main2;
    }
    @CheckLogin
    public static void start(Context context) {
        Intent starter = new Intent(context, Main2Activity.class);
        context.startActivity(starter);
    }
}

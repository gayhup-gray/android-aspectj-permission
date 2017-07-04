package net.androidwing.aspectj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HaHaActivity extends BaseActivity {


    @Override
    protected void initSubTitle() {
        setTitle(getString(R.string.both));
        setBackArrow();
    }

    @Override
    protected void initSubView() {

    }

    @Override
    protected int initSubLayout() {
        return R.layout.activity_ha_ha;
    }

    @CheckLogin
    @CheckAuth
    public static void start(Context context) {
        Intent starter = new Intent(context, HaHaActivity.class);
        context.startActivity(starter);
    }

}

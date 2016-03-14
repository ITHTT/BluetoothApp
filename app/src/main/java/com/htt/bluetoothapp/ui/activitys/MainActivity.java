package com.htt.bluetoothapp.ui.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.htt.bluetoothapp.R;
import com.htt.bluetoothapp.app.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    public void addRedColor(View view){
        setStatusBarColor(R.color.colorAccent);
    }

    public void addBlueColor(View view){
        setStatusBarColor(R.color.colorPrimary);
    }

    public void clearColor(View view){
        clearStatusBarColor();
    }
}

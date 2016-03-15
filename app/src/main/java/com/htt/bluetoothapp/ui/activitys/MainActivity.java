package com.htt.bluetoothapp.ui.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.htt.bluetoothapp.R;
import com.htt.bluetoothapp.app.BaseActivity;
import com.htt.bluetoothapp.ui.widgets.RandomViewLayout;
import com.htt.bluetoothapp.ui.widgets.ShadeCircleButton;
import com.htt.bluetoothapp.ui.widgets.radarscanview.RadarScanView;

public class MainActivity extends BaseActivity {
    private RadarScanView radarScanView;
    private ShadeCircleButton shadeCircleButton;
    private RandomViewLayout randomViewLayout;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        radarScanView= (RadarScanView) this.findViewById(R.id.radarscanview);
        shadeCircleButton=(ShadeCircleButton)this.findViewById(R.id.bt_radarscan);
        randomViewLayout=(RandomViewLayout)this.findViewById(R.id.layout_randomview);
        randomViewLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                randomViewLayout.addKeyWord("LOL");
                randomViewLayout.addKeyWord("炉石传说");
                randomViewLayout.addKeyWord("DATA2");
                randomViewLayout.addKeyWord("风暴英雄");
                randomViewLayout.addKeyWord("CS GO");
                randomViewLayout.addKeyWord("星际争霸");
                randomViewLayout.addKeyWord("国家崛起");
                randomViewLayout.addKeyWord("帝国时代");
                randomViewLayout.show();
            }
        },5*1000);
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

    public void startRadarScan(View view){
        if(radarScanView.isRunning()) {
            radarScanView.stopRadarScan();
            shadeCircleButton.setText("开始");
        }else{
            radarScanView.startRadarScan();
            shadeCircleButton.setText("暂停");
        }
    }
}

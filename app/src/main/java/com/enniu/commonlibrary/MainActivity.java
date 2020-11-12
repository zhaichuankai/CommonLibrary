package com.enniu.commonlibrary;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.enniu.library.dialog.XxLoadingDialog;
import com.enniu.library.widget.XxWaterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private XxWaterView mWaterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWaterView = findViewById(R.id.water_view);
        mWaterView.setData("签到",R.mipmap.ic_launcher);
    }
}

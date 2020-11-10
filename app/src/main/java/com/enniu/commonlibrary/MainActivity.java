package com.enniu.commonlibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.enniu.library.dialog.XxVipDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XxVipDialog dialog = new XxVipDialog(this, new XxVipDialog.IXxVipListener() {
            @Override
            public void clickTop() {

            }

            @Override
            public void clickBottom() {

            }
        });
        dialog.mTvTitle.setTextColor(ContextCompat.getColor(this,R.color.colorPink));
        dialog .show();
    }
}

package com.enniu.library.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.enniu.library.R;


/**
 * Created by zac on 2017/11/26 19:10
 * 获取验证码的倒计时
 */

public class XxTimeCount extends CountDownTimer {

    private TextView mTvCode;
    private Context mContext;
    private int mColorId;

    public XxTimeCount(long millisInFuture, long countDownInterval, TextView tv, Context context,int colorId) {
        super(millisInFuture, countDownInterval);
        this.mTvCode = tv;
        this.mContext = context;
        this.mColorId = colorId;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTvCode.setBackgroundResource(R.color.colorTransparent);
        mTvCode.setTextSize(14);
        mTvCode.setTextColor(ContextCompat.getColor(mContext, mColorId));
        mTvCode.setText("剩余" + millisUntilFinished / 1000 + "秒");
        mTvCode.setClickable(false);
        mTvCode.setEnabled(false);
    }

    @Override
    public void onFinish() {
        mTvCode.setBackgroundResource(R.color.colorTransparent);
        mTvCode.setTextSize(14);
        mTvCode.setTextColor(ContextCompat.getColor(mContext, mColorId));
        mTvCode.setText("重新获取");
        mTvCode.setClickable(true);
        mTvCode.setEnabled(true);
    }
}


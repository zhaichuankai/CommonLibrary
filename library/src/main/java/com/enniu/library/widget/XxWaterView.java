package com.enniu.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enniu.library.R;

import java.util.Random;

public class XxWaterView extends FrameLayout {
    private static final int WHAT_ADD_PROGRESS = 1;
    private static final int CHANGE_RANGE = 10;
    public static final int PROGRESS_DELAY_MILLIS = 12;
    private View mView;

    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //根据isCancelAnimtion来标识是否退出，防止界面销毁时，再一次改变UI
            setOffSet();
            mHandler.sendEmptyMessageDelayed(WHAT_ADD_PROGRESS, PROGRESS_DELAY_MILLIS);
        }
    };

    public XxWaterView(@NonNull Context context) {
        this(context, null);
    }

    public XxWaterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XxWaterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mView = inflater.inflate(R.layout.water_item, this, false);
        addView(mView);
    }

    public void setData(String name,int resId){
        mView.setTag(R.string.spd, 0.3f);
        mView.setTag(R.string.original_y, 0f);
        mView.setTag(R.string.isUp, new Random().nextBoolean());
        TextView tvName = mView.findViewById(R.id.tv_name);
        tvName.setText(name);

        ImageView imgIcon = mView.findViewById(R.id.img_icon);
        imgIcon.setImageResource(resId);

        mHandler.sendEmptyMessage(WHAT_ADD_PROGRESS);
    }

    private void setOffSet() {
            //拿到上次view保存的速度
            float spd = (float) mView.getTag(R.string.spd);
            //水滴初始的位置
            float original = (float) mView.getTag(R.string.original_y);
            float step = spd;
            boolean isUp = (boolean) mView.getTag(R.string.isUp);
            float translationY;
            //根据水滴tag中的上下移动标识移动view
            if (isUp) {
                translationY = mView.getY() - step;
            } else {
                translationY = mView.getY() + step;
            }
            //对水滴位移范围的控制
            if (translationY - original > CHANGE_RANGE) {
                translationY = original + CHANGE_RANGE;
                mView.setTag(R.string.isUp, true);
            } else if (translationY - original < -CHANGE_RANGE) {
                translationY = original - CHANGE_RANGE;
                mView.setTag(R.string.spd, 0.3f);
                mView.setTag(R.string.isUp, false);
            }
        mView.setY(translationY);
    }

    /**
     * 界面销毁时回调
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }
}

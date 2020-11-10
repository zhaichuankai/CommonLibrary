package com.enniu.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.enniu.library.R;

/**
 * @Description:
 * @Created:2020-11-10
 */
public class XxVipDialog extends Dialog {
    private RelativeLayout mRlTop, mRlBottom;
    public TextView mTvTitle;
    public TextView mTvContent;
    public ImageView mImgCancel;
    public ImageView mImgLogo;
    public TextView mTvTop;
    public TextView mTvBottom;

    public XxVipDialog(@NonNull Context context, IXxVipListener listener) {
        super(context, R.style.style_dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_deblocking, null);
        setContentView(view);

        mRlTop = view.findViewById(R.id.rl_top);
        mRlBottom = view.findViewById(R.id.rl_bottom);
        mImgCancel = view.findViewById(R.id.img_cancel);
        mImgLogo = view.findViewById(R.id.img_logo);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvContent = view.findViewById(R.id.tv_content);
        mTvTop = view.findViewById(R.id.tv_top);
        mTvBottom = view.findViewById(R.id.tv_bottom);

        mRlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickTop();
                dismiss();
            }
        });

        mRlBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickBottom();
                dismiss();
            }
        });

        mImgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface IXxVipListener{
        void clickTop();

        void clickBottom();
    }
}

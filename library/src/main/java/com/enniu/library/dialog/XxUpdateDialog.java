package com.enniu.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.enniu.library.R;

/**
 * 🙏 GOD BLESS NO BUG !!! 🙏
 * Author： vieboo
 * Date： 2019-12-22 16:09
 * Description：
 */
public class XxUpdateDialog extends Dialog {
    private Context mContext;
    public TextView mTvTitle, mTvContent;
    public Button mBtnSure;
    public ImageView mImgCancel;
    public String mLinkUrl;
    public String mVersion;

    public XxUpdateDialog(@NonNull Context context) {
        super(context, R.style.style_dialog);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
        setContentView(view);

        mTvTitle = view.findViewById(R.id.tv_title);
        mTvContent = view.findViewById(R.id.tv_content);
        mBtnSure = view.findViewById(R.id.btn_sure);
        mImgCancel = view.findViewById(R.id.img_cancel);

        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(mLinkUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
                dismiss();
            }
        });
    }

    public XxUpdateDialog setVersion(String version) {
        this.mVersion=version;
        mTvTitle.setText("【v" + version + "】新版上线");
        return this;
    }

    public void setLink(String link) {
        this.mLinkUrl = link;
    }

    public XxUpdateDialog setContent(String content) {
        mTvContent.setText(content);
        return this;
    }

    public XxUpdateDialog isForce(boolean isForce) {
        if (isForce) {
            mImgCancel.setVisibility(View.GONE);
        } else {
            mImgCancel.setVisibility(View.VISIBLE);
            mImgCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

        setCanceledOnTouchOutside(!isForce);
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

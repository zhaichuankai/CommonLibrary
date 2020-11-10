package com.enniu.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enniu.library.R;

/**
 * Created By TheRainMan
 * Time:2020/3/25 9:42
 * Email:17839330707@163.com
 * Describe:
 */
public class XxLogoutHintDialog {
    private Dialog mDialog;
    public LinearLayout mLlRoot;
    public TextView mTvTitle;
    public TextView mTvContent;
    public TextView mTvSure;
    public Button mBtnCancel;

    public XxLogoutHintDialog(Context context, XxIDialogListener listener) {
        View view = View.inflate(context, R.layout.dialog_logout_hint, null);
        mLlRoot = view.findViewById(R.id.ll_root);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvContent = view.findViewById(R.id.tv_content);
        mTvSure = view.findViewById(R.id.btn_sure);
        mBtnCancel = view.findViewById(R.id.btn_cancel);

        mDialog = new Dialog(context, R.style.style_dialog);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        mTvSure.setOnClickListener(view12 -> {
            listener.sure();
            mDialog.dismiss();
        });

        mBtnCancel.setOnClickListener(view12 -> {
            mDialog.dismiss();
        });
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }
}

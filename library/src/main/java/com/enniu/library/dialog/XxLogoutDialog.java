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
public class XxLogoutDialog {
    private Dialog mDialog;
    public LinearLayout mLlRoot;
    public TextView mTvTitle;
    public TextView mTvContent;
    public Button mBtnSure,mBtnCancel;

    public XxLogoutDialog(Context context, XxIDialogListener listener) {
        View view = View.inflate(context, R.layout.dialog_logout, null);
        mLlRoot = view.findViewById(R.id.ll_root);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvContent = view.findViewById(R.id.tv_content);
        mBtnSure = view.findViewById(R.id.btn_sure);
        mBtnCancel = view.findViewById(R.id.btn_cancel);

        mDialog = new Dialog(context, R.style.style_dialog);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        mBtnSure.setOnClickListener(view12 -> {
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

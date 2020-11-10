package com.enniu.library.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.enniu.library.R;
import com.enniu.library.widget.XxRotateLoading;

/**
 * 🙏 GOD BLESS NO BUG !!! 🙏
 * Author： vieboo
 * Date： 2019-07-09 15:20
 * Description：
 */
public class XxLoadingDialog {

    private Context mContext;
    private Dialog mDialog;
    private XxRotateLoading mLoadingView;

    private XxLoadingDialog() {

    }

    public static class Builder {
        public XxLoadingDialog buildLoading(Context context) {
            XxLoadingDialog dialog = new XxLoadingDialog();
            dialog.createLoadingDialog(context);
            return dialog;
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private void createLoadingDialog(Context context) {
        this.mContext = context;

        View contentView = LayoutInflater.from(context).inflate(R.layout.loading_layout, null, false);
        mLoadingView = contentView.findViewById(R.id.rotateloading);

        mDialog = new Dialog(mContext, R.style.style_loading);
        mDialog.setContentView(contentView);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
    }

    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
            mLoadingView.start();
        }
    }

    public void dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
            mLoadingView.stop();
        }
    }

    public Dialog getDialog() {
        return mDialog;
    }
}

package com.enniu.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.enniu.library.R;
import com.enniu.library.ilistener.XxIDialogListener;

/**
 * Created By TheRainMan
 * Time:2020/3/25 9:42
 * Email:17839330707@163.com
 * Describe:
 */
public class XxLogoutDialog {
    private Dialog mDialog;

    public XxLogoutDialog(Context context, XxIDialogListener listener) {
        mDialog = new Dialog(context, R.style.style_dialog);
        View view = View.inflate(context, R.layout.dialog_logout, null);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        TextView tvSure = view.findViewById(R.id.tv_logout);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);

        tvSure.setOnClickListener(view12 -> {
            listener.sure();
            mDialog.dismiss();
        });

        tvCancel.setOnClickListener(view12 -> {
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

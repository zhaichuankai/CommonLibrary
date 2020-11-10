package com.enniu.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.enniu.library.R;
import com.enniu.library.ilistener.XxIDialogListener;

/**
 * Created By TheRainMan
 * Time:2020/3/25 9:42
 * Email:17839330707@163.com
 * Describe:
 */
public class XxLogoffDialog {
    private Dialog mDialog;

    public XxLogoffDialog(Context context, XxIDialogListener listener) {
        mDialog = new Dialog(context, R.style.style_dialog);
        View view = View.inflate(context, R.layout.dialog_logoff, null);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        TextView tvSure = view.findViewById(R.id.tv_sure);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        ImageView imgCancel = view.findViewById(R.id.img_cancel);

        tvSure.setOnClickListener(view12 -> {
            listener.sure();
            mDialog.dismiss();
        });

        tvCancel.setOnClickListener(view12 -> {
            mDialog.dismiss();
        });

        imgCancel.setOnClickListener(view12 -> {
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

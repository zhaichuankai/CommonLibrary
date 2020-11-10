package com.enniu.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.enniu.library.R;


/**
 * 高级版弹窗
 */

public class XxCommentDialog {
    private Dialog mDialog;

    public XxCommentDialog(Context context, ICommentListener listener) {
        mDialog = new Dialog(context, R.style.style_dialog);
        View view = View.inflate(context, R.layout.dialog_comment, null);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        TextView tvLike = view.findViewById(R.id.tv_like);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvDislike = view.findViewById(R.id.tv_dislike);
        tvLike.setOnClickListener(view12 -> {
            listener.like();
            mDialog.dismiss();
        });

        tvCancel.setOnClickListener(view12 -> {
            mDialog.dismiss();
        });

        tvDislike.setOnClickListener(view12 -> {
            listener.dislike();
            mDialog.dismiss();
        });
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public interface ICommentListener {
        void like();

        void dislike();
    }
}
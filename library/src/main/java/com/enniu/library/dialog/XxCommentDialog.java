package com.enniu.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enniu.library.R;

public class XxCommentDialog {
    private Dialog mDialog;
    public LinearLayout mLlRoot;
    public TextView mTvTitle;
    public TextView mTvLike;
    public TextView mTvCancel;
    public TextView mTvDislike;
    public ImageView mImgLogo;

    public XxCommentDialog(Context context, ICommentListener listener) {
        View view = View.inflate(context, R.layout.dialog_comment, null);
        mLlRoot = view.findViewById(R.id.ll_root);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvLike = view.findViewById(R.id.tv_like);
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mTvDislike = view.findViewById(R.id.tv_dislike);
        mImgLogo = view.findViewById(R.id.img_logo);

        mDialog = new Dialog(context, R.style.style_dialog);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        mTvLike.setOnClickListener(view12 -> {
            listener.like();
            mDialog.dismiss();
        });

        mTvCancel.setOnClickListener(view12 -> {
            mDialog.dismiss();
        });

        mTvDislike.setOnClickListener(view12 -> {
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
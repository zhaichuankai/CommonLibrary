package com.enniu.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.enniu.library.R;
import com.enniu.library.widget.XxTextClickAble;

/**
 * Created By TheRainMan
 * Time:2020/3/25 9:42
 * Email:17839330707@163.com
 * Describe:
 */
public class XxProxyDialog {
    private Dialog mDialog;
    public LinearLayout mLlRoot;
    public TextView mTvTitle;
    public TextView mTvContent;
    public Button mBtnSure;
    public TextView mTvCancel;

    public XxProxyDialog(Context context, IProxyListener listener) {
        View view = View.inflate(context, R.layout.dialog_proxy, null);
        mLlRoot = view.findViewById(R.id.ll_root);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvContent = view.findViewById(R.id.tv_content);
        mBtnSure = view.findViewById(R.id.btn_sure);
        mTvCancel = view.findViewById(R.id.tv_cancel);

        mDialog = new Dialog(context, R.style.style_dialog);
        mDialog.setContentView(view);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);

        String contentText = context.getResources().getString(R.string.string_proxy);
        SpannableString span = new SpannableString(contentText);
        int split1 = contentText.indexOf("《用户协议》");
        int split2 = split1 + 6;

        mTvContent.setHighlightColor(ContextCompat.getColor(context, R.color.colorTransparent));
        mTvContent.setMovementMethod(LinkMovementMethod.getInstance());// 设置点击事件
        // Spanned.SPAN_EXCLUSIVE_EXCLUSIVE: 作用于选中的文字,不作用于选中文字的左右
        span.setSpan(new XxTextClickAble() {
            @Override
            public void onClick(View widget) {
                super.onClick(widget);
                listener.clickProxy();
            }
        }, split1, split2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new XxTextClickAble() {
            @Override
            public void onClick(View widget) {
                super.onClick(widget);
                listener.clickPublic();
            }
        }, split2 + 1, split2 + 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvContent.setText(span);

        TextView tvSure = view.findViewById(R.id.tv_sure);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);

        tvSure.setOnClickListener(view12 -> {
            listener.clickSure();
            mDialog.dismiss();
        });

        tvCancel.setOnClickListener(view12 -> {
            listener.clickCancel();
            mDialog.dismiss();
        });
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public interface IProxyListener {
        void clickProxy();

        void clickPublic();

        void clickSure();

        void clickCancel();
    }
}

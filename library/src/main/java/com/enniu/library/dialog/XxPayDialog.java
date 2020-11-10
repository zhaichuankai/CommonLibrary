package com.enniu.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enniu.library.R;


/**
 * @Description:
 * @Created:2020-11-10
 */

public class XxPayDialog {
    private Dialog mDialog;
    public LinearLayout mLlRoot;
    public TextView mTvTitle;
    public LinearLayout mLlAlipay,mLlWechat;
    public TextView mTvAlipay,mTvAlipayPrice;
    public TextView mTvWeChat,mTvWechatPrice;
    public ImageView mImgAlipay,mImgWeChat;
    public Button mBtnSure;

    public XxPayDialog(Context context, int payType, String money, IPayListener listener) {
        View view = View.inflate(context, R.layout.dialog_pay_method, null);
        View viewLine = view.findViewById(R.id.view_line);

        mLlRoot = view.findViewById(R.id.ll_root);
        mTvTitle = view.findViewById(R.id.tv_title);

        mLlAlipay = view.findViewById(R.id.ll_alipay);
        mTvAlipay = view.findViewById(R.id.tv_alipay);
        mTvAlipayPrice = view.findViewById(R.id.tv_alipay_price);
        mImgAlipay = view.findViewById(R.id.img_alipay);

        mLlWechat = view.findViewById(R.id.ll_wechat);
        mTvWeChat = view.findViewById(R.id.tv_wechat);
        mTvWechatPrice = view.findViewById(R.id.tv_wechat_price);
        mImgWeChat = view.findViewById(R.id.img_wechat);

        mDialog = new Dialog(context, R.style.style_dialog);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        if (payType == 0) {//支付宝
            mLlAlipay.setVisibility(View.VISIBLE);
            mLlWechat.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);

            mImgAlipay.setSelected(true);
            mImgWeChat.setSelected(false);
        } else if (payType == 1) {//微信
            mLlWechat.setVisibility(View.VISIBLE);
            mLlAlipay.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);

            mImgAlipay.setSelected(false);
            mImgWeChat.setSelected(true);
        } else {
            mLlWechat.setVisibility(View.VISIBLE);
            mLlAlipay.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);

            mImgAlipay.setSelected(true);
            mImgWeChat.setSelected(false);
        }

        mTvAlipayPrice.setText(money);
        mTvWechatPrice.setText(money);

        mLlAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgAlipay.setSelected(true);
                mImgWeChat.setSelected(false);
            }
        });

        mLlWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgAlipay.setSelected(false);
                mImgWeChat.setSelected(true);
            }
        });

        mBtnSure.setOnClickListener(view1 -> {
            listener.pay(mImgAlipay.isSelected() ? "ALIPAY" : "WECHAT_PAY");
            mDialog.dismiss();
        });
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public interface IPayListener {
        void pay(String type);
    }
}
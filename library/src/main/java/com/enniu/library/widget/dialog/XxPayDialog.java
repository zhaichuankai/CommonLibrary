package com.enniu.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
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

    public XxPayDialog(Context context, int payType, String money, IPayListener listener) {
        mDialog = new Dialog(context, R.style.style_dialog);
        View view = View.inflate(context, R.layout.dialog_pay_method, null);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        TextView tvAlipayMoney = view.findViewById(R.id.tv_alipay_price);
        TextView tvWechatMoney = view.findViewById(R.id.tv_wechat_price);
        View viewLine = view.findViewById(R.id.view_line);
        LinearLayout llAlipay = view.findViewById(R.id.ll_alipay);
        LinearLayout llWechat = view.findViewById(R.id.ll_wechat);
        ImageView imgAlipay = view.findViewById(R.id.img_alipay);
        ImageView imgWechat = view.findViewById(R.id.img_wechat);
        TextView tvSure = view.findViewById(R.id.tv_pay);

        if (payType == 0) {//支付宝
            llWechat.setVisibility(View.GONE);
            llAlipay.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.GONE);

            imgAlipay.setSelected(true);
            imgWechat.setSelected(false);
        } else if (payType == 1) {//微信
            llWechat.setVisibility(View.VISIBLE);
            llAlipay.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
            imgAlipay.setSelected(false);
            imgWechat.setSelected(true);
        } else {
            llWechat.setVisibility(View.VISIBLE);
            llAlipay.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
            imgAlipay.setSelected(true);
            imgWechat.setSelected(false);
        }

        tvAlipayMoney.setText(money);
        tvWechatMoney.setText(money);

        llAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgAlipay.setSelected(true);
                imgWechat.setSelected(false);
            }
        });

        llWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgAlipay.setSelected(false);
                imgWechat.setSelected(true);
            }
        });

        tvSure.setOnClickListener(view1 -> {
            listener.pay(imgAlipay.isSelected() ? "ALIPAY" : "WECHAT_PAY");
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
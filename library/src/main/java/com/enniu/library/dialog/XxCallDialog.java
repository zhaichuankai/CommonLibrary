package com.enniu.library.dialog;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.enniu.library.R;

/**
 * @Powered:Enniu
 * @Email:17839330707@163.com
 * @Author:TheRainMan
 * @Description:
 * @Created:2020-09-14
 */
public class XxCallDialog extends Dialog {
    private Context mContext;
    public LinearLayout mLlRoot;
    public ImageView mImgLogo;
    public TextView mTvTitle, mTvTop, mTvBottom;
    public LinearLayout mLlWeChat, mLlQQ;
    public ImageView mImgCancel;

    public XxCallDialog(@NonNull Context context) {
        super(context, R.style.style_dialog);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_call, null);
        setContentView(view);

        mLlRoot = view.findViewById(R.id.ll_root);
        mImgLogo = view.findViewById(R.id.img_logo);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvTop = view.findViewById(R.id.tv_top);
        mTvBottom = view.findViewById(R.id.tv_bottom);
        mLlWeChat = view.findViewById(R.id.ll_wechat);
        mLlQQ = view.findViewById(R.id.ll_qq);
        mImgCancel = view.findViewById(R.id.img_cancel);

        mLlWeChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinWechat();
                dismiss();
            }
        });

        mLlQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinQQ();
                dismiss();
            }
        });

        mImgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    private void joinWechat() {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", "rhinox01");
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);

        try {
            Toast.makeText(mContext, "微信号已复制", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");

            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext, "已复制，请下载微信", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 跳转QQ聊天界面
     */
    public void joinQQ() {
        try {
            //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=1404556846";//uin是发送过去的qq号码
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            e.printStackTrace();
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", "1404556846");
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            Toast.makeText(mContext, "已复制，请下载QQ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

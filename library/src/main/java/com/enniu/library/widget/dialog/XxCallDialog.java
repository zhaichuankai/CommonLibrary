package com.enniu.library.widget.dialog;

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
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.enniu.library.R;
import com.enniu.library.base.XxBaseActivity;

/**
 * @Powered:Enniu
 * @Email:17839330707@163.com
 * @Author:TheRainMan
 * @Description:
 * @Created:2020-09-14
 */
public class XxCallDialog extends Dialog {
    private XxBaseActivity mActivity;
    private LinearLayout llWechat,llQQ;
    private ImageView imgCancel;

    public XxCallDialog(@NonNull XxBaseActivity activity) {
        super(activity, R.style.style_dialog);
        this.mActivity = activity;
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_call, null);
        setContentView(view);

        llWechat = view.findViewById(R.id.ll_wechat);
        llQQ = view.findViewById(R.id.ll_qq);
        imgCancel = view.findViewById(R.id.img_cancel);

        llWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinWechat();
                dismiss();
            }
        });

        llQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinQQ();
                dismiss();
            }
        });

        imgCancel.setOnClickListener(new View.OnClickListener() {
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
        ClipboardManager cm = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", "rhinox01");
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);

        try {
            Toast.makeText(mActivity, "微信号已复制", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");

            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            mActivity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mActivity, "已复制，请下载微信", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 跳转QQ聊天界面
     */
    public void joinQQ() {
        try {
            //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=1404556846";//uin是发送过去的qq号码
            mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            e.printStackTrace();
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", "1404556846");
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            Toast.makeText(mActivity, "已复制，请下载QQ", Toast.LENGTH_SHORT).show();
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

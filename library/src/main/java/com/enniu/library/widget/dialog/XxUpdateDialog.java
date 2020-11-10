package com.enniu.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.enniu.library.R;

/**
 * 🙏 GOD BLESS NO BUG !!! 🙏
 * Author： vieboo
 * Date： 2019-12-22 16:09
 * Description：
 */
public class XxUpdateDialog extends Dialog {
    private Context mContext;
    private TextView tvVersion, tvContent, btnUpdate;
    private ImageView imgCancel;
    private String linkUrl;
    private String version;

    public XxUpdateDialog(@NonNull Context context) {
        super(context, R.style.style_dialog);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
        setContentView(view);

        tvVersion = view.findViewById(R.id.tvVersion);
        tvContent = view.findViewById(R.id.tvContent);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        imgCancel = view.findViewById(R.id.img_cancel);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(linkUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
                dismiss();
            }
        });
    }

    public XxUpdateDialog setVersion(String version) {
        this.version=version;
        tvVersion.setText("【v" + version + "】新版上线");
        return this;
    }

    public void setLink(String link) {
        this.linkUrl = link;
    }

    public XxUpdateDialog setContent(String content) {
        tvContent.setText(content);
        return this;
    }

    public XxUpdateDialog isForce(boolean isForce) {
        if (isForce) {
            imgCancel.setVisibility(View.GONE);
        } else {
            imgCancel.setVisibility(View.VISIBLE);
            imgCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

        setCanceledOnTouchOutside(!isForce);
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

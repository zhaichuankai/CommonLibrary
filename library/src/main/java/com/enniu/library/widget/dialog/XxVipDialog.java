package com.enniu.library.widget.dialog;

import android.app.Dialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.enniu.library.R;
import com.enniu.library.base.XxBaseActivity;

/**
 * @Description:
 * @Created:2020-11-10
 */
public class XxVipDialog extends Dialog {
    private RelativeLayout rlTop, rlBottom;
    private ImageView imgCancel;

    public XxVipDialog(@NonNull XxBaseActivity activity, IXxVipListener listener) {
        super(activity, R.style.style_dialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_deblocking, null);
        setContentView(view);

        rlTop = view.findViewById(R.id.rl_top);
        rlBottom = view.findViewById(R.id.rl_bottom);
        imgCancel = view.findViewById(R.id.img_cancel);

        rlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickTop();
                dismiss();
            }
        });

        rlBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickBottom();
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

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface IXxVipListener{
        void clickTop();

        void clickBottom();
    }
}

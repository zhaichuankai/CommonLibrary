package com.enniu.library.widget;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

// XxTextClickAble 实现类
public class XxTextClickAble extends ClickableSpan {

    @Override
    public void onClick(View widget) {
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.parseColor("#41A4FF"));// 高亮文字颜色
        ds.setUnderlineText(false);// 不要下划线
        ds.clearShadowLayer();
    }
}

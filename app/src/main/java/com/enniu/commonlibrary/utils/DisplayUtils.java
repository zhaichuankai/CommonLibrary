package com.enniu.commonlibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.enniu.commonlibrary.base.BaseApplication;

import java.lang.reflect.Method;


/**
 * Created by Administrator on 2016/5/11.
 *
 */
public class DisplayUtils {

    private static float mScale = 0.0f; // 密度
    private static int mDpi = 0; // dpi
    private static float mFontScale = 0.0f;
    private static int mWidthPixels = 0;
    private static int mHeightPixels = 0;

    public static void init() {
        DisplayMetrics displaysMetrics = new DisplayMetrics();// 初始化一个结构
        WindowManager wm = (WindowManager) BaseApplication.mInstance.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displaysMetrics);// 对该结构赋值
        mWidthPixels = displaysMetrics.widthPixels;
        mHeightPixels = displaysMetrics.heightPixels;
        DisplayMetrics dm = BaseApplication.mInstance.getResources().getDisplayMetrics();
        mScale = dm.density;
        mDpi = dm.densityDpi;
        mFontScale = dm.scaledDensity;

    }

    /**
     * dip转px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        if (mScale == 0) {
            init();
        }
        return (int) (dpValue * mScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        if (mScale == 0) {
            init();
        }
        return (int) (pxValue / mScale + 0.5f);
    }

    /**
     * 得到的屏幕的宽度
     */
    public static int getWidthPx() {
        // DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸
        if (mWidthPixels == 0) {
            init();
        }
        return mWidthPixels;
    }

    /**
     * 得到的屏幕的高度
     */
    public static int getHeightPx() {
        // DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸
        if (mHeightPixels == 0) {
            init();
        }
        return mHeightPixels;
    }

    /**
     * 得到屏幕的dpi
     */
    public static int getDensityDpi() {
        // DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸
        if (mDpi == 0) {
            init();
        }
        return mDpi;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try{
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


    public static int px2sp(float pxValue) {
        if (mFontScale == 0) {
            init();
        }
        return (int) (pxValue / mFontScale + 0.5f);
    }

    public static int getVirtualBarHeight(Context context) {
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - display.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     */
    public static int sp2px(float spValue) {
        if (mFontScale == 0) {
            init();
        }
        return (int) (spValue * mFontScale + 0.5f);
    }

    /**
     * 获取是否存在NavigationBar
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }
}

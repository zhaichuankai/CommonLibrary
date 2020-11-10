package com.enniu.library.base;

import android.app.Application;

import com.enniu.library.utils.XxUtils;

public class XxBaseApplication extends Application {
    public static XxBaseApplication mInstance;
    public AccountInfo mAccountInfo;
    public boolean mIsDebug = false;
    public boolean mIsLogin = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAccountInfo = new AccountInfo();

        XxUtils.init(this);
    }

    public static class AccountInfo {
        public String mUserId;
        public String mToken;
        public String mPhoneNum;
        public String mNickName;
        public String mMobile;
        public String mAvatar;
        public String mLogoffTime;
        public String mVipType;
        public String mVipExpireTime;

        public void clear() {
            mUserId = "";
            mToken = "";
            mNickName = "";
            mAvatar = "";
            mMobile = "";
            mPhoneNum = "";
            mVipType = "";
            mVipExpireTime = "";
        }
    }
}

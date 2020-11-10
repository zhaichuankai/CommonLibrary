package com.enniu.library.entity.params;

import android.text.TextUtils;

import com.enniu.library.base.XxBaseApplication;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 🙏 GOD BLESS NO BUG !!! 🙏
 * Author： vieboo
 * Date： 2020-03-18 14:46
 * Description：
 */
public class XxBaseBodyParams implements XxIBaseParams {

    protected String accountId;
    protected String timestamp;

    public XxBaseBodyParams() {
        this.accountId = TextUtils.isEmpty(XxBaseApplication.mInstance.mAccountInfo.mUserId) ? null : XxBaseApplication.mInstance.mAccountInfo.mUserId;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    @Override
    public RequestBody toBody() {
        String json = new Gson().toJson(this);
        return FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", accountId);
        params.put("timestamp", timestamp);
        return params;
    }


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

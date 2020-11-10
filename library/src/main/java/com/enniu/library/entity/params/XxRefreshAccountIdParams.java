package com.enniu.library.entity.params;

import android.content.Context;

/**
 * @Description:
 * @Created:2020-10-23
 */
public class XxRefreshAccountIdParams extends XxLoginParams {

    private String mobile;
    private String token;

    public XxRefreshAccountIdParams(String mobile, String token) {
        this.mobile = mobile;
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.enniu.library.entity.params;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 🙏 GOD BLESS NO BUG !!! 🙏
 * Author： vieboo
 * Date： 2020-03-25 10:10
 * Description：
 */
public class XxNoLoginParams implements XxIBaseParams {

    protected String appName;
    protected String brand;
    protected String channel;
    protected String deviceModel;
    protected String deviceType;
    protected String uuid;
    protected String version;
    private String oaid;
    private String androidId;

    @Override
    public RequestBody toBody() {
        String json = new Gson().toJson(this);
        return FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }

    @Override
    public Map<String, Object> toMap() {
        return null;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

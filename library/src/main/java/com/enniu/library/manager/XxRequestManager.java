package com.enniu.library.manager;

import android.widget.Toast;

import com.enniu.library.base.XxBaseActivity;
import com.enniu.library.base.XxBaseApplication;
import com.enniu.library.entity.bean.XxBaseResultBean;
import com.enniu.library.entity.bean.XxBaseResultStringBean;
import com.enniu.library.entity.event.XxGoLoginEvent;
import com.enniu.library.entity.event.XxLoginStatusEvent;
import com.enniu.library.entity.params.XxRefreshAccountIdParams;
import com.enniu.library.ilistener.XxIRequestListener;
import com.enniu.library.utils.XxJsonUtil;
import com.enniu.library.utils.XxNetUtils;
import com.enniu.library.utils.XxSPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import okhttp3.RequestBody;

public class XxRequestManager {
    private XxIRequestListener mListener;
    private String mUrl;

    public XxRequestManager(String url) {
        mUrl = url;
    }

    public void doPost(XxBaseActivity activity, String url, RequestBody body, XxIRequestListener listener, boolean showLoading) {
        mListener = listener;

        if (!XxNetUtils.isNetworkConnected(activity)) {
            Toast.makeText(activity, "网络未连接，请您检查网络后重试!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (showLoading) {
            activity.showLoading();
        }

        OkGo.<String>post(url)
                .upRequestBody(body)
                .tag(activity)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null != listener) {
                            XxBaseResultBean result = XxJsonUtil.parse(response.body(), XxBaseResultBean.class);
                            if (result.isSuccess()) {
                                listener.onRequestSuccess(response.body());
                            } else if (result.getErrorCode().contains("9990")) {
                                getNewAccountId(activity);
                            } else if (result.getErrorCode().contains("9991")) {
                                OkGo.getInstance().cancelAll();
                                EventBus.getDefault().post(new XxGoLoginEvent());
                            } else {
                                Toast.makeText(activity, result.getErrorMsg(), Toast.LENGTH_LONG).show();
                            }

                            if (showLoading) {
                                activity.dismissLoading();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (showLoading) {
                            activity.dismissLoading();
                        }
                    }
                });
    }

    public void doUpload(XxBaseActivity activity, String url, RequestBody body, XxIRequestListener listener, boolean showLoading) {
        mListener = listener;

        if (!XxNetUtils.isNetworkConnected(activity)) {
            Toast.makeText(activity, "网络未连接，请您检查网络后重试!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (showLoading) {
            activity.showLoading();
        }

        OkGo.<String>post(url)
                .upRequestBody(body)
                .tag(activity)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null != listener) {
                            XxBaseResultBean result = XxJsonUtil.parse(response.body(), XxBaseResultBean.class);
                            if (result.isSuccess()) {
                                listener.onRequestSuccess(response.body());
                            } else if (result.getErrorCode().contains("9990")) {
                                getNewAccountId(activity);
                            } else if (result.getErrorCode().contains("9991")) {
                                OkGo.getInstance().cancelAll();
                                EventBus.getDefault().post(new XxGoLoginEvent());
                            } else {
                                //其他错误
                            }

                            if (showLoading) {
                                activity.dismissLoading();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (showLoading) {
                            activity.dismissLoading();
                        }
                    }
                });
    }

    private void getNewAccountId(XxBaseActivity activity) {
            OkGo.<String>post(mUrl)
                    .upRequestBody(new XxRefreshAccountIdParams(XxBaseApplication.mInstance.mAccountInfo.mPhoneNum, XxBaseApplication.mInstance.mAccountInfo.mToken).toBody())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            XxBaseResultStringBean result = XxJsonUtil.parse(response.body(), XxBaseResultStringBean.class);
                            if (result.isSuccess()) {
                                XxBaseApplication.mInstance.mAccountInfo.mUserId= result.getResult();
                                XxSPUtils.getInstance().put("user_id", result.getResult());
                                mListener.onRequestExpired();
                            }else if (result.getErrorCode().contains("9991")) {
                                OkGo.getInstance().cancelAll();
                                XxSPUtils.getInstance().put("user_id", "");
                                XxSPUtils.getInstance().put("token", "");
                                XxSPUtils.getInstance().put("phone_num", "");

                                XxBaseApplication.mInstance.mIsLogin = false;
                                XxBaseApplication.mInstance.mAccountInfo.clear();
                                EventBus.getDefault().post(new XxLoginStatusEvent());
                                EventBus.getDefault().post(new XxGoLoginEvent());

                                Toast.makeText(activity, "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
                            } else {
                                //其他错误
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                        }
                    });
        }
    }

package com.enniu.library.entity.bean;

/**
 * @Powered:Enniu
 * @Email:17839330707@163.com
 * @Author:TheRainMan
 * @Description:
 * @Created:2020-10-23
 */
public class XxBaseResultStringBean {

    /**
     * errorCode : string
     * errorMsg : string
     * errorStack : string
     * result : string
     * returnCode : string
     * success : true
     * timeOut : true
     */

    private String errorCode;
    private String errorMsg;
    private String errorStack;
    private String result;
    private String returnCode;
    private boolean success;
    private boolean timeOut;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isTimeOut() {
        return timeOut;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }
}

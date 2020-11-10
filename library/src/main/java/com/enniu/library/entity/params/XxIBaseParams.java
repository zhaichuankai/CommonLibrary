package com.enniu.library.entity.params;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @Description:
 * @Created:2020-10-23
 */
public interface XxIBaseParams {

    RequestBody toBody();

    Map<String, Object> toMap();
}

package com.dianping.dzopen.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class RequestUtil {

    /**
     * Map转URL的参数串。
     *
     * @param params 请求参数
     * @return param string
     */
    public static String mapToGetParam(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
        }
        return sb.toString();
    }
}

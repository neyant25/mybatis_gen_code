package com.company.project.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;

public class ContextHelper {

    private static final Logger logger = Logger.getLogger(ContextHelper.class);

    private static HttpServletRequest getReqeust() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static String getDomin() {
        HttpServletRequest request = getReqeust();
        return request.getLocalAddr() + ":" + request.getLocalPort();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getPostData(Class<?> clazz) {
        String jsonText = getPostData();
        logger.info("系统收到数据》》" + jsonText);
        return (T) JSON.parseObject(jsonText, clazz);
    }

    public static String getPostData() {
        HttpServletRequest request = getReqeust();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                request.getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public static JSONObject getRequestData() {
        HttpServletRequest request = getReqeust();
        Map<String, String[]> paramters = request.getParameterMap();
        JSONObject json = new JSONObject();
        for (Entry<String, String[]> entry : paramters.entrySet()) {
            json.put(entry.getKey(), getValue(entry.getValue()[0]));
        }
        return json;
    }

    private static Object getValue(String value) {
        if (value.startsWith("[") && value.endsWith("]")) {
            return JSON.parseArray(value);
        }
        if (value.startsWith("{") && value.endsWith("}")) {
            return JSON.parseObject(value);
        }
        return value;

    }

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @return
     * @throws IOException
     */
    public static String getIpAddress() throws IOException {
        HttpServletRequest request = getReqeust();
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}

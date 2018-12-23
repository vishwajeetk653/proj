package com.svladiko.services.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Влад on 07.10.2015.
 */
public class Generate {
    private static final String prefix = "/WEB-INF/pages/";
    private static final String suffix = ".jsp";
    public static HttpServletRequest req;

    static  public String getUrl(String param) {
        return prefix + param + suffix;
    }
    static  public String getParam(String param) {

        return req.getParameter(param);
    }
}

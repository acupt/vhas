package com.swust.vhas.util;

import com.google.gson.Gson;

/**
 * Created by liujie on 2017/5/13.
 */
public class JsonUtil {
    private static Gson gson = new Gson();

    public static String t(Object object) {
        return gson.toJson(object);
    }
}

package com.xpectra.tvmaze.models.interfaces;

/**
 * Created by xpectra on 6/4/2017.
 */
public interface ApiListener {
    void successApi(Object result);
    void errorApi(int code);
}

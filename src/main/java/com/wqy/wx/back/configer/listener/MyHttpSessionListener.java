package com.wqy.wx.back.configer.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("sessionCreated被调用");
        online++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("sessionDestroyed被调用");
        online--;
    }
}

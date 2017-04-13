package com.chy.videotutorial.base.entity;

import java.io.Serializable;

/**
 * Created by 丁飞 on 2016/1/7.
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 缓存标识
     */
    protected transient String cacheKey;

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }
}
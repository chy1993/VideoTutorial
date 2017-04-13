package com.chy.videotutorial.base.entity;

import java.io.Serializable;

/**
 * 实体类的基类
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
package com.chy.videotutorial.Utils;

/**
 * 通用的接口返回数据
 */
public class ApiResponse<T> {
    private boolean success;
    private String  message;
    private T  data;
    private String  currentPage;
    private String  totalPages; // 目前为总记录条数

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
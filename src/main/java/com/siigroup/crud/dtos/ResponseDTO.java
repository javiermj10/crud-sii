package com.siigroup.crud.dtos;

public class ResponseDTO<T>{
    private String message;
    private boolean result;
    private T data;

    public ResponseDTO() {
    }

    public ResponseDTO(String message, boolean result, T data) {
        this.message = message;
        this.result = result;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

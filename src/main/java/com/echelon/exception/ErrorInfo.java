package com.echelon.exception;

/**
 * Created by Fahad Qureshi on 4/18/2017.
 */
public class ErrorInfo {
    private String exceptionMessage;
    private String path;

    public ErrorInfo() {
    }

    public ErrorInfo(String path, String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
        this.path = path;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

package com.echelon.exception.validation;

/**
 * Created by Fahad Qureshi on 4/24/2017.
 */
public class FieldErrorInfo {
    private String entity;
    private String feild;
    private String rejectedValue;
    private String fieldErrorMsg;

    public FieldErrorInfo() {
    }

    public FieldErrorInfo(String entity, String feild, String rejectedValue, String fieldErrorMsg) {
        this.entity = entity;
        this.feild = feild;
        this.rejectedValue = rejectedValue;
        this.fieldErrorMsg = fieldErrorMsg;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getFeild() {
        return feild;
    }

    public void setFeild(String feild) {
        this.feild = feild;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getFieldErrorMsg() {
        return fieldErrorMsg;
    }

    public void setFieldErrorMsg(String fieldErrorMsg) {
        this.fieldErrorMsg = fieldErrorMsg;
    }
}

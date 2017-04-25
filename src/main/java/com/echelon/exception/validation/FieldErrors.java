package com.echelon.exception.validation;

import com.echelon.exception.ErrorInfo;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fahad Qureshi on 4/24/2017.
 */
public class FieldErrors extends ErrorInfo {
    private List<FieldErrorInfo> fieldErrorInfoList;

    public FieldErrors() {
    }

    public FieldErrors(String path, String exceptionMessage) {
        super(path, exceptionMessage);
        this.fieldErrorInfoList = new ArrayList<>();
    }

    public List<FieldErrorInfo> getFieldErrorInfoList() {
        return fieldErrorInfoList;
    }

    public void saveFieldErrors (List<FieldError> errors) {
        if (errors != null) {
            errors.forEach(error -> fieldErrorInfoList.add(getFieldErrorInfo(error)));
        }
    }
    private FieldErrorInfo getFieldErrorInfo (FieldError error) {
        String msg = error.getField() + " " + error.getDefaultMessage();
        String rv = error.getRejectedValue() != null?
                error.getRejectedValue().toString():
                null;
        return new FieldErrorInfo(
                error.getObjectName(),
                error.getField(),
                rv,
                msg);
    }
}

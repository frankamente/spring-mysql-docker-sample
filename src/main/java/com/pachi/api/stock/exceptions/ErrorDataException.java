package com.pachi.api.stock.exceptions;

import com.pachi.api.stock.exceptions.entity.SubErrors;

import java.util.List;

public interface ErrorDataException {
    String getErrorCode();

    void setErrorCode(String errorCode);

    String getDescription();

    void setDescription(String description);

    String getDetail();

    void setDetail(String detail);

    List<SubErrors> getSubErrors();

    void setSubErrors(List<SubErrors> subErrors);
}

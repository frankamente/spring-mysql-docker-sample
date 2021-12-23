package com.pachi.api.stock.exceptions;

import com.pachi.api.stock.exceptions.entity.SubErrors;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class GenericRuntimeException extends RuntimeException implements ErrorDataException {
    private static final long serialVersionUID = -1004380058480758857L;
    private String errorCode;
    private String description;
    private String detail;
    private List<SubErrors> subErrors;

    public GenericRuntimeException() {
    }

    public GenericRuntimeException(Throwable cause) {
        super(cause);
    }
}

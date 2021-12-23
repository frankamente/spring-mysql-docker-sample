package com.pachi.api.stock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * StockNotFoundException.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockNotFoundException extends GenericRuntimeException {

    private static final long serialVersionUID = -7860143923165246707L;

    /**
     * StockNotFoundException.
     *
     * @param id The Id of the Employee
     */
    public StockNotFoundException(final Long id) {
        setErrorCode("00404");
        setDescription("Not found.");
        setDetail(String.format("Not found stock with id: %s", id));
    }
}

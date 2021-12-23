package com.pachi.api.stock.dto;

import java.io.Serializable;

public interface Dto<I> extends Serializable {
    I getId();

    void setId(I id);
}
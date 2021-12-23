package com.pachi.api.stock.exceptions.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@ToString
@EqualsAndHashCode
public class SubErrors implements Serializable {
    private static final long serialVersionUID = 2694940109703247198L;
    private final UUID id;
    private final String code;
    private final String description;

    public SubErrors(String code, String description) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.description = description;
    }
}

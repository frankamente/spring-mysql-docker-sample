package com.pachi.api.stock.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@Data
public class CreateStockDto implements Serializable {

    private String name;
}

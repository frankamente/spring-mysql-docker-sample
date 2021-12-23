package com.pachi.api.stock.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class StockDto implements Dto<Long> {

    private Long id;
    private String name;
}

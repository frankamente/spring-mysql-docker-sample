package com.pachi.api.stock.mapper;

import com.pachi.api.stock.dto.CreateStockDto;
import com.pachi.api.stock.dto.StockDto;
import com.pachi.api.stock.entity.Stock;
import org.mapstruct.Mapper;

/**
 * StockMapper.
 */
@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDto entityToDto(Stock entity);

    Stock createDtoToEntity(CreateStockDto dto);

    Stock dtoToEntity(StockDto dto);
}

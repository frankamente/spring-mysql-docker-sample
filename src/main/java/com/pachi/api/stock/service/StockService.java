package com.pachi.api.stock.service;


import com.pachi.api.stock.dto.CreateStockDto;
import com.pachi.api.stock.dto.StockDto;
import com.pachi.api.stock.entity.Stock;
import com.pachi.api.stock.exceptions.StockNotFoundException;
import com.pachi.api.stock.mapper.StockMapper;
import com.pachi.api.stock.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository repository;

    private final StockMapper mapper;

    public StockDto create(CreateStockDto createStockDto) {
        return mapper.entityToDto(repository.save(mapper.createDtoToEntity(createStockDto)));
    }

    public StockDto update(StockDto stockDtoToUpdate) {
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(stockDtoToUpdate)));
    }

    public void delete(Long id) {
        repository.delete(findStock(id));
    }

    public StockDto find(Long id) {
        return mapper.entityToDto(findStock(id));
    }

    private Stock findStock(Long id) {
        return repository.findById(id).orElseThrow(() -> new StockNotFoundException(id));
    }

    public Page<StockDto> getStocks(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }
}

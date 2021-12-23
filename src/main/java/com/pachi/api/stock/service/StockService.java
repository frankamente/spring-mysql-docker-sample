package com.pachi.api.stock.service;


import com.pachi.api.stock.entity.Stock;
import com.pachi.api.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }
}

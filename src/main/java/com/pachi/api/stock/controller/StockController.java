package com.pachi.api.stock.controller;

import com.pachi.api.stock.entity.Stock;
import com.pachi.api.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping(path = "")
    ResponseEntity<List<Stock>> stock() {
        return ResponseEntity.ok(stockService.getStocks());
    }
}

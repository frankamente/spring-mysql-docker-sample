package com.pachi.api.stock.controller;

import com.pachi.api.stock.builders.ResponseEntityBuilder;
import com.pachi.api.stock.core.jackson.JacksonSerializer;
import com.pachi.api.stock.dto.CreateStockDto;
import com.pachi.api.stock.dto.StockDto;
import com.pachi.api.stock.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/stock")
@AllArgsConstructor
@Slf4j
public class StockController implements StockControllerDocumentation {

    private final StockService stockService;
    private final ResponseEntityBuilder<StockDto, Long> responseEntity;
    private final JacksonSerializer<CreateStockDto> createStockDtoJacksonSerializer;
    private final JacksonSerializer<StockDto> jacksonSerializer;
    private final JacksonSerializer<Page<StockDto>> pagesJacksonSerializer;

    @Override
    public ResponseEntity<StockDto> newStock(CreateStockDto createStockDto) {

        if (log.isInfoEnabled()) {
            log.info("[Start] - StockController.newStock {}",
                    createStockDtoJacksonSerializer.getObjectAsString(createStockDto));
        }
        StockDto stockDto = stockService.create(createStockDto);

        if (log.isInfoEnabled()) {
            log.info("[End] - StockController.newStock {}",
                    jacksonSerializer.getObjectAsString(stockDto));
        }
        return responseEntity.buildPostMappingResponse(stockDto);
    }

    @Override
    public ResponseEntity<StockDto> updateStock(StockDto stockDtoToUpdate, Long id) {

        if (log.isInfoEnabled()) {
            log.info("[Start] - StockController.updateStock {}",
                    jacksonSerializer.getObjectAsString(stockDtoToUpdate));
        }
        StockDto stockDto = stockService.update(stockDtoToUpdate);

        if (log.isInfoEnabled()) {
            log.info("[End] - StockController.updateStock {}",
                    jacksonSerializer.getObjectAsString(stockDto));
        }
        return responseEntity.buildPostMappingResponse(stockDto);
    }

    @Override
    public ResponseEntity<Void> deleteStock(Long id) {
        log.info("[Start] - StockController.deleteStock {}", id.toString());

        stockService.delete(id);

        log.info("[End] - StockController.deleteStock");
        return responseEntity.buildDeleteMappingResponse();
    }

    @Override
    public ResponseEntity<StockDto> findStock(Long id) {
        log.info("[Start] - StockController.findStock {}", id.toString());

        StockDto stockDto = stockService.find(id);

        if (log.isInfoEnabled()) {
            log.info("[End] - StockController.findStock {}",
                    jacksonSerializer.getObjectAsString(stockDto));
        }
        return null;
    }

    @Override
    public ResponseEntity<Page<StockDto>> getStocks(boolean unPaged, Pageable pageable) {
        log.info("[Start] - StockController.getStocks");

        Page<StockDto> stocks = stockService.getStocks(unPaged ? Pageable.unpaged() : pageable);

        if (log.isInfoEnabled()) {
            log.info("[End] - StockController.getStocks {}",
                    pagesJacksonSerializer.getObjectAsString(stocks));
        }
        return responseEntity.buildGetCollectionPageMappingResponse(
                stocks);
    }
}

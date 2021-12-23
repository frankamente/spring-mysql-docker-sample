package com.pachi.api.stock.controller;

import com.pachi.api.stock.dto.CreateStockDto;
import com.pachi.api.stock.dto.StockDto;
import com.pachi.api.stock.exceptions.entity.RestErrorEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;

@Tag(name = "Stock API")
public interface StockControllerDocumentation {

    @Operation(
            description = "Create a new entity of Stock",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created successfully.",
                            content = @Content(schema = @Schema(implementation = StockDto.class)),
                            headers = {@Header(name = "Stock", description = "New Stock resource URI")}),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error. Couldn't create new resource",
                            content = @Content(schema = @Schema(implementation = RestErrorEntity.class)))
            })
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<StockDto> newStock(
            @Parameter(
                    description = "Stock recurso a crear",
                    required = true,
                    schema = @Schema(implementation = CreateStockDto.class))
            @RequestBody
            @Valid
            @NotNull final CreateStockDto createStockDto) throws URISyntaxException;

    @Operation(
            description = "Update Stock resource",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Request ok. See body to see response.",
                            content = @Content(schema = @Schema(implementation = StockDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content(schema = @Schema(implementation = RestErrorEntity.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error.",
                            content = @Content(schema = @Schema(implementation = RestErrorEntity.class)))
            })
    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<StockDto> updateStock(
            @Parameter(
                    description = "Stock data to update.",
                    required = true,
                    schema = @Schema(implementation = StockDto.class))
            @RequestBody
            @Valid
            @NotNull final StockDto stockDto,
            @Parameter(description = "Stock Id", required = true)
            @PathVariable
            @Valid
            @NotNull final Long id);

    @Operation(
            description = "Delete Stock resource from system.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content. Deleted corrected.",
                            content = @Content(schema = @Schema(implementation = Void.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found.",
                            content = @Content(schema = @Schema(implementation = RestErrorEntity.class)))
            })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteStock(
            @Parameter(description = "Stock Identificador", required = true)
            @PathVariable
            @Valid
            @NotNull final Long id);

    @Operation(
            description = "Get Stock by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Request ok. See body to see response.",
                            content = @Content(schema = @Schema(implementation = StockDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found.",
                            content = @Content(schema = @Schema(implementation = RestErrorEntity.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error.",
                            content = @Content(schema = @Schema(implementation = RestErrorEntity.class)))
            })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<StockDto> findStock(
            @Parameter(description = "Stock Identificador", required = true)
            @PathVariable
            @Valid
            @NotNull final Long id);

    @Operation(
            description = "Get Stock resources list.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Request ok. See body to see response.",
                            content =
                            @Content(array = @ArraySchema(schema = @Schema(implementation = StockDto.class)))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error.",
                            content = @Content(schema = @Schema(implementation = RestErrorEntity.class)))
            })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<StockDto>> getStocks(
            @RequestParam(name = "unPaged", required = false) boolean unPaged,
            @PageableDefault(size = 20) @ParameterObject Pageable pageable);
}

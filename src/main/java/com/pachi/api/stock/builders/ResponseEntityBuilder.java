package com.pachi.api.stock.builders;

import com.pachi.api.stock.dto.Dto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ResponseEntityBuilder<D extends Dto<I>, I> {
    ResponseEntity<D> buildPostMappingResponse(@Valid @NotNull D dtoToResponse);

    ResponseEntity<List<D>> buildPostCollectionMappingResponse(@Valid @NotNull List<D> listToResponse);

    ResponseEntity<Page<D>> buildPostCollectionPageMappingResponse(@Valid @NotNull Page<D> page);

    ResponseEntity<D> buildPutMappingResponse(@Valid @NotNull D dtoToResponse);

    ResponseEntity<D> buildPatchMappingResponse(@Valid @NotNull D dtoToResponse);

    ResponseEntity<Void> buildDeleteMappingResponse();

    ResponseEntity<D> buildGetMappingResponse(@Valid @NotNull D dtoToResponse);

    ResponseEntity<List<D>> buildGetCollectionMappingResponse(@Valid @NotNull List<D> listToResponse);

    ResponseEntity<Page<D>> buildGetCollectionPageMappingResponse(@Valid @NotNull Page<D> page);

    ResponseEntity<D> buildActionMappingResponse(@Valid @NotNull D dtoToResponse, @Valid @NotNull HttpStatus status);

    ResponseEntity<D> buildActionMappingResponse(@Valid @NotNull HttpStatus status);
}

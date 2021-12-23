package com.pachi.api.stock.builders;

import com.pachi.api.stock.dto.Dto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Validated
@Component
@Scope("prototype")
public class ResponseEntityBuilderImpl<D extends Dto<I>, I> implements ResponseEntityBuilder<D, I> {
    public static final String OBJECT_ID_REQUIRED = "Object with Id required in the response";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String CACHE_CONTROL_VALUE = "no store, private, max-age=0";
    private static final String TOTAL_COUNT = "Total-Count";
    private static final String TOTAL_PAGES = "Total-Pages";
    private static final String NUMBER_OF_ELEMENTS = "Number-Of-Elements";
    private static final String ZERO = "0";

    public ResponseEntityBuilderImpl() {
    }

    public ResponseEntity<D> buildPostMappingResponse(@Valid @NotNull D dtoToResponse) {
        if (!Optional.ofNullable(dtoToResponse.getId()).isPresent()) {
            throw new RuntimeException("Object with Id required in the response");
        } else {
            try {
                return ((ResponseEntity.BodyBuilder) ResponseEntity.created(new URI(String.format("%s/%s", ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString(), dtoToResponse.getId()))).headers(this.getResponseHeaders())).body(dtoToResponse);
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public ResponseEntity<List<D>> buildPostCollectionMappingResponse(@Valid @NotNull List<D> listToResponse) {
        return this.createCollectionMappingResponse(listToResponse);
    }

    public ResponseEntity<Page<D>> buildPostCollectionPageMappingResponse(@Valid @NotNull Page<D> page) {
        return this.createCollectionPageMappingResponse(page);
    }

    public ResponseEntity<D> buildPutMappingResponse(@Valid @NotNull D dtoToResponse) {
        if (!Optional.ofNullable(dtoToResponse.getId()).isPresent()) {
            throw new RuntimeException("Object with Id required in the response");
        } else {
            try {
                HttpHeaders responsePutHeaders = this.addResponseHeaders(new HttpHeaders());
                responsePutHeaders.setLocation(new URI(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString()));
                return ((ResponseEntity.BodyBuilder) ResponseEntity.ok().headers(responsePutHeaders)).body(dtoToResponse);
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public ResponseEntity<D> buildPatchMappingResponse(@Valid @NotNull D dtoToResponse) {
        return this.buildPutMappingResponse(dtoToResponse);
    }

    public ResponseEntity<Void> buildDeleteMappingResponse() {
        return ResponseEntity.noContent().headers(this.getResponseHeaders()).build();
    }

    public ResponseEntity<D> buildGetMappingResponse(@Valid @NotNull D dtoToResponse) {
        ResponseEntity response;
        if (Optional.ofNullable(dtoToResponse.getId()).isPresent()) {
            response = new ResponseEntity(dtoToResponse, this.getResponseHeaders(), HttpStatus.OK);
        } else {
            response = ResponseEntity.notFound().headers(this.getResponseHeaders()).build();
        }

        return response;
    }

    public ResponseEntity<List<D>> buildGetCollectionMappingResponse(@Valid @NotNull List<D> listToResponse) {
        return this.createCollectionMappingResponse(listToResponse);
    }

    public ResponseEntity<Page<D>> buildGetCollectionPageMappingResponse(@Valid @NotNull Page<D> page) {
        return this.createCollectionPageMappingResponse(page);
    }

    public ResponseEntity<D> buildActionMappingResponse(@Valid @NotNull D dtoToResponse, @Valid @NotNull HttpStatus status) {
        return ((ResponseEntity.BodyBuilder) ResponseEntity.status(status).headers(this.getResponseHeaders())).body(dtoToResponse);
    }

    public ResponseEntity<D> buildActionMappingResponse(@Valid @NotNull HttpStatus status) {
        return ((ResponseEntity.BodyBuilder) ResponseEntity.status(status).headers(this.getResponseHeaders())).build();
    }

    private HttpHeaders getResponseHeaders() {
        return this.addResponseHeaders(new HttpHeaders());
    }

    private HttpHeaders addResponseHeaders(@Valid @NotNull HttpHeaders responseHeaders) {
        responseHeaders.add("Cache-Control", "no store, private, max-age=0");
        responseHeaders.add("Content-Type", "application/json");
        return responseHeaders;
    }

    private ResponseEntity<List<D>> createCollectionMappingResponse(@Valid @NotNull final List<D> listToResponse) {
        HttpHeaders responseListHeaders = this.addResponseHeaders(new HttpHeaders());
        ResponseEntity response;
        if (CollectionUtils.isNotEmpty(listToResponse)) {
            responseListHeaders.set("Total-Pages", "1");
            responseListHeaders.set("Total-Count", String.valueOf(listToResponse.size()));
            responseListHeaders.set("Number-Of-Elements", String.valueOf(listToResponse.size()));
            response = ((ResponseEntity.BodyBuilder) ResponseEntity.ok().headers(responseListHeaders)).body(listToResponse);
        } else {
            responseListHeaders.set("Total-Pages", "0");
            responseListHeaders.set("Total-Count", "0");
            responseListHeaders.set("Number-Of-Elements", "0");
            response = ((ResponseEntity.BodyBuilder) ResponseEntity.ok().headers(responseListHeaders)).body(Collections.emptyList());
        }

        return response;
    }

    private ResponseEntity<Page<D>> createCollectionPageMappingResponse(@Valid @NotNull Page<D> page) {
        HttpHeaders responsePageHeaders = this.addResponseHeaders(new HttpHeaders());
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            responsePageHeaders.set("Total-Count", String.valueOf(page.getTotalElements()));
            responsePageHeaders.set("Total-Pages", String.valueOf(page.getTotalPages()));
            responsePageHeaders.set("Number-Of-Elements", String.valueOf(page.getNumberOfElements()));
        } else {
            responsePageHeaders.set("Total-Count", "0");
            responsePageHeaders.set("Total-Pages", "0");
            responsePageHeaders.set("Number-Of-Elements", "0");
        }

        return ((ResponseEntity.BodyBuilder) ResponseEntity.ok().headers(responsePageHeaders)).body(page);
    }
}

package com.pachi.api.stock.exceptions.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Data
@ToString
@EqualsAndHashCode
public class RestErrorEntity implements Serializable {
    private static final long serialVersionUID = 3117534736382437023L;
    private final UUID id = UUID.randomUUID();
    private String errorCode;
    private Integer status;
    private String description;
    private String detail;
    private String apiVersion;
    private List<SubErrors> subErrors = new ArrayList();

    public RestErrorEntity() {
    }

    public List<SubErrors> getSubErrors() {
        return List.copyOf(this.subErrors);
    }

    public void addSubErrors(List<SubErrors> subErrors) {
        if (subErrors != null) {
            this.subErrors.addAll(subErrors);
        }

    }

    public Map<String, Object> toMap() {
        Map<String, Object> errorAttributes = new LinkedHashMap();
        errorAttributes.put("id", this.getId());
        errorAttributes.put("errorCode", this.getErrorCode());
        errorAttributes.put("status", this.getStatus());
        errorAttributes.put("description", this.getDescription());
        errorAttributes.put("detail", this.getDetail());
        errorAttributes.put("apiVersion", this.getApiVersion());
        errorAttributes.put("subErrors", this.getSubErrors());
        return errorAttributes;
    }
}

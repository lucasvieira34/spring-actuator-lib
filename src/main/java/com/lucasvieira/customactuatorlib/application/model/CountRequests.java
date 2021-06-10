package com.lucasvieira.customactuatorlib.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountRequests {

    @JsonProperty("totalRequests")
    private Integer totalRequests;
    @JsonProperty("200")
    private Long okStatus;
    @JsonProperty("202")
    private Long acceptedStatus;
    @JsonProperty("400")
    private Long badRequestStatus;
    @JsonProperty("404")
    private Long notFoundStatus;
    @JsonProperty("412")
    private Long preconditionalStatus;
    @JsonProperty("500")
    private Long internalStatus;

}

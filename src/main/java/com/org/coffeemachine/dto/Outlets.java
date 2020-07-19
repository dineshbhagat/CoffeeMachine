package com.org.coffeemachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "count_n"
})
public class Outlets {
    @JsonProperty("count_n")
    private Integer countN;

    public Integer getCountN() {
        return countN;
    }

    public void setCountN(Integer countN) {
        this.countN = countN;
    }

}
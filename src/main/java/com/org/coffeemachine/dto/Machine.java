package com.org.coffeemachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "outlets",
        "total_items_quantity",
        "beverages"
})
public class Machine {

    @JsonProperty("outlets")
    private Outlets outlets;
    @JsonProperty("total_items_quantity")
    private TotalItemsQuantity totalItemsQuantity;
    @JsonProperty("beverages")
    private Beverages beverages;

    public Outlets getOutlets() {
        return outlets;
    }

    public void setOutlets(Outlets outlets) {
        this.outlets = outlets;
    }

    public TotalItemsQuantity getTotalItemsQuantity() {
        return totalItemsQuantity;
    }

    public void setTotalItemsQuantity(TotalItemsQuantity totalItemsQuantity) {
        this.totalItemsQuantity = totalItemsQuantity;
    }

    public Beverages getBeverages() {
        return beverages;
    }

    public void setBeverages(Beverages beverages) {
        this.beverages = beverages;
    }

}
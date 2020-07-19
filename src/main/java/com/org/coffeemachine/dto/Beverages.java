package com.org.coffeemachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hot_tea",
        "hot_coffee",
        "black_tea",
        "green_tea"
})
public class Beverages {

    @JsonProperty("hot_tea")
    private HotTea hotTea;
    @JsonProperty("hot_coffee")
    private HotCoffee hotCoffee;
    @JsonProperty("black_tea")
    private BlackTea blackTea;
    @JsonProperty("green_tea")
    private GreenTea greenTea;

    public HotTea getHotTea() {
        return hotTea;
    }

    public void setHotTea(HotTea hotTea) {
        this.hotTea = hotTea;
    }

    public HotCoffee getHotCoffee() {
        return hotCoffee;
    }

    public void setHotCoffee(HotCoffee hotCoffee) {
        this.hotCoffee = hotCoffee;
    }

    public BlackTea getBlackTea() {
        return blackTea;
    }

    public void setBlackTea(BlackTea blackTea) {
        this.blackTea = blackTea;
    }

    public GreenTea getGreenTea() {
        return greenTea;
    }

    public void setGreenTea(GreenTea greenTea) {
        this.greenTea = greenTea;
    }

}
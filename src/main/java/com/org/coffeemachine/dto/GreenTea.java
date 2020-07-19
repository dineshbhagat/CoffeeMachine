package com.org.coffeemachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hot_water",
        "ginger_syrup",
        "sugar_syrup",
        "green_mixture"
})
public class GreenTea {

    @JsonProperty("hot_water")
    private Integer hotWater;
    @JsonProperty("ginger_syrup")
    private Integer gingerSyrup;
    @JsonProperty("sugar_syrup")
    private Integer sugarSyrup;
    @JsonProperty("green_mixture")
    private Integer greenMixture;

    public Integer getHotWater() {
        return hotWater;
    }

    public void setHotWater(Integer hotWater) {
        this.hotWater = hotWater;
    }

    public Integer getGingerSyrup() {
        return gingerSyrup;
    }

    public void setGingerSyrup(Integer gingerSyrup) {
        this.gingerSyrup = gingerSyrup;
    }

    public Integer getSugarSyrup() {
        return sugarSyrup;
    }

    public void setSugarSyrup(Integer sugarSyrup) {
        this.sugarSyrup = sugarSyrup;
    }

    public Integer getGreenMixture() {
        return greenMixture;
    }

    public void setGreenMixture(Integer greenMixture) {
        this.greenMixture = greenMixture;
    }

}
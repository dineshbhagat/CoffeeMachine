package com.org.coffeemachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hot_water",
        "hot_milk",
        "ginger_syrup",
        "sugar_syrup",
        "tea_leaves_syrup"
})
public class HotTea {

    @JsonProperty("hot_water")
    private Integer hotWater;
    @JsonProperty("hot_milk")
    private Integer hotMilk;
    @JsonProperty("ginger_syrup")
    private Integer gingerSyrup;
    @JsonProperty("sugar_syrup")
    private Integer sugarSyrup;
    @JsonProperty("tea_leaves_syrup")
    private Integer teaLeavesSyrup;

    public Integer getHotWater() {
        return hotWater;
    }

    public void setHotWater(Integer hotWater) {
        this.hotWater = hotWater;
    }

    public Integer getHotMilk() {
        return hotMilk;
    }

    public void setHotMilk(Integer hotMilk) {
        this.hotMilk = hotMilk;
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

    public Integer getTeaLeavesSyrup() {
        return teaLeavesSyrup;
    }

    public void setTeaLeavesSyrup(Integer teaLeavesSyrup) {
        this.teaLeavesSyrup = teaLeavesSyrup;
    }

}
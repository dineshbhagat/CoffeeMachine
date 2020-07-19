package com.org.coffeemachine.dao;

import com.org.coffeemachine.constant.Constants;
import com.org.coffeemachine.dto.*;
import com.org.coffeemachine.entity.Beverage;
import com.org.coffeemachine.entity.Ingredient;

import java.util.concurrent.Executors;

public class DaoHelper {
    private final CoffeeMachineDataStore coffeeMachineDataStore;

    public DaoHelper(CoffeeMachineDataStore coffeeMachineDataStore) {
        this.coffeeMachineDataStore = coffeeMachineDataStore;
    }

    void convertInputDtoToEntity(Machine machine) {
        // Outlets
        Integer outlets = machine.getOutlets().getCountN();
        int totalOutlets = outlets != null ? outlets : 0;
        coffeeMachineDataStore.setExecutorService(Executors.newFixedThreadPool(totalOutlets));

        // Store total Ingredients Quantities
        TotalItemsQuantity total = machine.getTotalItemsQuantity();
        Ingredient gingerSyrup = Ingredient.getInstance(Constants.GINGER_SYRUP);
        Ingredient hotMilk = Ingredient.getInstance(Constants.HOT_MILK);
        Ingredient hotWater = Ingredient.getInstance(Constants.HOT_WATER);
        Ingredient teaLeavesSyrup = Ingredient.getInstance(Constants.TEA_LEAVES_SYRUP);
        Ingredient sugarSyrup = Ingredient.getInstance(Constants.SUGAR_SYRUP);
        Ingredient greenMixture = Ingredient.getInstance(Constants.GREEN_MIXTURE);

        coffeeMachineDataStore.getTotalQuantity().put(gingerSyrup, total.getGingerSyrup());
        coffeeMachineDataStore.getTotalQuantity().put(hotMilk, total.getHotMilk());
        coffeeMachineDataStore.getTotalQuantity().put(hotWater, total.getHotWater());
        coffeeMachineDataStore.getTotalQuantity().put(teaLeavesSyrup, total.getTeaLeavesSyrup());
        coffeeMachineDataStore.getTotalQuantity().put(sugarSyrup, total.getSugarSyrup());


        // Store Beverages
        Beverages bvgs = machine.getBeverages();
        BlackTea bt = bvgs.getBlackTea();
        Beverage blackTea = Beverage.getInstance(Constants.BLACK_TEA);
        blackTea.getIngredientQuantity().put(hotWater, bt.getHotWater());
        blackTea.getIngredientQuantity().put(gingerSyrup, bt.getGingerSyrup());
        blackTea.getIngredientQuantity().put(sugarSyrup, bt.getSugarSyrup());
        blackTea.getIngredientQuantity().put(teaLeavesSyrup, bt.getTeaLeavesSyrup());

        coffeeMachineDataStore.getBeverages().put(Constants.BLACK_TEA, blackTea);

        GreenTea gt = bvgs.getGreenTea();
        Beverage greenTea = Beverage.getInstance(Constants.GREEN_TEA);
        greenTea.getIngredientQuantity().put(hotWater, gt.getHotWater());
        greenTea.getIngredientQuantity().put(gingerSyrup, gt.getGingerSyrup());
        greenTea.getIngredientQuantity().put(sugarSyrup, gt.getSugarSyrup());
        greenTea.getIngredientQuantity().put(greenMixture, gt.getGreenMixture());

        coffeeMachineDataStore.getBeverages().put(Constants.GREEN_TEA, greenTea);

        HotTea ht = bvgs.getHotTea();
        Beverage hotTea = Beverage.getInstance(Constants.HOT_TEA);
        hotTea.getIngredientQuantity().put(hotWater, ht.getHotWater());
        hotTea.getIngredientQuantity().put(gingerSyrup, ht.getGingerSyrup());
        hotTea.getIngredientQuantity().put(sugarSyrup, ht.getSugarSyrup());
        hotTea.getIngredientQuantity().put(teaLeavesSyrup, ht.getTeaLeavesSyrup());
        hotTea.getIngredientQuantity().put(hotMilk, ht.getHotMilk());

        coffeeMachineDataStore.getBeverages().put(Constants.HOT_TEA, hotTea);

        HotCoffee cf = bvgs.getHotCoffee();
        Beverage hotCoffee = Beverage.getInstance(Constants.HOT_COFFEE);
        hotCoffee.getIngredientQuantity().put(hotWater, cf.getHotWater());
        hotCoffee.getIngredientQuantity().put(gingerSyrup, cf.getGingerSyrup());
        hotCoffee.getIngredientQuantity().put(sugarSyrup, cf.getSugarSyrup());
        hotCoffee.getIngredientQuantity().put(teaLeavesSyrup, cf.getTeaLeavesSyrup());
        hotCoffee.getIngredientQuantity().put(hotMilk, cf.getHotMilk());

        coffeeMachineDataStore.getBeverages().put(Constants.HOT_COFFEE, hotCoffee);
    }
}
package com.ord.coffeemachine.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.coffeemachine.command.Command;
import com.org.coffeemachine.processor.CommandProcessor;
import com.org.coffeemachine.service.CoffeeMachineService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static com.org.coffeemachine.constant.Constants.*;

public class CoffeeMachineServiceTest {

    ObjectMapper objectMapper = new ObjectMapper();

    CoffeeMachineService coffeeMachineService;

    CommandProcessor commandProcessor;

    @Before
    public void init() {
        coffeeMachineService = new CoffeeMachineService(objectMapper);
        commandProcessor = new CommandProcessor(coffeeMachineService);
    }

    /**
     * Dummy test to check manual functional logic
     */
    @Test
    public void executeFlow() {
        commandProcessor.process("");
        commandProcessor.process("abcd");
        commandProcessor.process(Command.HELP.getValue());
        commandProcessor.process("order hot_coffee green_tea black_tea hot_tea");
        //blank order
        commandProcessor.process("order");
        commandProcessor.process("reset");
        commandProcessor.process(Command.QUIT.getValue());
    }

    @Test
    public void OnlyTwoOfTheBeveragesCanBeServed() {
        coffeeMachineService.resetMachine("TwoBeverages.json");
        Assert.assertNotNull(coffeeMachineService.getAvailableBeverages());
        Assert.assertTrue(!coffeeMachineService.getAvailableBeverages().isEmpty());
        Command order = Command.ORDER;
        order.getArguments().add(HOT_COFFEE);
        order.getArguments().add(GREEN_TEA);
        order.getArguments().add(BLACK_TEA);
        order.getArguments().add(HOT_TEA);
        Set<String> result = coffeeMachineService.order(order);
        int success = 0;
        for (String s : result) {
            if (!"".equals(s) && s.contains(PREPARED)) {
                success++;
            }
        }
        Assert.assertEquals(2, success);
    }

    @Test
    public void orderOneAndShouldBeServed() {
        coffeeMachineService.resetMachine("TwoBeverages.json");
        Assert.assertNotNull(coffeeMachineService.getAvailableBeverages());
        Assert.assertTrue(!coffeeMachineService.getAvailableBeverages().isEmpty());
        Command order = Command.ORDER;
        order.getArguments().add(HOT_COFFEE);
        Set<String> result = coffeeMachineService.order(order);
        int success = 0;
        for (String s : result) {
            if (!"".equals(s) && s.contains(PREPARED)) {
                success++;
            }
        }
        Assert.assertEquals(1, success);
    }

    @Test
    public void orderWithNoIngredientsShouldNotBeServed() {
        coffeeMachineService.resetMachine("TwoBeverages.json");
        Assert.assertNotNull(coffeeMachineService.getAvailableBeverages());
        Assert.assertTrue(!coffeeMachineService.getAvailableBeverages().isEmpty());
        Command order = Command.ORDER;
        order.getArguments().add(GREEN_TEA);
        Set<String> result = coffeeMachineService.order(order);
        int success = 0;
        int failed = 0;
        for (String s : result) {
            if (!"".equals(s) && s.contains(PREPARED)) {
                success++;
            }
            if (!"".equals(s) && s.contains(CAN_NOT_BE_PREPARED)) {
                failed++;
            }
        }
        Assert.assertEquals(0, success);
        Assert.assertEquals(1, failed);
    }

    /**
     * We will order 1st hot_coffee and black_tea to reduce sugar inventory and
     * then order the beverage which do not have sufficient ingredients
     */
    @Test
    public void orderWithInsufficientIngredientsShouldNotBeServed() {
        coffeeMachineService.resetMachine("TwoBeverages.json");
        Assert.assertNotNull(coffeeMachineService.getAvailableBeverages());
        Assert.assertTrue(!coffeeMachineService.getAvailableBeverages().isEmpty());
        Command order = Command.ORDER;
        order.getArguments().add(HOT_COFFEE);
        order.getArguments().add(BLACK_TEA);
        Set<String> result = coffeeMachineService.order(order);
        int success = 0;
        int failed = 0;
        for (String s : result) {
            if (!"".equals(s) && s.contains(PREPARED)) {
                success++;
            }
            if (!"".equals(s) && s.contains(CAN_NOT_BE_PREPARED)) {
                failed++;
            }
        }
        Assert.assertEquals(2, success);
        Assert.assertEquals(0, failed);

        // Next order
        Command order2 = Command.ORDER;
        order2.getArguments().add(HOT_TEA);
        Set<String> result2 = coffeeMachineService.order(order2);
        success = 0;
        failed = 0;
        for (String s : result2) {
            if (!"".equals(s) && s.contains(PREPARED)) {
                success++;
            }
            if (!"".equals(s) && s.contains(CAN_NOT_BE_PREPARED)) {
                failed++;
            }
        }
        Assert.assertEquals(0, success);
        Assert.assertEquals(1, failed);
    }

    @Test
    public void testInvalidInputFile() {
        coffeeMachineService.resetMachine("invalidJsonFile.json");
    }
}

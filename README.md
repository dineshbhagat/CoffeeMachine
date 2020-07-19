####
Assumptions:

- For now Loading total outlets in a machine, total types of beverages and it's ingredient's quantities, total available quantity for ingredients, from [input.json](src/main/resources/input.json) file
- Project can be exteded to add/refill ingredients individually, for now resetting with config values specified in above file.
- To refill ingredients execute 'reset' command.
- Main logic is in [CoffeeMachineService](src/main/java/com/org/coffeemachine/service/CoffeeMachineService.java)
- Tests are in [CoffeeMachineServiceTest](src/test/java/com/ord/coffeemachine/service/CoffeeMachineServiceTest.java)
#### To Run an application

 - build
 ```bash
 ./gradlew clean build
 ```
 
 - run
 ```bash
 java -jar build/libs/CoffeeMachine-1.0-SNAPSHOT.jar
 ```
    
```text
Type 'help' once you run above command, you will get an command list
e.g.
order hot_coffee green_tea black_tea hot_tea
```
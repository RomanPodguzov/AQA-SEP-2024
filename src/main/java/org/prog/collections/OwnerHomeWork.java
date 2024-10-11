package org.prog.collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerHomeWork {


    public class Owner {

        public static void main(String[] args) {
            // TODO: create Map which will allow you to track car owners and cars they own
            // Создаем карту для отслеживания владельцев и их автомобилей
            Map<String, List<String>> carOwners = new HashMap<>();

            // Добавляем владельцев и их машины
            carOwners.put("Alice", Arrays.asList("Toyota", "BMW"));
            carOwners.put("Bob", Arrays.asList("Honda", "BMW"));  // Bob и Alice оба владеют BMW
            carOwners.put("Charlie", Arrays.asList("Ford"));
            carOwners.put("Dave", Arrays.asList("Tesla"));

            // TODO: At least one car must be owned by two owners
            // BMW принадлежит и Alice, и Bob

            // Создаем карту для отслеживания автомобилей, которые делят владельцы
            Map<String, List<String>> sharedCars = new HashMap<>();

            // Проходим по каждому владельцу и его автомобилям
            for (String owner : carOwners.keySet()) {
                List<String> cars = carOwners.get(owner);
                for (String car : cars) {
                    // Если машина уже есть в карте, добавляем владельца
                    if (!sharedCars.containsKey(car)) {
                        extracted(car, sharedCars);
                    }
                    sharedCars.get(car).add(owner);
                }
            }

            // TODO: print owners that share cars
            // Печатаем владельцев, которые делят машины
            System.out.println("Owners that share cars:");
            for (String car : sharedCars.keySet()) {
                List<String> owners = sharedCars.get(car);
                if (owners.size() > 1) {
                    System.out.println("Car: " + car + " is shared by: " + owners);
                }
            }
        }

        private static void extracted(String car, Map<String, List<String>> sharedCars) {
            sharedCars.put(car, new ArrayList<>());
        }
    }

}

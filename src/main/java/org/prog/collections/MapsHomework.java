package org.prog.collections;

import org.prog.inheritance.Car;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//TODO: create Map which will allow you to track car owners and cars they own
//TODO: At least one car must be owned by two owners
//TODO: print owners that share cars
public class MapsHomework {

    public static void main(String[] args) {
        Car coOwnedCar = new Car("red");

        CarOwner john = createOwner("John", "Doe", "+1234567890");
        CarOwner jane = createOwner("Jane", "Doe", "+3216549870");
        CarOwner bob = createOwner("Bob", "Marley", "+65447891230");

        Map<CarOwner, Set<Car>> ownersAndCars = new HashMap<>();
        ownersAndCars.put(john, new HashSet<>());
        ownersAndCars.put(jane, new HashSet<>());
        ownersAndCars.put(bob, new HashSet<>());

        ownersAndCars.get(john).add(coOwnedCar);
        ownersAndCars.get(jane).add(coOwnedCar);

        ownersAndCars.get(john).add(new Car("blue"));
        ownersAndCars.get(jane).add(new Car("yellow"));
        ownersAndCars.get(bob).add(new Car("green"));

        Set<CarOwner> ownersWithSameCars = new HashSet<>();
        Set<Car> temporaryCars = new HashSet<>();
        for (Map.Entry<CarOwner, Set<Car>> entry : ownersAndCars.entrySet()) {
            temporaryCars = entry.getValue();
            for (Map.Entry<CarOwner, Set<Car>> entrySecondary : ownersAndCars.entrySet()) {
                if (entrySecondary.getKey().equals(entry.getKey())) {
                    System.out.println("skip it");
                } else {
                    for (Car car : entrySecondary.getValue()) {
                        if (temporaryCars.contains(car)) {
                            ownersWithSameCars.add(entry.getKey());
                            ownersWithSameCars.add(entrySecondary.getKey());
                        }
                    }
                }
            }
        }

        for (CarOwner owner : ownersWithSameCars){
            System.out.println(owner.firstName + " " + owner.lastName);
        }
    }

    public static CarOwner createOwner(String name, String lastName, String phoneNumber) {
        CarOwner user = new CarOwner();
        user.lastName = lastName;
        user.firstName = name;
        user.phoneNumber = phoneNumber;
        return user;
    }
}

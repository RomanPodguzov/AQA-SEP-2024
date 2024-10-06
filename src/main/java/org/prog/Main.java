package org.prog;

import org.prog.inheritance.*;
import org.prog.poly.BMW;
import org.prog.poly.Ford;
import org.prog.poly.ICar;
import org.prog.poly.Toyota;

import java.io.File;

public class Main {

    public static void main(String[] args) {
//        Ford ford = new Ford();
//        Toyota toyota = new Toyota();
//        BMW bmw = new BMW();
//
//        ford.honk();
//
//        driveCar(ford);
//        driveCar(toyota);
//        driveCar(bmw);

        System.out.println("=======================================");

        Car car = new Car("a");
//        Truck truck = new Truck();
//        BigTruck bigTruck = new BigTruck();
//        RoadTrain roadTrain = new RoadTrain();
//        BigRoadTrain bigRoadTrain = new BigRoadTrain();
        car.turnLeft();
//        truck.turnLeft();
//        bigTruck.turnLeft();
//        roadTrain.turnLeft();
//        bigRoadTrain.turnLeft();



        car.goTo("Kyiv");
        car.goTo("Kyiv", "Odessa");
        car.goTo("Kyiv", "Odessa", "Dnipro");
        car.goTo("Kyiv", "Odessa", "Dnipro", "My cats");
    }

    public static void driveCar(ICar iCar) {
        iCar.driveForward();
        iCar.turn("Left");
        iCar.driveForward();
        iCar.turn("Right");
        iCar.driveForward();
    }
}

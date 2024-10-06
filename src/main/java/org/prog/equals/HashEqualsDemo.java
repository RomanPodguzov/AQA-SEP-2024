package org.prog.equals;

import org.prog.inheritance.Car;

public class HashEqualsDemo {

    /*
    ####################################
    ####################################
    #########car1#######################
    ####################################
    ####################################
    ################car2################
    ##########1#########################
     */

    public static void main(String[] args) {
        Car car1 = new Car("a");
        Car joshCar = car1;
        Car car2 = new Car("a");

        car1.color = "red";
        car2.color = "red";


        System.out.println(car1.equals(car2));
        System.out.println(car1 == car2);
        System.out.println(car1 == joshCar);
    }
}

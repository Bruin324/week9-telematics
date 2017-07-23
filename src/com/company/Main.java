package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);

        VehicleInfo car1 = new VehicleInfo();
        System.out.println("Enter a new car and its info:");
        System.out.println("What is the VIN?");
        car1.setVIN(input.nextInt());
        System.out.println("How many gallons of gas has it consumed?");
        car1.setConsumption(input.nextDouble());
        System.out.println("What is the engine size? (2.0 or 4.5)");
        car1.setEngineSize(input.nextDouble());
        System.out.println("What is the current odometer reading?");
        car1.setOdometer(input.nextDouble());
        System.out.println("What was the odometer reading at your last oil change?");
        car1.setOdometerReadingForLastOilChange(input.nextDouble());

        TelematicsService.report(car1);
    }

}

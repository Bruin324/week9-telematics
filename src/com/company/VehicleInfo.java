package com.company;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by macuser on 7/21/17.
 */
public class VehicleInfo {

    int VIN;
    double odometer;
    double consumption;
    double odometerReadingForLastOilChange;
    double engineSize;

    public VehicleInfo() {

    }

    public int getVIN() {
        return VIN;
    }

    public void setVIN(int VIN) {
        this.VIN = VIN;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getOdometerReadingForLastOilChange() {
        return odometerReadingForLastOilChange;
    }

    public void setOdometerReadingForLastOilChange(double odometerReadingForLastOilChange) {
        this.odometerReadingForLastOilChange = odometerReadingForLastOilChange;
    }

    @JsonIgnore
    public double getMilesPerGallon() {
        return (odometer / consumption);
    }


//    public void setMilesPerGallon(double odometer, double consumption) {
//    }
}

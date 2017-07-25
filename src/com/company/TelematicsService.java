package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/**
 * Created by macuser on 7/21/17.
 */
public class TelematicsService {

    public static void report(VehicleInfo vehicleInfo){
        System.out.println("Here is your vehicle info:");
        System.out.println("VIN: " + vehicleInfo.getVIN());
        System.out.println("Gallons of gas consumed: " + vehicleInfo.getConsumption());
        System.out.println("Engine Size: " + vehicleInfo.getEngineSize());
        System.out.println("Current Odometer: " + vehicleInfo.getOdometer());
        System.out.println("Odometer at last oil Change: " + vehicleInfo.getOdometerReadingForLastOilChange());
        System.out.println("Gas Mileage: " + vehicleInfo.getMilesPerGallon() + "mpg");


        //Creates file for new vehicle and writes to JSON */

        String json = null;
        File newVehicle = new File(vehicleInfo.getVIN() + ".json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.writeValueAsString(vehicleInfo);
            System.out.println("vehicleInfo: " + vehicleInfo);
            System.out.println("json: " + json);
            FileWriter createFile = new FileWriter(newVehicle);
            createFile.write(json);
            createFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reads all JSON files and calculates totals for all values

        double consumptionTotal = 0;
        double engineSizeTotal = 0;
        double odometerTotal = 0;
        double oilChangeTotal = 0;
        int totalVehicles = 0;

        File file = new File(".");

        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                FileReader jsonFiles;
                try {
                    jsonFiles = new FileReader(f);
                    VehicleInfo vi = mapper.readValue(jsonFiles, VehicleInfo.class);
                    consumptionTotal += vi.getConsumption();
                    engineSizeTotal += vi.getEngineSize();
                    odometerTotal += vi.getOdometer();
                    oilChangeTotal += vi.getOdometerReadingForLastOilChange();
                    totalVehicles++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //

        double consumptionAvg =  Math.round((consumptionTotal/totalVehicles) * 10.0) / 10.0;
        double engineAvg =  Math.round((engineSizeTotal/totalVehicles) * 10.0) / 10.0;
        double odometerAvg =  Math.round((odometerTotal/totalVehicles) * 10.0) / 10.0;
        double oilChangeAvg =  Math.round((oilChangeTotal/totalVehicles) * 10.0) / 10.0;

        String html =
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Vehicle Telematics Dashboard</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1 align=\"center\">Averages for " + totalVehicles + " vehicles</h1>\n" +
                "<table align=\"center\">\n" +
                "    <tr>\n" +
                "        <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td align=\"center\">" + odometerAvg + "</td><td align=\"center\">" + consumptionAvg + "</td><td align=\"center\">" + oilChangeAvg + "</td align=\"center\"><td align=\"center\">" + engineAvg + "</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<h1 align=\"center\">History</h1>\n" +
                "<table align=\"center\" border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Gas Mileage (mpg)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
                "    </tr>\n";

        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                FileReader jsonFiles;
                try {
                    jsonFiles = new FileReader(f);
                    VehicleInfo vi = mapper.readValue(jsonFiles, VehicleInfo.class);
                    html += "<tr>\n" +
                            "        <td align=\"center\">" + vi.getVIN() + "</td><td align=\"center\">" + vi.getOdometer() + "</td><td align=\"center\">" + vi.getConsumption() + "</td><td align=\"center\">" + (Math.round((vi.getMilesPerGallon()) * 10.0) / 10.0) + "</td><td align=\"center\">" + vi.getOdometerReadingForLastOilChange() + "</td align=\"center\"><td align=\"center\">" + vi.getEngineSize() + "</td>\n" +
                            "    </tr>";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        html += "</table>\n" +
                "</body>\n" +
                "</html>";

        File dashboard = new File("TelematicsDashboard.html");
        try {
            FileWriter createFile = new FileWriter(dashboard);
            createFile.write(html);
            createFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

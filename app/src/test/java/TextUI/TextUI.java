/**
 * store Inspections into inspectionManager.
 * store Restaurants into RestaurantsManager.
 */

package TextUI;

import Model.Inspection;
import Model.InspectionsManager;
import Model.Restaurant;
import Model.RestaurantsManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextUI {
    public TextUI() throws FileNotFoundException {
        storeRestaurants();
        storeInspections();
    }

    private void storeRestaurants() throws FileNotFoundException {
        File file = new File("/Users/rattlecruiser/Desktop/restaurants_itr1.csv"); //this is my source root.
        Scanner scan = null;
        scan = new Scanner(file);
        Restaurant restaurant;
        RestaurantsManager manager;
        manager = RestaurantsManager.getInstance();
        String line;
        line = scan.nextLine();
        double latitude;
        double longitude;
        while (scan.hasNextLine()){
            line = scan.nextLine();
            String[] lineArray = line.split(",");
            latitude = Double.parseDouble(lineArray[5]);
            longitude = Double.parseDouble(lineArray[6]);
            restaurant = new Restaurant(lineArray[0], lineArray[1], lineArray[2], lineArray[3], lineArray[4], latitude, longitude);
            manager.addRestaurant(restaurant);
        }
        scan.close();
    }

    private void storeInspections() throws FileNotFoundException {
        File file = new File("/Users/rattlecruiser/Desktop/inspectionreports_itr1.csv"); //this is my source root.
        Scanner scan = null;
        scan = new Scanner(file);
        Inspection inspection;
        InspectionsManager manager;
        manager = InspectionsManager.getInstance();
        String line;
        line = scan.nextLine();
        int inspectionDate;
        int numCritical;
        int numNonCritical;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            String[] lineArray = line.split(",");
            inspectionDate = Integer.parseInt(lineArray[1]);
            numCritical = Integer.parseInt(lineArray[3]);
            numNonCritical = Integer.parseInt(lineArray[4]);

            if (lineArray.length == 6){
                inspection = new Inspection(lineArray[0], inspectionDate, lineArray[2], numCritical, numNonCritical, lineArray[5], "");
            }
            else{
                inspection = new Inspection(lineArray[0], inspectionDate, lineArray[2], numCritical, numNonCritical, lineArray[5], lineArray[6]);
            }
            manager.addInspection(inspection);
        }
        scan.close();
    }

}

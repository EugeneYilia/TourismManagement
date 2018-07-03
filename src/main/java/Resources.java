import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Resources {
    private static ArrayList<String> places = new ArrayList<String>();
    private static ArrayList<Spot> spots = new ArrayList<Spot>();
    private static String fileName = "document/spots.txt";

    static {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, "——");
                String firstPlace = stringTokenizer.nextToken();
                String secondPlace = stringTokenizer.nextToken();
                int distance = Integer.parseInt(stringTokenizer.nextToken());
                spots.add(new Spot(firstPlace, secondPlace, distance));
                if (!isExist(firstPlace)) {
                    places.add(firstPlace);
                }
                if (!isExist(secondPlace)) {
                    places.add(secondPlace);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isExist(String name) {
        for (String spot: places) {
            if (name.equals(spot)) {
                return true;
            }
        }
        return false;
    }

    private static void showSpots() {
        for (Spot spot: spots) {
            System.out.println(spot.getSourcePlace() + "->" + spot.getDestinationPlace() + "   distance:" + spot.getDistance());
        }
    }

    private static void showPlaces() {
        for (String place: places) {
            System.out.println(place);
        }
    }

    @Test
    public void testResult() {
        System.out.println("#############show spots#############");
        showSpots();
        System.out.println("$$$$$$$$$$$$$show spots$$$$$$$$$$$$$");

        System.out.println("#############show places#############");
        showPlaces();
        System.out.println("$$$$$$$$$$$$$show places$$$$$$$$$$$$$");
    }
}

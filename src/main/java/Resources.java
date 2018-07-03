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
    private static int[][] distances;

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
            distances = new int[places.size()][places.size()];
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

    public static void printResult() {
        System.out.print("          ");
        for (String place: places) {
            System.out.print(place + "        ");
        }
        System.out.println();
        for (int i = 0; i < places.size(); i++) {
            for (int j = 0; j < places.size(); j++) {
                distances[i][j] = getTwoPlaceDistanc(places.get(i), places.get(j));
            }
        }
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).length() == 2) {
                System.out.print(places.get(i) + "      ");
            } else {
                System.out.print(places.get(i) + "    ");
            }
            for (int j = 0; j < places.size(); j++) {
                if ((distances[i][j] / 10) == 0) {
                    System.out.print(distances[i][j] + "    " + "        ");
                } else if ((distances[i][j] / 100) == 0) {
                    System.out.print(distances[i][j] + "   " + "        ");
                } else if ((distances[i][j] / 1000) == 0) {
                    System.out.print(distances[i][j] + "  " + "        ");
                } else if ((distances[i][j] / 10000) == 0) {
                    System.out.print(distances[i][j] + " " + "        ");
                } else {
                    System.out.print(distances[i][j] + "        ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printResult();
    }

    private static int getTwoPlaceDistanc(String sourcePlace, String destinationPlace) {
        if (sourcePlace.equals(destinationPlace)) {
            return 0;
        }
        for (Spot spot: spots) {
            if (spot.getSourcePlace().equals(sourcePlace) && spot.getDestinationPlace().equals(destinationPlace)) {
                return spot.getDistance();
            } else if (spot.getDestinationPlace().equals(sourcePlace) && spot.getSourcePlace().equals(destinationPlace)) {
                return spot.getDistance();
            }
        }
        return 32767;
    }

    @Test
    public void testSpots() {
        System.out.println("#############show spots#############");
        showSpots();
        System.out.println("$$$$$$$$$$$$$show spots$$$$$$$$$$$$$");
    }

    @Test
    public void testPlaces() {
        System.out.println("#############show places#############");
        showPlaces();
        System.out.println("$$$$$$$$$$$$$show places$$$$$$$$$$$$$");
    }
}

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
    private static String distanceFileName = "document/spots.txt";
    private static String descriptionFileName = "document/spotsDescription.txt";
    private static int[][] distances;
    private static ArrayList<Description> descriptions = new ArrayList<Description>();

    static {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(distanceFileName)));
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

    static {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(descriptionFileName)));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line," ");
                String spotName = stringTokenizer.nextToken();
                String spotDescription = stringTokenizer.nextToken();
                int loveDegree = Integer.parseInt(stringTokenizer.nextToken());
                boolean hasRestPlace = Boolean.parseBoolean(stringTokenizer.nextToken());
                boolean hasToilet = Boolean.parseBoolean(stringTokenizer.nextToken());
                descriptions.add(new Description(spotName,spotDescription,loveDegree,hasRestPlace,hasToilet));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isExist(String name) {
        for (String place: places) {
            if (name.equals(place)) {
                return true;
            }
        }
        return false;
    }

    public static void showSpots() {
        for (Spot spot: spots) {
            System.out.println(spot.getSourcePlace() + "->" + spot.getDestinationPlace() + "   distance:" + spot.getDistance());
        }
    }

    public static boolean deleteRoad(String firstPlace, String secondPlace) {
        for (Spot spot: spots) {
            if (spot.getSourcePlace().equals(firstPlace) && spot.getDestinationPlace().equals(secondPlace)) {
                spots.remove(spot);
                return true;
            } else if (spot.getSourcePlace().equals(secondPlace) && spot.getDestinationPlace().equals(firstPlace)) {
                spots.remove(spot);
                return true;
            }
        }
        return false;
    }

    public static boolean addRoad(String firstPlace, String secondPlace, int distance) {
        for (Spot spot: spots) {
            if (spot.getSourcePlace().equals(firstPlace) && spot.getDestinationPlace().equals(secondPlace)) {
                spot.setDistance(distance);
                return true;
            } else if (spot.getSourcePlace().equals(secondPlace) && spot.getDestinationPlace().equals(firstPlace)) {
                spot.setDistance(distance);
                return true;
            }
        }
        if (isExist(firstPlace) && isExist(secondPlace)) {
            spots.add(new Spot(firstPlace, secondPlace, distance));
            return true;
        }
        return false;
    }


    public static boolean removePlace(String place) {
        for (String p: places) {
            if (p.equals(place)) {
                places.remove(p);
                return true;
            }
        }
        return false;
    }

    public static void showPlaces() {
        for (String place: places) {
            System.out.print(place + "  ");
        }
        System.out.println();
    }

    public static void printResult() {
        distances = new int[places.size()][places.size()];
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

    public static ArrayList<Description> getDescriptions() {
        return descriptions;
    }

    public static void setDescriptionFileName(String descriptionFileName) {
        Resources.descriptionFileName = descriptionFileName;
    }

    public static ArrayList<String> getPlaces() {
        return places;
    }

    public static ArrayList<Spot> getSpots() {
        return spots;
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

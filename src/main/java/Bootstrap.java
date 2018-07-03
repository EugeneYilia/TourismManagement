import sun.security.krb5.internal.crypto.Des;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Bootstrap {
    public static Scanner scanner = new Scanner(System.in);
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        while (true) {
            System.out.println("1.管理员登陆");
            System.out.println("2.景点查找");
            System.out.println("3.景点排序");
            System.out.println("4.两景点之间的最短距离和路径");//路径就是途中会经过哪些景点
            System.out.println("5.输出导游路线图");
            System.out.println("6.输出车辆的进出信息");
            System.out.println("0.退出");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                managerLogin();
            } else if (input.equals("2")) {
                searchSpot();
            } else if (input.equals("3")) {
                sortSpot();
            } else if (input.equals("4")) {

            } else if (input.equals("0")) {
                return;
            }
        }
    }

    public static void sortSpot() {
        ArrayList<Description> originalDescriptions = Resources.getDescriptions();
        ArrayList<Description> tempDescriptions = new ArrayList<Description>();
        int count = 0;
        System.out.println("推荐等级        景点名称                景点简介                       游客喜欢程度              是否有休息场所               是否有厕所");
        for (int i = 10; i >= 0; i--) {
            int j = 1;
            int m = count;
            for (Description description: originalDescriptions) {
                if (description.getLoveDegree() == i) {
                    count++;
                    if ((j + m) < 10) {
                        if (description.getSpotName().length() == 2) {
                            System.out.println(j + m + ".  " + "            " + description.getSpotName() + "   " + "           "+description.getSpotDescription() + "         " + description.getLoveDegree() + "                   " + description.hasRestPlace() + "                          " + description.hasToilet());
                        } else if (description.getSpotName().length() == 3) {
                            System.out.println(j + m + ".  " + "            " + description.getSpotName() + " " + "           "+description.getSpotDescription() + "         " + description.getLoveDegree() + "                    " + description.hasRestPlace() + "                          " + description.hasToilet());
                        }
                    } else {
                        if (description.getSpotName().length() == 2) {
                            System.out.println(j + m + ". " + "            " + description.getSpotName() + "   " +"           "+ description.getSpotDescription() + "              " + description.getLoveDegree() + "                     " + description.hasRestPlace() + "                     " + description.hasToilet());
                        } else if (description.getSpotName().length() == 3) {
                            System.out.println(j + m + ". " + "            " + description.getSpotName() + " " +"           "+ description.getSpotDescription() + "         " + description.getLoveDegree() + "                    " + description.hasRestPlace() + "                          " + description.hasToilet());
                        }
                    }
                }
            }
            j++;
        }
    }

    public static void searchSpot() {
        System.out.println("请输入你想要查找的景区特征");
        String feature = scanner.nextLine();
        ArrayList<Description> returnSpots = new ArrayList<Description>();
        for (Description description: Resources.getDescriptions()) {
            if (description.getSpotName().contains(feature) || description.getSpotDescription().contains(feature)) {
                returnSpots.add(description);
            }
        }

        if (returnSpots.size() != 0) {
            System.out.println("符合要求的景点包括");
            System.out.println("景点名称        景点描述                       景点喜爱程度      是否有休息的场所        否有厕所");
        } else {
            System.out.println("没有你想要查找的景点");
        }
        for (Description description: returnSpots) {
            System.out.println(description.getSpotName() + "        " + description.getSpotDescription() + "        " + description.getLoveDegree() + "          " + description.hasRestPlace() + "                    " + description.hasToilet());
        }
    }

    private static void managerLogin() {
        String account = "";
        String password = "";
        System.out.println("请输入账号");
        account = scanner.nextLine();
        System.out.println("请输入密码");
        password = scanner.nextLine();
        if (account.equals("root") && password.equals("root")) {
            showManagerInterface();
        }
    }

    private static void showManagerInterface() {
        while (true) {
            System.out.println("1.插入一个景点");
            System.out.println("2.删除一个景点");
            System.out.println("3.插入一条路");
            System.out.println("4.删除一条路");
            System.out.println("5.发布通知");
            System.out.println("6.查看现在所有的景点(名称)");
            System.out.println("7.查看现在所有的景点(简略)");
            System.out.println("8.查看现在所有的景点(详细)");
            System.out.println("0.退出");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                System.out.println("输入想要插入景点的名称");
                input = scanner.nextLine();
                Resources.getPlaces().add(input);
            } else if (input.equals("2")) {
                System.out.println("输入想要删除的景点的名称");
                input = scanner.nextLine();
                boolean isDeleteSuccessfully = Resources.removePlace(input);
                if (isDeleteSuccessfully) {
                    System.out.println("删除成功");
                } else {
                    System.out.println("不存在要删除的景点");
                }
            } else if (input.equals("3")) {
                System.out.println("输入要插入的路的第一个端点");
                String firstPlace = scanner.nextLine();
                System.out.println("输入要插入的路的第二个端点");
                String secondPlace = scanner.nextLine();
                System.out.println("输入要插入的路的长度");
                int distance = Integer.parseInt(scanner.nextLine());
                boolean isAddSuccessfully = Resources.addRoad(firstPlace, secondPlace, distance);
                if (isAddSuccessfully) {
                    System.out.println("添加成功");
                } else {
                    System.out.println("至少有一个端点不存在，添加失败");
                }
            } else if (input.equals("4")) {
                System.out.println("输入要删除的路的第一个端点");
                String firstPlace = scanner.nextLine();
                System.out.println("输入要删除的路的第二个端点");
                String secondPlace = scanner.nextLine();
                boolean isDeleteSuccessfully = Resources.deleteRoad(firstPlace, secondPlace);
                if (isDeleteSuccessfully) {
                    System.out.println("删除成功");
                } else {
                    System.out.println("至少有一个端点不存在，删除失败");
                }
            } else if (input.equals("5")) {
                System.out.println("输入你想要发布的公告的内容");
                String content = scanner.nextLine();
                try {
                    PrintWriter printWriter = new PrintWriter(new FileWriter(new File("document/bulletin.txt"), true));
                    printWriter.println(simpleDateFormat.format(new Date()) + ":" + content);
                    printWriter.flush();
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (input.equals("6")) {
                Resources.showPlaces();
            } else if (input.equals("7")) {
                Resources.showSpots();
            } else if (input.equals("8")) {
                Resources.printResult();
            } else if (input.equals("0")) {
                return;
            }
        }
    }
}

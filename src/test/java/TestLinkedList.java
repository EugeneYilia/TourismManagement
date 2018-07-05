import java.util.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
//        showElements(linkedList);
        showFirstElement(linkedList);

        linkedList.remove(0);
//        showElements(linkedList);
        showFirstElement(linkedList);
    }

    public static void showElements(LinkedList linkedList) {
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
    }

    public static void showFirstElement(LinkedList linkedList) {
        System.out.println(linkedList.get(0));
    }
}

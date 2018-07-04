import java.util.ArrayList;

public class TestArrayList2 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println(arrayList.get(0));
        arrayList.remove(0);
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
    }
}

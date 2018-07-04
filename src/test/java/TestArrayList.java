import java.util.ArrayList;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(2);
        System.out.println(arrayList.size());
        for(Object object:arrayList){
            System.out.println(object);
        }
        arrayList = add(arrayList);
        System.out.println(arrayList.size());
        for(Object object:arrayList){
            System.out.println(object);
        }
    }

    public static ArrayList add(ArrayList arrayList) {
        ArrayList arrayList1 = new ArrayList();
        arrayList1.add(1);
        arrayList1.add(1);
        arrayList1.add(1);
        arrayList1.add(1);
        arrayList1.add(1);
        arrayList1.add(1);
        arrayList = arrayList1;
        return arrayList;
//        System.out.println(arrayList.size());
//        arrayList.add(1);
//        arrayList.add(1);
    }
}

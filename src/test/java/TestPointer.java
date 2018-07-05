public class TestPointer {

    public static void main(String[] args) {
        Object object = new Object();//object可以理解为Object对象的地址相当于C语言中指针的值一样，虽然形参会对其进行复制，也就是形参指针的地址和当前这个指针的地址不同，但是他们的值都为object对象的地址，或者在java中就可以说指的就是object这个对象
        System.out.println("1." + object);//指向初始对象的句柄
        printAddress(object);
        System.out.println("4." + object);//由于函数中是在实参的复制上进行的操作，因此之前指向初始对象的句柄仍然指向初始对象，故此值不变
    }

    public static void printAddress(Object object) {//形参对传进来的实参(Object对象的句柄)进行复制，然后在其复制品上进行操作，该复制品句柄也指向内存中的Object对象，虽然两个句柄的地址不同，但是两个句柄的值是相同，也就是说两个句柄指向的都是object对象的地址或者说是object对象
        System.out.println("2." + object);//此时形参指向的object与初始对象相同
        object = new Object();
        System.out.println("3." + object);//此时形参指向新的object
    }
}

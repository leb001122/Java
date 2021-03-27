package practice.list;

public class ArrListMain {
    public static void main(String[] args) {
        ArrList<String> s = new ArrList<String>();
        s.insert("apple");
        s.print();
        s.insert("cherry");
        s.print();
        s.insert("grape", 1);
        s.print();
        s.insert("kiwi");
        s.print();
        s.insert("mango");
        s.print();
        s.delete(2);
        s.delete(3);
        s.delete(4);
        s.print();
        System.out.println("1번째 항목은"+s.peek(1)+"이다.");
    }
}

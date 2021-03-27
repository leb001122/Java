package dataStructure.first;

public class HanoiTowerAlgorithm {

    static String msgFormat = "%d번 원반을 %c에서 %c로 이동\n";

    public static void main(String [] args) {
        hanoi(20, 'A', 'C', 'B');
    }

    public static void move(int N, char start, char to) {
        System.out.printf(msgFormat, N, start, to);
    }

    public static void hanoi(int N, char start, char to, char via) {
        if(N==1)
            move(1, start, to);
        else
        {
            hanoi(N-1, start, via, to);
            move(N, start, to);
            hanoi(N-1, via, to, start);
        }
    }
}

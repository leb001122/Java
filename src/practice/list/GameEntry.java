package practice.list;

public class GameEntry {
    private String name;
    private int score;

    public GameEntry(String n, int s) {
        name = n;
        score = s;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }

    //오버라이딩
    public String toString() {
        return "(" + name + ", " + score +")";
    }

}

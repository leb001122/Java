package practice.list;

public class GameScoreMain {
    public static void main(String [] args) {
        Scoreboard scoreboard = new Scoreboard(10);
        GameEntry entry = new GameEntry("이은빈", 6);
        scoreboard.add(entry);
        scoreboard.add(new GameEntry("서민정", 9));
        scoreboard.add(new GameEntry("이준재", 1));
        System.out.print(scoreboard);
    }
}

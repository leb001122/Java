package practice.list;

public class Scoreboard {
    private int numEntries = 0;
    private GameEntry[] board;

    public Scoreboard(int capacity) {
        board = new GameEntry[capacity];
    }

    public void add(GameEntry e) {
        int newScore = e.getScore();

        // 상위 점수
        if(numEntries<board.length || newScore>board[numEntries-1].getScore()) {
            if(numEntries<board.length)
                numEntries++;

            int j = numEntries - 1;

            while(j>0 && board[j-1].getScore()<newScore) {
                board[j] = board[j-1];
                j--;
            }
            board[j] = e;
        }
    }

    public String toString() {
        String res = "";
        res += "Num of Entry : " + numEntries +"\n";
        for(int i=0; i<numEntries; i++) {
            res += board[i] + "\n";
        }
        return res;
    }
}
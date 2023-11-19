public class test {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    //public void run() {
        // 玩游戏

        if (win) {
            checkWin(name);
        }
    }
    public static boolean isGameOver = false;
    private void checkWin(String name) {
        // 和之前一样的检查胜利和通知代码
        if (!isGameOver) {
            System.out.println(name + " wins!");
            isGameOver = true;
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
    }
}

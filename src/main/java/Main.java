/**
 */
public class Main {

    public static void main(String[] args) {
        MainRunner mainRunner = new MainRunner();
        mainRunner.init();

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

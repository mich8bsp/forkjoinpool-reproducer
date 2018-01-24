import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 */
public class MainRunner extends TimerTask {

    private ForkJoinPool pool;
    private Random random = new Random();
    private int counter = 1;

    public void init() {
        pool = new ForkJoinPool();
        Timer timer = new Timer("MyTimer", false);
        timer.schedule(this,0, 10);
    }

    public void run() {
        List<Runnable> tasks = IntStream.range(0, random.nextInt(500))
                .boxed()
                .map(i-> buildTask(i))
                .collect(Collectors.toList());

        System.out.println("Scheduled run number " + counter + ", start running all jobs");
        pool.invoke(new ParallelMainTask(tasks));
        System.out.println("Finished run number " + counter);
        counter++;
    }

    private Runnable buildTask(int i) {
       return () -> {
           System.out.println("Started job " + i + " in thread " + Thread.currentThread().getName());
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("Finished job " + i + " in thread " + Thread.currentThread().getName());
       };
    }
}

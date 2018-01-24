import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 */
public class ParallelMainTask extends ForkJoinTask<Object>{

    private List<ForkJoinTask<?>> tasks;

    public ParallelMainTask(List<Runnable> jobs) {
        this.tasks = new ArrayList<>();
        for(Runnable runnable : jobs){
            ForkJoinTask<?> task = ForkJoinTask.adapt(runnable);
            tasks.add(task);
        }
    }

    @Override
    public Object getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Object value) {

    }

    @Override
    protected boolean exec() {
        ForkJoinTask.invokeAll(tasks);
        return true;
    }
}

package Foundation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DispatchQueue {

    public static DispatchQueue main = new DispatchQueue();
    public static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

    private DispatchQueue() {
        run();
    }

    public void run() {
        // Dispatch queue
        // while (true) {
        //     try {
        //         queue.take().run();
        //     } catch (InterruptedException e) {
        //     }
        // }
    }

    // public void asyncAfter(long seconds, Runnable execution) {
    //     try {
    //         Thread async = new Thread(execution, "DispatchQueue.asyncAfter");
    //         async.start();
    //         async.wait(seconds);
    //     } catch (InterruptedException e) {
    //     }
    // }

    // public void async(long seconds, Runnable execution) {
    //     queue.add(execution);
    // }

}
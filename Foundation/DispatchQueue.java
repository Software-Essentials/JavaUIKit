package Foundation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DispatchQueue {

  private static final Map<String, Thread> threads = new HashMap<String, Thread>();
  public static final DispatchQueue main = new DispatchQueue("main"); // Main thead;
  public static final DispatchQueue ui = new DispatchQueue("ui"); // UI thead

  public static DispatchQueue global() { // Global thead
    return new DispatchQueue("global");
  }

  private String name;

  DispatchQueue(String name) {
    this.name = name;
    Thread thread;
    if (name.equals("main")) {
      thread = Thread.currentThread();
    } else {
      thread = new Thread();
    }
    String threadName = "DispatchQueue." + name;
    thread.setName(threadName);
    manage(() -> {
      threads.put(name, thread);
    });
  }

  /**
   * Perform operation in DispatchQueue without interupting the DispatchQueue's
   * thead.
   */
  private void asyncAfter(String name, long seconds, PassingCallback<Thread> execution) {
    Thread thread = new Thread() {
      @Override
      public void run() {
        super.run();
        try {
          if (seconds > 0) {
            Thread.currentThread().wait(seconds * 1000);
          }
        } catch (InterruptedException exception) {
          // DispatchQueue interupted.
        } catch (IllegalMonitorStateException exception) {
          // DispatchQueue interupted.
        }
        manage(() -> {
          execution.call(this);
        });
      }
    };
    String threadName = name;
    if (threadName == null) {
      threadName = "DispatchQueue." + name + ".async" + thread.getId();
    }
    thread.setName(threadName);
    thread.start();
  }

  /**
   * Perform operation in DispatchQueue without interupting the DispatchQueue's
   * thead.
   */
  public void asyncAfter(long seconds, Callback execution) {
    asyncAfter(null, 0, (thread) -> {
      execution.call();
    });
  }

  /**
   * Perform operation in DispatchQueue without interupting the DispatchQueue's
   * thead.
   */
  public void asyncAfter(long seconds, PassingCallback<Thread> execution) {
    asyncAfter(null, 0, execution);
  }

  /**
   * Perform operation in DispatchQueue without interupting the DispatchQueue's
   * thead.
   */
  public void async(Callback execution) {
    asyncAfter(0, execution);
  }

  /**
   * Perform operation in DispatchQueue without interupting the DispatchQueue's
   * thead.
   */
  public void async(PassingCallback<Thread> execution) {
    asyncAfter(0, execution);
  }

  public void start(Callback execution) {
    asyncAfter(name, 0, (thread) -> {
      execution.call();
    });
  }

  public static void manage(Callback execution) {
    manage(DispatchQueue.main, execution);
  }

  public static void manage(DispatchQueue dispatchQueue, Callback execution) {
    try {
      execution.call();
    } catch (Exception exception) {
      String failurePoint = null;
      String stackTrace = "DispatchQueue." + dispatchQueue.name + " interupted by '"
          + exception.getClass().getSimpleName() + "'\n\n";
      for (StackTraceElement element : exception.getStackTrace()) {
        if (element.toString().startsWith("Foundation.")) {
          continue;
        }
        if (element.toString().startsWith("UIKit.")) {
          continue;
        }
        stackTrace += "\t" + element + "\n";
        if (failurePoint == null) {
          String[] parts = element.getClassName().split(Pattern.quote("."));
          String path = "";
          for (String part : parts) {
            path += "/" + part;
          }
          path = (path + ".java").substring(1);
          if (FileSystem.fileExists("src/" + path)) {
            failurePoint = "src/" + path + ":" + element.getLineNumber();
          }
        }
      }
      if (failurePoint != null) {
        Device.main.executeSync("code -g " + failurePoint);
      }
      FD.print("\n" + FD.TERMCOL_CYAN + "[", "Foundation", "]", FD.TERMCOL_RED + stackTrace + FD.TERMCOL_RESET);
    }
  }

}
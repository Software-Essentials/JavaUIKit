package Foundation;

import java.lang.reflect.Method;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Application implements ApplicationProtocol {

  public static final Application instance = new Application();

  public static void start(String[] arguments, LaunchMode mode) {
    DispatchQueue.main.start(() -> {
      instance.didLaunch(arguments, mode);
    });
  }

  public final Version version;
  public LaunchMode mode;
  public String[] arguments;

  protected Application() {
    version = new Version(1, 0, 0);
    mode = LaunchMode.normal;
  }

  public void didLaunch(String[] arguments, LaunchMode mode) {
    if (FileSystem.fileExists("src/AppDelegate.java")) {
      try {
        Class clas = Class.forName("AppDelegate");
        Method method = clas.getMethod("didLaunch", arguments.getClass(), mode.getClass());
        method.invoke(clas.getDeclaredConstructor().newInstance(), arguments, mode);
        this.mode = mode;
        this.arguments = arguments;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
package UIKit;

import Foundation.*;

import java.awt.Taskbar;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UIApplication extends Application {

  public static final UIApplication instance = new UIApplication();

  public static void start(String[] arguments, LaunchMode mode) {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    try {
      instance.icon = new UIImage("assets/AppIcon.png");
      Taskbar.getTaskbar().setIconImage(instance.icon.toAWT());
    } catch (UnsupportedOperationException e) {
      UI.print("The os does not support: 'taskbar.setIconImage'");
    } catch (SecurityException e) {
      UI.print("There was a security exception for: 'taskbar.setIconImage'");
    } catch (Exception e) {
      if (e.getCause().getClass().getSimpleName().equals("NullPointerException")) {
        UI.print("Missing 'assets/AppIcon.png'!");
      }
    }
    DispatchQueue.main.start(() -> {
      instance.didLaunch(arguments, mode);
    });
  }

  List<UIWindow> windows = new ArrayList<UIWindow>();
  public boolean debuggingViews = false;
  public UIImage icon;

  private UIApplication() {
    super();
    UIColor.updateConstantConstraints();
  }

  public void didLaunch(String[] arguments, LaunchMode mode) {
    if (FileSystem.fileExists("src/AppDelegate.java")) {
      try {
        Class clas = Class.forName("AppDelegate");
        Method method = clas.getMethod("didLaunch", arguments.getClass(), mode.getClass());
        method.invoke(clas.getDeclaredConstructor().newInstance(), arguments, mode);
        this.mode = mode;
        this.arguments = arguments;
        instance.update();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void update() {
    Application.instance.mode = instance.mode;
    Application.instance.arguments = instance.arguments;
    for (UIWindow window : windows) {
      window.update();
    }
  }

  public void register(UIWindow window) {
    windows.add(window);
    update();
  }

}
package UIKit;

import static javax.swing.JFrame.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import Foundation.*;

/**
 * Window
 * 
 * @see UIScene
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
public class UIWindow {

  private List<Runnable> openEvents = new ArrayList<Runnable>();
  private List<Runnable> exitEvents = new ArrayList<Runnable>();
  private LinkedList<UINavigationView> scenes = new LinkedList<UINavigationView>();
  private JFrame window = new JFrame();
  private UINavigationView rootScene = null;
  private UIView rootView = null;
  private Rectangle bounds;

  public UIWindow() {
    this("");
  }

  public UIWindow(UINavigationView rootScene) {
    this("", rootScene);
  }

  public UIWindow(String title) {
    this(title, null);
  }

  public UIWindow(String title, UINavigationView rootScene) {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    int x = (int) ((UIScreen.main.width - window.getWidth()) / 2);
    int y = (int) ((UIScreen.main.width - window.getHeight()) / 2);
    bounds = new Rectangle(x, y, 800, 700);
    window.setMinimumSize(new Dimension(750, 500));
    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(HIDE_ON_CLOSE);
    window.setTitle(title);
    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent windowEvent) {
        didOpen();
        for (Runnable event : openEvents) {
          event.run();
        }
        super.windowOpened(windowEvent);
      }

      @Override
      public void windowClosing(WindowEvent windowEvent) {
        didClose();
        for (Runnable event : exitEvents) {
          event.run();
        }
        super.windowClosing(windowEvent);
      }
    });
    setNavigationView(rootScene);
  }

  public UIWindow(String title, Component rootView) {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    int x = (int) ((UIScreen.main.width - window.getWidth()) / 2);
    int y = (int) ((UIScreen.main.width - window.getHeight()) / 2);
    bounds = new Rectangle(x, y, 800, 700);
    window.setMinimumSize(new Dimension(750, 500));
    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(HIDE_ON_CLOSE);
    window.setTitle(title);
    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent windowEvent) {
        didOpen();
        for (Runnable event : openEvents) {
          event.run();
        }
        super.windowOpened(windowEvent);
      }

      @Override
      public void windowClosing(WindowEvent windowEvent) {
        didClose();
        for (Runnable event : exitEvents) {
          event.run();
        }
        super.windowClosing(windowEvent);
      }
    });
    setRootView(rootView);
  }

  public void setRootView(Component rootView) {
    if (rootView != null && this.rootView == null) {
      if (rootView instanceof UIView) {
        this.rootView = ((UIView) rootView);
        ((UIView) rootView).setWindow(this);
        window.setContentPane((UIView) rootView);
      } else {
        UIView view = new UIView();
        view.add(rootView);
        this.rootView = view;
        view.setWindow(this);
        window.setContentPane(view);
      }
      window.validate();
    }
  }

  public void setNavigationView(UINavigationView rootScene) {
    if (rootScene != null && this.rootScene == null) {
      scenes.add(rootScene);
      this.rootScene = rootScene;
      rootScene.setWindow(this);
      window.setContentPane(rootScene);
      window.validate();
    }
  }

  public void show() {
    window.pack();
    window.setSize(bounds.width, bounds.height);
    window.setVisible(true);
  }

  public void close() {
    window.setVisible(false);
  }

  public void setTitle(String title) {
    window.setTitle(title);
  }

  public UIWindow resize(int width, int height) {
    this.bounds.width = width;
    this.bounds.height = height;
    window.setSize(width, height);
    window.validate();
    return this;
  }

  public void setMinimumSize(int width, int height) {
    window.setMinimumSize(new Dimension(width, height));
  }

  public void setMaximumSize(int width, int height) {
    window.setMaximumSize(new Dimension(width, height));
  }

  public void setAppIcon(Image appIcon) {
    window.setIconImage(appIcon);
  }

  public void setMenuBar(UIMenuBar menuBar) {
    window.setJMenuBar(menuBar.getJMenuBar());
  }

  public JFrame getJFrame() {
    return window;
  }

  /**
   * Pops current scene from the SceneRouter, and displays underlaying Scene. If
   * there is only 1 scene in the SceneRouter nothing will happen.
   */
  public void popScene() {
    if (scenes.size() > 1) {
      scenes.pollLast();
      window.setContentPane(scenes.getLast());
      window.validate();
    }
  }

  /**
   * Pushes a scene onto the SceneRouter
   * 
   * @param scene Scene to display
   */
  public void pushScene(UINavigationView scene) {
    scenes.push(scene);
    window.setContentPane(scene);
    window.validate();
  }

  /**
   * Cleans up anything left of the closed window.
   */
  public void dispose() {
    window.dispose();
  }

  /**
   * Sets the application to be exited when a window is closed.
   */
  public void exitOnClose() {
    exitOnClose(true);
  }

  /**
   * Sets wheater ornot the application should exit once the window closes.
   * 
   * @param shouldExitOnClose
   */
  public void exitOnClose(boolean shouldExitOnClose) {
    if (shouldExitOnClose) {
      window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    } else {
      window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
  }

  /**
   * Sets the window to be hidden when a window is closed.
   */
  public void hideOnClose() {
    hideOnClose(true);
  }

  /**
   * Sets wheater ornot the window should hide itself once the window closes.
   * 
   * @param shouldExitOnClose
   */
  public void hideOnClose(boolean shouldExitOnClose) {
    if (shouldExitOnClose) {
      window.setDefaultCloseOperation(HIDE_ON_CLOSE);
    } else {
      window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
  }

  public void onOpen(Runnable callback) {
    openEvents.add(callback);
  }

  public void onClose(Runnable callback) {
    exitEvents.add(callback);
  }

  protected void didOpen() {
  }

  protected void didClose() {
  }

}
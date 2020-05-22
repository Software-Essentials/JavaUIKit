package UIKit;

import static javax.swing.JFrame.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Foundation.*;
import Foundation.ApplicationProtocol.LaunchMode;

/**
 * Window
 * 
 * @see UIScene
 * @since 0.1.0
 */
public class UIWindow {

  private List<PassingCallback<UIWindow>> openCallbacks = new ArrayList<PassingCallback<UIWindow>>();
  private List<PassingCallback<UIWindow>> exitCallbacks = new ArrayList<PassingCallback<UIWindow>>();
  public JFrame window = new JFrame();
  private UIView rootView = null;
  private UIMenuBar menuBar = new UIMenuBar();
  private Rectangle bounds;
  private boolean allowReset = false;
  private boolean center = false;
  boolean debuggingViews = true;

  public UIWindow() {
    this("");
  }

  public UIWindow(String title) {
    this(title, null);
  }

  public UIWindow(String title, Component rootView) {
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
        super.windowOpened(windowEvent);
      }

      @Override
      public void windowClosing(WindowEvent windowEvent) {
        didClose();
        super.windowClosing(windowEvent);
      }
    });
    try {
      Image image = UIApplication.instance.icon.toAWT();
      window.setIconImage(image);
    } catch (Exception e) {
    }
    setRootView(rootView);
    UIApplication.instance.register(this);
  }

  public void update() {
    debuggingViews = UIApplication.instance.debuggingViews;
    setupDeveloperTools();
    window.validate();
  }

  public void allowReset() {
    allowReset(true);
  }

  public void allowReset(boolean state) {
    allowReset = state;
  }

  public void setRootView(UIView rootView) {
    if (allowReset || (rootView != null && this.rootView == null)) {
      // rootView.accessibilityWindow(this);
      rootView.window = this;
      // rootView.background(UIColor.systemBackground);
      this.rootView = rootView;
      window.setContentPane(rootView);
      window.validate();
    }
  }

  public void setRootView(Component rootView) {
    if (allowReset || (rootView != null && this.rootView == null)) {
      if (rootView instanceof UIView) {
        // rootView.accessibilityWindow(this);
        ((UIView) rootView).window = this;
        // ((UIView) rootView).background(UIColor.systemBackground);
        this.rootView = ((UIView) rootView);
        window.setContentPane((UIView) rootView);
      } else {
        UIView view = new UIView();
        view.add(rootView);
        // view.accessibilityWindow(this);
        view.window = this;
        this.rootView = view;
        // view.background(UIColor.systemBackground);
        window.setContentPane(view);
      }
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

  public void setSize(int width, int height) {
    this.bounds.width = width;
    this.bounds.height = height;
    window.setSize(width, height);
    if (center) {
      center();
    }
  }

  public void setMinimumSize(int width, int height) {
    bounds.width = bounds.width < width ? width : bounds.width;
    bounds.height = bounds.height < height ? height : bounds.height;
    window.setMinimumSize(new Dimension(width, height));
    if (center) {
      center();
    }
  }

  public void setMaximumSize(int width, int height) {
    Dimension minSize = window.getMinimumSize();
    int minWidth = (int) minSize.getWidth();
    int minHeight = (int) minSize.getHeight();
    if (minWidth > width || minHeight > height) {
      window.setMinimumSize(new Dimension(minWidth - (minWidth - width), minHeight - (minHeight - height)));
    }
    window.setMaximumSize(new Dimension(width, height));
    if (center) {
      center();
    }
  }

  public void setAppIcon(Image appIcon) {
    window.setIconImage(appIcon);
  }

  public void setMenuBar(UIMenuBar menuBar) {
    window.setJMenuBar(menuBar.accessibilityComponent());
  }

  public void menu(String text, UIMenuBarItem... items) {
    menuBar.add(new UIMenuBarList(text, items));
    window.setJMenuBar(menuBar.accessibilityComponent());
    setupDeveloperTools();
  }

  private void setupDeveloperTools() {
    if (Application.instance.mode == LaunchMode.development || Application.instance.mode == LaunchMode.debug) {
      menuBar.add(new UIMenuBarList("Developer", new UIMenuBarItem("Inspect subviews").onClick(() -> {
        UIApplication.instance.debuggingViews = !UIApplication.instance.debuggingViews;
        UIApplication.instance.update();
      })));
      window.setJMenuBar(menuBar.accessibilityComponent());
    }
  }

  public UIView getSubview() {
    return rootView;
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

  public void onOpen(PassingCallback<UIWindow> callback) {
    openCallbacks.add(callback);
  }

  public void onClose(PassingCallback<UIWindow> callback) {
    exitCallbacks.add(callback);
  }

  protected void didOpen() {
    for (PassingCallback<UIWindow> callback : openCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
  }

  protected void didClose() {
    for (PassingCallback<UIWindow> callback : exitCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
  }

  // MARK: - Accessibility methods

  public JFrame accessibilityFrame() {
    return window;
  }

  // MARK: - Chaining methods

  public UIWindow move(int x, int y) {
    this.bounds.x = x;
    this.bounds.y = y;
    window.setLocation(x, y);
    center = false;
    return this;
  }

  public UIWindow resize(int width, int height) {
    this.bounds.width = width;
    this.bounds.height = height;
    window.setSize(width, height);
    if (center) {
      center();
    }
    window.validate();
    return this;
  }

  public UIWindow center() {
    center = true;
    int x = (int) ((UIScreen.main.width / 2) - (bounds.width / 2));
    int y = (int) ((UIScreen.main.height / 2) - (bounds.height / 2));
    window.setLocation(x, y);
    return this;
  }

}
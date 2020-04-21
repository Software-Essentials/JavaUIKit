package UIKit;

import java.awt.GridLayout;

/**
 * UIScene
 * 
 * @see UIWindow
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
@SuppressWarnings("serial")
public class UINavigationView extends UIView {

  public UINavigationView() {
    this(null);
  }

  public UINavigationView(UIView rootView) {
    if (rootView != null) {
      this.add(rootView);
    }
    this.setLayout(new GridLayout(1, 1));
  }

  public UIWindow getWindow() {
    return window;
  }

  public void navigateTo(UINavigationView scene) {
    window.pushScene(scene);
  }

  public void navigate(String location) {
  }

}
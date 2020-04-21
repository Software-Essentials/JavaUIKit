package UIKit;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * UIMenuBar
 * 
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
public class UIMenuBar {

  private JMenuBar menuBar;

  public UIMenuBar() {
    this(new JMenuBar());
  }

  public UIMenuBar(JMenuBar menuBar) {
    this.menuBar = menuBar;
  }

  public void add(JMenu menu) {
    menuBar.add(menu);
  }

  public JMenuBar getJMenuBar() {
    return menuBar;
  }

}
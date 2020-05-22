package UIKit;

import java.awt.Component;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Foundation.FD;

/**
 * UIMenuBar
 * 
 * @since 0.1.0
 */
public class UIMenuBar {

  private JMenuBar component;

  public UIMenuBar() {
    this(new JMenuBar());
  }

  public UIMenuBar(JMenuBar menuBar) {
    this.component = menuBar;
  }

  public UIMenuBar(UIMenuBarList... lists) {
    this.component = new JMenuBar();
    for (UIMenuBarList list : lists) {
      component.add(list.accessibilityComponent());
    }
  }

  public void add(UIMenuBarList menu) {
    for (Component comp : component.getComponents()) {
      if (comp instanceof JMenu && ((JMenu) comp).getText().equals(menu.getText())) {
        System.out.println(FD.TERMCOL_BLUE + FD.TERMCOL_BOLD + "[UIKit]" + FD.TERMCOL_RESET + FD.TERMCOL_YELLOW + " Detected and removed a duplicate UIMenuBarList" + FD.TERMCOL_RESET);
        component.remove(comp);
      }
    }
    component.add(menu.accessibilityComponent());
  }

  public JMenuBar accessibilityComponent() {
    return component;
  }

}
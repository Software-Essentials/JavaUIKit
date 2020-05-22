package UIKit;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Foundation.FD;

import java.awt.Component;

/**
 * UIMenuBar
 * 
 * @since 0.1.0
 */
public class UIMenuBarList extends UIMenuBarItem {

  private JMenu component;

  public UIMenuBarList(String text) {
    this(new JMenu(text));
  }

  public UIMenuBarList(JMenu list) {
    component = list;
  }

  public UIMenuBarList(String text, UIMenuBarItem... items) {
    this.component = new JMenu(text);
    for (UIMenuBarItem item : items) {
      add(item);
    }
  }

  public void add(UIMenuBarItem menuBarItem) {
    for (Component comp : component.getMenuComponents()) {
      if (comp instanceof JMenuItem && ((JMenuItem) comp).getText().equals(menuBarItem.getText())) {
        System.out.println(FD.TERMCOL_BLUE + FD.TERMCOL_BOLD + "[UIKit]" + FD.TERMCOL_RESET + FD.TERMCOL_YELLOW + " Detected and removed a duplicate UIMenuBarListItem" + FD.TERMCOL_RESET);
        component.remove(comp);
      }
    }
    component.add(menuBarItem.accessibilityComponent());
  }

  public String getText() {
    return component.getText();
  }

  public JMenu accessibilityComponent() {
    return component;
  }

}
package UIKit;

import Foundation.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class UIMenuBarItem implements ActionListener {

  private List<Callback> clickCallbacks = new ArrayList<Callback>();

  private JMenuItem component;

  public UIMenuBarItem() {
    this(new JMenuItem());
  }

  public UIMenuBarItem(String text) {
    this(new JMenuItem(text));
  }

  public UIMenuBarItem(JMenuItem item) {
    component = item;
    component.addActionListener(this);
  }

  public String getText() {
    return component.getText();
  }

  public void setAccelerator(FD.Key key, FD.Key.Mask mask) {
    component.setAccelerator(KeyStroke.getKeyStroke(key.code, mask.code));
  }

  // MARK: - Accessibility methods

  public JMenuItem accessibilityComponent() {
    return component;
  }

  // MARK: - Chaining methods

  public UIMenuBarItem bind(FD.Key key) {
    component.setAccelerator(KeyStroke.getKeyStroke(key.code, 0));
    return this;
  }

  public UIMenuBarItem bind(FD.Key.Mask mask, FD.Key key) {
    component.setAccelerator(KeyStroke.getKeyStroke(key.code, mask.code));
    return this;
  }

  public UIMenuBarItem disabled() {
    disabled(true);
    return this;
  }

  public UIMenuBarItem disabled(boolean state) {
    component.setEnabled(!state);
    return this;
  }

  public UIMenuBarItem onClick(Callback callback) {
    clickCallbacks.add(callback);
    return this;
  }

  // MARK: - Integrations

  @Override
  public void actionPerformed(ActionEvent e) {
    for (Callback callback : clickCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call();
      });
    }
  }

}
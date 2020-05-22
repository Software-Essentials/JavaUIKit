package UIKit;

import Foundation.*;

import static java.awt.RenderingHints.*;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * A view is a component of a scene.
 * 
 * @since 0.1.0
 */
@SuppressWarnings("serial")
public class UIView extends JPanel implements MouseListener, FocusListener {

  public enum State {
    normal, hover, active, disabled
  }

  UIWindow window = null;

  private List<PassingCallback<UIView>> clickCallbacks = new ArrayList<PassingCallback<UIView>>();
  private List<PassingCallback<UIView>> pressedCallbacks = new ArrayList<PassingCallback<UIView>>();
  private List<PassingCallback<UIView>> releasedCallbacks = new ArrayList<PassingCallback<UIView>>();
  private List<PassingCallback<UIView>> enterCallbacks = new ArrayList<PassingCallback<UIView>>();
  private List<PassingCallback<UIView>> exitCallbacks = new ArrayList<PassingCallback<UIView>>();
  private List<PassingCallback<UIView>> gainedCallbacks = new ArrayList<PassingCallback<UIView>>();
  private List<PassingCallback<UIView>> lostCallbacks = new ArrayList<PassingCallback<UIView>>();

  protected UIColor hoverBackgroundColor = UIColor.clear;
  protected UIColor hoverForegroundColor = UIColor.systemBlue.transpire(2);
  protected UIColor pressedBackgroundColor = UIColor.clear;
  protected UIColor pressedForegroundColor = UIColor.systemBlue.transpire(4);
  protected UIColor disabledBackgroundColor = UIColor.clear;
  protected UIColor disabledForegroundColor = UIColor.systemGray;
  protected UIColor normalBackgroundColor = UIColor.clear;
  protected UIColor normalForegroundColor = UIColor.black.transpire();
  protected UIColor backgroundColor = UIColor.clear;
  protected UIColor foregroundColor = UIColor.black.transpire();
  protected UIColor underlapColor = UIColor.systemBackground;

  protected Cursor hoverCursor = new Cursor(Cursor.HAND_CURSOR);
  protected Cursor pressedCursor = new Cursor(Cursor.HAND_CURSOR);
  protected Cursor cursor;
  protected UIView parent = null;

  protected int cornerRadius = 0;

  protected int paddingTop = 0;
  protected int paddingBottom = 0;
  protected int paddingLeft = 0;
  protected int paddingRight = 0;

  protected MouseEvent clickedEvent = null;
  protected MouseEvent pressedEvent = null;
  protected MouseEvent releasedEvent = null;
  protected MouseEvent enteredEvent = null;
  protected MouseEvent exitedEvent = null;

  public State state = State.normal;

  public UIView() {
    this(null);
  }

  public UIView(UIView parent) {
    super(true);
    if (parent != null) {
      this.parent = parent;
      try {
        this.window = parent.window;
      } catch (NullPointerException e) {
        String parentName = parent.getClass().getSimpleName();
        String name = getClass().getSimpleName();
        UI.print("Parent " + parentName + " of UIView " + name + " does not have a window. That's illegal!");
      }
    }
    cursor = this.getCursor();
    // setPreferredSize(new Dimension(0, 0));
    setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    setLayout(new GridLayout(1, 1));
    addMouseListener(this);
    addFocusListener(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    super.paintComponent(g);
    switch (state) {
      case normal:
        foregroundColor = normalForegroundColor;
        backgroundColor = normalBackgroundColor;
        break;
      case hover:
        foregroundColor = hoverForegroundColor;
        backgroundColor = hoverBackgroundColor;
        break;
      case active:
        foregroundColor = pressedForegroundColor;
        backgroundColor = pressedBackgroundColor;
        break;
      case disabled:
        foregroundColor = disabledForegroundColor;
        backgroundColor = disabledBackgroundColor;
        break;
    }
    setBackground(underlapColor.toAWT());
    setForeground(foregroundColor.toAWT());
    g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
    g.setColor(backgroundColor.toAWT());
    int px = paddingLeft;
    int py = paddingTop;
    int pw = getWidth() - paddingLeft - paddingRight;
    int ph = getHeight() - paddingTop - paddingBottom;
    g.fillRoundRect(px, py, pw, ph, cornerRadius, cornerRadius);
    // if (window != null) {
    // System.out.println("[DEBUGGING?] " + window.debuggingViews);
    // }
    if (window != null && window.debuggingViews) {
    // if (true) {
      // System.out.println("[RRRRRR!]");
      g.setColor(UIColor.red.toAWT());
      g.drawString(getClass().getName(), paddingLeft + 6, paddingTop + 16);
      g.drawString("frame(w: " + getWidth() + ", h: " + getHeight() + ")", paddingLeft + 6, paddingTop + 28);
      g.drawString("CBG: " + backgroundColor, paddingLeft + 6, paddingTop + 40);
      g.drawRect(0, 0, getWidth(), getHeight());
      g.setColor(UIColor.blue.toAWT());
      g.drawRoundRect(px, py, pw, ph, cornerRadius, cornerRadius);
    }
    render(g2);
  }

  public void reload() {
    this.viewWillUnload();
    this.viewWillLoad();
  }

  public void unload() {
    this.viewWillUnload();
  }

  public void setWindow(UIWindow window) {
    if (this.window == null) {
      this.window = window;
    }
    this.viewWillLoad();
  }

  // public void add(UIView view) {
  // // view.accessibilityWindow(window);
  // // System.out.println("window on " + getClass().getSimpleName() + " is " +
  // // window);
  // view.accessibilityParent(this);
  // super.add(view);
  // }

  @Override
  public Component add(Component component) {
    if (component instanceof UIView) {
      // ((UIView) component).accessibilityWindow(window);
      ((UIView) component).accessibilityParent(this);
    }
    return super.add(component);
  }

  public void update() {
    repaint();
  }

  protected void render(Graphics2D g) {
  }

  protected void viewWillLoad() {
  }

  protected void viewWillUnload() {
  }

  public void setCornerRadius(int radius) {
    cornerRadius = radius;
  }

  public int getCornerRadius() {
    return cornerRadius;
  }

  public void setForeground(UIColor color) {
    foregroundColor = color;
    super.setForeground(color.toAWT());
  }

  public void setBackground(UIColor color) {
    backgroundColor = color;
    update();
  }

  public String printHierarchy() {
    return printHierarchy("?");
  }

  public String printHierarchy(String string) {
    String newString = string + " -> ";
    if (this.parent() == null) {
      return newString + getClass().getSimpleName();
    }
    newString += this.parent().getClass().getSimpleName();
    return this.parent().printHierarchy(newString);
  }

  public UIWindow window() {
    return window;
  }

  public UIView parent() {
    return parent;
  }

  // MARK: - Chaining methods

  public UIView padding(int value) {
    return padding(value, value, value, value);
  }

  public UIView padding(UI side, int value) {
    if (side == UI.left || side == UI.leading) {
      paddingLeft += value;
    }
    if (side == UI.right || side == UI.trailing) {
      paddingRight += value;
    }
    if (side == UI.top) {
      paddingTop += value;
    }
    if (side == UI.bottom) {
      paddingBottom += value;
    }
    setBorder(BorderFactory.createEmptyBorder(paddingTop, paddingLeft, paddingBottom, paddingRight));
    return this;
  }

  public UIView padding(int top, int left, int bottom, int right) {
    paddingLeft = left;
    paddingRight = right;
    paddingTop = top;
    paddingBottom = bottom;
    setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    return this;
  }

  public UIView foreground(UIColor color) {
    try {
      normalForegroundColor = color;
      hoverForegroundColor = color;
      pressedForegroundColor = color;
      setForeground(color.toAWT());
    } catch (NullPointerException e) {
      String simpleName = this.getClass().getSimpleName();
      UI.print("NilGuard: " + simpleName + ".foreground(UIColor) received a nil parameter. That's illegal!");
    }
    return this;
  }

  public UIView background(UIColor color) {
    try {
      normalBackgroundColor = color;
      hoverBackgroundColor = color;
      pressedBackgroundColor = color;
      update();
    } catch (NullPointerException e) {
      String simpleName = this.getClass().getSimpleName();
      UI.print("NilGuard: " + simpleName + ".background(UIColor) received a nil parameter. That's illegal!");
    }
    return this;
  }

  public UIView cornerRadius(int radius) {
    cornerRadius = radius;
    return this;
  }

  /**
   * Triggers when the UIView gains focus.
   * 
   * @param callback Lambda expression as a callback function.
   * @return
   */
  public UIView onFocus(PassingCallback<UIView> callback) {
    gainedCallbacks.add(callback);
    return this;
  }

  /**
   * Triggers when the UIView gains focus.
   * 
   * @param callback Lambda expression as a callback function.
   * @return
   */
  public UIView onFocus(Callback callback) {
    gainedCallbacks.add((view) -> {
      callback.call();
    });
    return this;
  }

  /**
   * Triggers when the UIView loses focus.
   * 
   * @param callback Lambda expression as a callback function.
   * @return
   */
  public UIView onFocusLost(PassingCallback<UIView> callback) {
    lostCallbacks.add(callback);
    return this;
  }

  /**
   * Triggers when the UIView loses focus.
   * 
   * @param callback Lambda expression as a callback function.
   * @return
   */
  public UIView onFocusLost(Callback callback) {
    lostCallbacks.add((view) -> {
      callback.call();
    });
    return this;
  }

  /**
   * Triggers when the UIView is clicked or tapped.
   * 
   * @param callback Lambda expression as a callback function.
   * @return
   */
  public UIView onClick(PassingCallback<UIView> callback) {
    clickCallbacks.add(callback);
    return this;
  }

  /**
   * Triggers when the UIView is clicked or tapped.
   * 
   * @param callback Lambda expression as a callback function.
   * @return
   */
  public UIView onClick(Callback callback) {
    clickCallbacks.add((view) -> {
      callback.call();
    });
    return this;
  }

  public UIView onClickStart(PassingCallback<UIView> callback) {
    pressedCallbacks.add(callback);
    return this;
  }

  public UIView onClickEnd(PassingCallback<UIView> callback) {
    releasedCallbacks.add(callback);
    return this;
  }

  public UIView onHoverStart(PassingCallback<UIView> callback) {
    enterCallbacks.add(callback);
    return this;
  }

  public UIView onHoverEnd(PassingCallback<UIView> callback) {
    exitCallbacks.add(callback);
    return this;
  }

  public UIView disabled() {
    disabled(true);
    return this;
  }

  public UIView disabled(boolean state) {
    this.state = state ? State.disabled : State.normal;
    if (state) {
      foregroundColor = disabledForegroundColor;
      backgroundColor = disabledBackgroundColor;
    }
    update();
    return this;
  }

  // MARK: - Accessibility methods

  public void accessibilityParent(UIView parent) {
    if (parent != null) {
      this.parent = parent;
      try {
        this.window = parent.window;
      } catch (NullPointerException e) {
        String parentName = parent.getClass().getSimpleName();
        String name = getClass().getSimpleName();
        UI.print("Parent " + parentName + " of UIView " + name + " does not have a window. That's illegal!");
      }
    }
    this.viewWillLoad();
  }

  // public void accessibilityWindow(UIWindow window) {
  // if (window == null) {
  // String simpleName = this.getClass().getSimpleName();
  // UI.print("NilGuard: " + simpleName + ".accessibilityWindow(UIWindow) received
  // a nil parameter. That's illegal!");
  // return;
  // }
  // if (this.window == null) {
  // this.window = window;
  // }
  // update();
  // }

  public void accessibilityForeground(UIColor foreground) {
    if (this.normalForegroundColor == null) {
      foreground(foreground);
    }
    this.viewWillLoad();
  }

  // MARK: - Intrgration

  @Override
  public void mouseClicked(MouseEvent e) {
    for (PassingCallback<UIView> callback : clickCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
    clickedEvent = e;
    update();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (state == State.disabled) {
      return;
    }
    state = State.active;
    if (e.isPopupTrigger())
      doPop(e);
    for (PassingCallback<UIView> callback : pressedCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
    pressedEvent = e;
    update();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.isPopupTrigger())
      doPop(e);
    for (PassingCallback<UIView> callback : releasedCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
    releasedEvent = e;
    update();
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (state == State.disabled) {
      return;
    }
    state = State.hover;
    for (PassingCallback<UIView> callback : enterCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
    enteredEvent = e;
    update();
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (state == State.disabled) {
      return;
    }
    state = State.normal;
    for (PassingCallback<UIView> callback : exitCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
    exitedEvent = e;
    update();
  }

  private void doPop(MouseEvent e) {
    PopUpDemo menu = new PopUpDemo();
    menu.show(e.getComponent(), e.getX(), e.getY());
  }

  class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;

    public PopUpDemo() {
      // anItem = new JMenuItem("Click Me!");
      // add(anItem);
    }
  }

  @Override
  public void focusGained(FocusEvent e) {
    for (PassingCallback<UIView> callback : gainedCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
    update();
  }

  @Override
  public void focusLost(FocusEvent e) {
    for (PassingCallback<UIView> callback : lostCallbacks) {
      DispatchQueue.manage(DispatchQueue.ui, () -> {
        callback.call(this);
      });
    }
    update();
  }

}
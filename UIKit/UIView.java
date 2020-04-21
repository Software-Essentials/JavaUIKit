package UIKit;

import static java.awt.RenderingHints.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A view is a component of a scene.
 * 
 * @see UINavigationView
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
@SuppressWarnings("serial")
public class UIView extends JPanel {

  protected UIWindow window = null;
  protected UIView parent = null;
  protected UINavigationView navigationView = null;

  protected UIColor foreground = null;
  protected UIColor background = null;

  protected int paddingTop = 0;
  protected int paddingBottom = 0;
  protected int paddingLeft = 0;
  protected int paddingRight = 0;

  public UIView() {
    this(null);
  }

  public UIView(UIBuilder content) {
    setBorder(new EmptyBorder(0, 0, 0, 0));
    setBackground(UIColor.clear.toAWT());
    this.setLayout(new GridLayout(1, 1));
  }

  public UINavigationView getNavigationView() {
    return navigationView;
  }

  public void setScene(UINavigationView navigationView) {
    if (this.navigationView == null) {
      this.navigationView = navigationView;
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
    render(g2);
  }

  public void setWindow(UIWindow window) {
    if (this.window == null) {
      this.window = window;
    }
    this.viewWillLoad();
  }

  public void accessibilityParent(UIView parent) {
    if (this.parent == null) {
      this.parent = parent;
    }
    this.viewWillLoad();
  }

  public void accessibilityForeground(UIColor foreground) {
    if (this.foreground == null) {
      foreground(foreground);
    }
    this.viewWillLoad();
  }

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
    setBorder(new EmptyBorder(paddingTop, paddingLeft, paddingBottom, paddingRight));
    return this;
  }

  public UIView padding(int top, int left, int bottom, int right) {
    setBorder(new EmptyBorder(top, left, bottom, right));
    return this;
  }

  public UIView foreground(UIColor color) {
    foreground = color;
    setForeground(color.toAWT());
    return this;
  }

  public UIView background(UIColor color) {
    background = color;
    setBackground(color.toAWT());
    return this;
  }

  public void update() {
    repaint();
  }

  public UIView parent() {
    return parent;
  }

  protected void render(Graphics2D g) {
  }

  protected void viewWillLoad() {
  }

}
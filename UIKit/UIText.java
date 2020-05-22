package UIKit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * UIText
 * 
 * @since 0.1.0
 */
@SuppressWarnings("serial")
public class UIText extends UIView {

  public JLabel component;

  private UI alignment = UI.center;

  private String text = "";
  private String fontFamily = "Helvetica Neue";
  private int fontWeight = Font.PLAIN;
  private int fontSize = 16;

  public UIText(String text) {
    super();
    this.text = text;
    this.setOpaque(true);
    component = new JLabel(text, SwingConstants.CENTER);
    component.setUI(UITextListener.labelUI);
    foreground(UIColor.black);
    updateFont();
    add(component);
  }

  @Override
  protected void render(Graphics2D g) {
    super.render(g);
    component.setForeground(foregroundColor.toAWT());
  }

  private void updateFont() {
    component.setFont(new Font(fontFamily, fontWeight, fontSize));
  }

  public String getText() {
    return text;
  }

  @Override
  public void setForeground(UIColor color) {
    System.out.println("SUPER SET COLOR " + color);
    component.setForeground(color.toAWT());
  }

  @Override
  public void setBackground(UIColor color) {
    super.setBackground(color);
  }

  // MARK: - Chaining methods

  public UIText background(UIColor color) {
    super.background(color);
    return this;
  }

  public UIText foreground(UIColor color) {
    try {
      super.foreground(color);
      component.setForeground(color.toAWT());
    } catch (NullPointerException e) {
      String simpleName = this.getClass().getSimpleName();
      UI.print("NilGuard: " + simpleName + ".foreground(UIColor) received a nil parameter. That's illegal!");
    }
    return this;
  }

  public UIText bold() {
    fontWeight = Font.BOLD;
    updateFont();
    return this;
  }

  public UIText italic() {
    fontWeight = Font.ITALIC;
    updateFont();
    return this;
  }

  public UIText font(int size) {
    fontSize = size;
    updateFont();
    return this;
  }

  public UIText font(String familyName) {
    fontFamily = familyName;
    updateFont();
    return this;
  }

  public UIText font(Font font) {
    fontFamily = font.getFontName();
    fontWeight= font.getStyle();
    fontSize = font.getSize();
    updateFont();
    return this;
  }

  public UIText align(UI direction) {
    this.alignment = direction;
    if (alignment == UI.left || alignment == UI.leading) {
      component.setHorizontalAlignment(SwingConstants.LEFT);
    } else if (alignment == UI.right || alignment == UI.trailing) {
      component.setHorizontalAlignment(SwingConstants.RIGHT);
    } else if (alignment == UI.bottom) {
      component.setVerticalAlignment(SwingConstants.BOTTOM);
    } else if (alignment == UI.top) {
      component.setVerticalAlignment(SwingConstants.TOP);
    } else {
      component.setHorizontalAlignment(SwingConstants.CENTER);
      component.setVerticalAlignment(SwingConstants.CENTER);
    }
    return this;
  }

  public UIText padding(int value) {
    return padding(value, value, value, value);
  }

  public UIText padding(UI side, int value) {
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
    setBorder(BorderFactory.createLineBorder(backgroundColor.toAWT(), value));
    return this;
  }

  public UIText padding(int top, int left, int bottom, int right) {
    paddingLeft = left;
    paddingRight = right;
    paddingTop = top;
    paddingBottom = bottom;
    setBorder(BorderFactory.createLineBorder(backgroundColor.toAWT(), top));
    return this;
  }

  // MARK: - Accessibility methods

  public JLabel accessibilityComponent() {
    return component;
  }

}
package UIKit;

import static java.awt.RenderingHints.*;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Foundation.Rectangle;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * UILabel
 * 
 * @since 0.1.0
 */
@SuppressWarnings("serial")
public class UITextField extends UIView {

  public JTextField component;

  private UI alignment = UI.center;

  private String placeholder = "";
  private UIColor placeholderColor = UIColor.systemGray2;
  private String fontFamily = "Helvetica Neue";
  private int fontWeight = Font.PLAIN;
  private int fontSize = 16;

  public UITextField() {
    this("");
  }

  public UITextField(String text) {
    component = new JTextField(text) {
      @Override
      public void setBorder(Border border) {
      }

      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        component.setCaretColor(normalForegroundColor.toAWT());
        if (placeholder == null || placeholder.length() == 0 || component.getText().length() > 0) {
          return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g.setColor(placeholderColor.toAWT());
        Font font = new Font(fontFamily, fontWeight, fontSize);
        if (alignment == UI.left || alignment == UI.leading) {
          int x = component.getInsets().left;
          int y = g.getFontMetrics().getMaxAscent() + component.getInsets().top;
          g.setFont(font);
          g.drawString(placeholder, x, y);
        } else {
          drawCenteredString(g, placeholder, new Rectangle(component.getInsets().left, component.getInsets().top,
              component.getWidth(), component.getHeight()), font);
        }
        component.setCaretColor(placeholderColor.toAWT());
      }
    };
    component.addFocusListener(this);
    onFocus(() -> {
    });
    onFocusLost(() -> {
    });
    component.setFont(new Font(fontFamily, fontWeight, fontSize));
    align(UI.center);
    add(component);
  }

  /**
   * Draw a String centered in the middle of a Rectangle.
   *
   * @param g    The Graphics instance.
   * @param text The String to draw.
   * @param rect The Rectangle to center the text in.
   */
  public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
    FontMetrics metrics = g.getFontMetrics(font);
    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    g.setFont(font);
    g.drawString(text, x, y);
  }

  public void setText(String text) {
    component.setText(text);
  }

  public String getText() {
    return component.getText();
  }

  public void requestFocus() {
    component.requestFocusInWindow();
  }

  public void selectAll() {
    component.selectAll();
  }

  public void setCaret(int position) {
    component.setCaretPosition(position);
  }

  public void setCaret(UIColor color) {
    component.setCaretColor(color.toAWT());
  }

  public void setEditable() {
    component.setEditable(true);
  }

  public void setEditable(Boolean value) {
    component.setEditable(value);
  }

  private void updateFont() {
    component.setFont(new Font(fontFamily, fontWeight, fontSize));
  }

  // MARK: - Chaining methods

  public UITextField tip(String tooltip) {
    component.setToolTipText(tooltip);
    return this;
  }

  public UITextField bold() {
    fontWeight = Font.BOLD;
    updateFont();
    return this;
  }

  @Override
  public UITextField background(UIColor color) {
    component.setBackground(color.toAWT());
    super.background(color);
    return this;
  }

  @Override
  public UITextField foreground(UIColor color) {
    normalForegroundColor = color;
    component.setForeground(color.toAWT());
    component.setCaretColor(color.toAWT());
    super.foreground(color);
    return this;
  }

  public UITextField align(UI direction) {
    this.alignment = direction;
    if (alignment == UI.left || alignment == UI.leading) {
      component.setHorizontalAlignment(SwingConstants.LEFT);
    } else if (alignment == UI.right || alignment == UI.trailing) {
      component.setHorizontalAlignment(SwingConstants.RIGHT);
    } else {
      component.setHorizontalAlignment(SwingConstants.CENTER);
    }
    return this;
  }

  /**
   * Adds placeholder text when there is no text inside the UITextField.
   * 
   * @param text Placeholder text
   * @return
   */
  public UITextField placeholder(String text) {
    placeholder = text;
    return this;
  }

  /**
   * Sets the placeholder color
   * 
   * @param color Placeholder color
   * @return
   */
  public UITextField placeholderColor(UIColor color) {
    placeholderColor = color;
    return this;
  }

  // MARK: - Accessibility methods

  public JTextField accessibilityComponent() {
    return component;
  }

}
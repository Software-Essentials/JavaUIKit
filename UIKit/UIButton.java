package UIKit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

/**
 * UIButton
 * 
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
@SuppressWarnings("serial")
public class UIButton extends JButton {

  private UIColor hoverBackgroundColor;
  private UIColor pressedBackgroundColor;
  private Font font = new Font("Helvetica Neue", Font.PLAIN, 20);
  private int cornerRadius = 0;

  public UIButton() {
    this(null);
  }

  public UIButton(String title) {
    this(title, true);
  }

  public UIButton(String title, boolean enabled) {
    super(title);
    setBorderPainted(false);
    setContentAreaFilled(false);
    setCornerRadius(10);
    setBackground(new Color(196, 196, 196, 196));
    setHoverBackgroundColor(new UIColor(196, 196, 196, 128));
    setPressedBackgroundColor(new UIColor(196, 196, 196, 255));
    setFont(font);
    setForeground(new Color(96, 96, 96));
  }

  @Override
  protected void paintComponent(Graphics g) {
    font = new Font("Helvetica Neue", Font.PLAIN, getHeight() / 3);
    setFont(font);
    if (getModel().isPressed()) {
      g.setColor(pressedBackgroundColor.toAWT());
    } else if (getModel().isRollover()) {
      g.setColor(hoverBackgroundColor.toAWT());
    } else {
      g.setColor(getBackground());
    }
    g.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
    super.paintComponent(g);
  }

  @Override
  public void setContentAreaFilled(boolean b) {
  }

  public void setCornerRadius(int radius) {
    cornerRadius = radius;
  }

  public int getCornerRadius() {
    return cornerRadius;
  }

  public UIColor getHoverBackgroundColor() {
    return hoverBackgroundColor;
  }

  public void setHoverBackgroundColor(UIColor hoverBackgroundColor) {
    this.hoverBackgroundColor = hoverBackgroundColor;
  }

  public UIColor getPressedBackgroundColor() {
    return pressedBackgroundColor;
  }

  public void setPressedBackgroundColor(UIColor pressedBackgroundColor) {
    this.pressedBackgroundColor = pressedBackgroundColor;
  }

}
package UIKit;

import java.awt.Color;

/**
 * UIColor
 * 
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
public class UIColor {

  @SuppressWarnings("unused")
  private static final UIColor instance = new UIColor();
  public static UIColor clear = new UIColor(0, 0, 0, 0);
  public static UIColor black = new UIColor(0, 0, 0);
  public static UIColor white = new UIColor(1f, 1f, 1f);
  public static UIColor systemBlue;
  public static UIColor systemGreen;
  public static UIColor systemIndigo;
  public static UIColor systemOrange;
  public static UIColor systemPink;
  public static UIColor systemPurple;
  public static UIColor systemRed;
  public static UIColor systemTeal;
  public static UIColor systemYellow;
  public static UIColor systemGray;
  public static UIColor systemGray2;
  public static UIColor systemGray3;
  public static UIColor systemGray4;
  public static UIColor systemGray5;
  public static UIColor systemGray6;

  public static UIColor create(Color color) {
    return new UIColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  private Color awtColor;

  private UIColor() {
    updateConstantConstraints();
  }

  public UIColor(int red, int green, int blue, int alpha) {
    awtColor = new Color(red, green, blue, alpha);
  }

  public UIColor(int red, int green, int blue) {
    awtColor = new Color(red, green, blue);
  }

  public UIColor(float red, float green, float blue, float alpha) {
    awtColor = new Color(red, green, blue, alpha);
  }

  public UIColor(float red, float green, float blue) {
    awtColor = new Color(red, green, blue);
  }

  private void updateConstantConstraints() {
    systemBlue = new UIColor(0, 122, 255);
    systemGreen = new UIColor(52, 199, 89);
    systemIndigo = new UIColor(88, 86, 214);
    systemOrange = new UIColor(255, 149, 0);
    systemPink = new UIColor(255, 45, 89);
    systemPurple = new UIColor(175, 82, 222);
    systemRed = new UIColor(255, 59, 48);
    systemTeal = new UIColor(90, 200, 250);
    systemYellow = new UIColor(255, 204, 0);
    systemGray = new UIColor(142, 142, 147);
    systemGray2 = new UIColor(174, 174, 178);
    systemGray3 = new UIColor(199, 199, 204);
    systemGray4 = new UIColor(209, 209, 214);
    systemGray5 = new UIColor(229, 229, 234);
    systemGray6 = new UIColor(242, 242, 247);
  }

  public Color toAWT() {
    return awtColor;
  }

}
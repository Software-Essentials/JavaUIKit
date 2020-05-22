package UIKit;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * UIColor
 * 
 * @since 0.1.0
 */
public class UIColor {

  private static final Map<String, UIColor> colors = new HashMap<String, UIColor>();

  public static UIColor clear = new UIColor(0, 0, 0, 0, "clear");
  public static UIColor black = new UIColor(0, 0, 0, 255, "black");
  public static UIColor white = new UIColor(255, 255, 255, 255, "white");
  public static UIColor red = new UIColor(255, 0, 0, 255, "red");
  public static UIColor yellow = new UIColor(255, 255, 255, 255, "yellow");
  public static UIColor green = new UIColor(0, 255, 0, 255, "green");
  public static UIColor cyan = new UIColor(0, 255, 255, 255, "cyan");
  public static UIColor blue = new UIColor(0, 0, 255, 255, "blue");
  public static UIColor magenta = new UIColor(255, 0, 255, 255, "magenta");
  public static UIColor lightGray = new UIColor(174, 174, 178, 255, "lightGray");
  public static UIColor gray = new UIColor(99, 99, 102, 255, "gray");
  public static UIColor darkGray = new UIColor(72, 72, 74, 255, "darkGray");
  public static UIColor systemBackground = new UIColor(255, 255, 255, 255, "systemBackground");
  public static UIColor systemBlue = new UIColor(0, 122, 255, 255, "systemBlue");
  public static UIColor systemGreen = new UIColor(52, 199, 89, 255, "systemGreen");
  public static UIColor systemIndigo = new UIColor(88, 86, 214, 255, "systemIndigo");
  public static UIColor systemOrange = new UIColor(255, 149, 0, 255, "systemOrange");
  public static UIColor systemPink = new UIColor(255, 45, 89, 255, "systemPink");
  public static UIColor systemPurple = new UIColor(175, 82, 222, 255, "systemPurple");
  public static UIColor systemRed = new UIColor(255, 59, 48, 255, "systemRed");
  public static UIColor systemTeal = new UIColor(90, 200, 250, 255, "systemTeal");
  public static UIColor systemYellow = new UIColor(255, 204, 0, 255, "systemYellow");
  public static UIColor systemGray = new UIColor(142, 142, 147, 255, "systemGray");
  public static UIColor systemGray2 = new UIColor(174, 174, 178, 255, "systemGray2");
  public static UIColor systemGray3 = new UIColor(199, 199, 204, 255, "systemGray3");
  public static UIColor systemGray4 = new UIColor(209, 209, 214, 255, "systemGray4");
  public static UIColor systemGray5 = new UIColor(229, 229, 234, 255, "systemGray5");
  public static UIColor systemGray6 = new UIColor(242, 242, 247, 255, "systemGray6");

  @Deprecated
  public static UIColor create(Color color) {
    updateConstantConstraints();
    return new UIColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  public static UIColor fromAWT(Color color) {
    updateConstantConstraints();
    return new UIColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  public static UIColor fromHex(String hex) {
    updateConstantConstraints();
    Color color = Color.decode("#" + hex);
    return new UIColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  static void updateConstantConstraints() {

    // Update standard color database
    clear.update(new UIColor(0, 0, 0, 0, "clear"));
    black.update(new UIColor(0, 0, 0, 255, "black"));
    white.update(new UIColor(255, 255, 255, 255, "white"));
    red.update(new UIColor(255, 0, 0, 255, "red"));
    yellow.update(new UIColor(255, 255, 255, 255, "yellow"));
    green.update(new UIColor(0, 255, 0, 255, "green"));
    cyan.update(new UIColor(0, 255, 255, 255, "cyan"));
    blue.update(new UIColor(0, 0, 255, 255, "blue"));
    magenta.update(new UIColor(255, 0, 255, 255, "magenta"));
    lightGray.update(new UIColor(174, 174, 178, 255, "lightGray"));
    gray.update(new UIColor(99, 99, 102, 255, "gray"));
    darkGray.update(new UIColor(72, 72, 74, 255, "darkGray"));

    // Update system color database
    systemBackground.update(new UIColor(255, 255, 255, 255, "systemBackground"));
    systemBlue.update(new UIColor(0, 122, 255, 255, "systemBlue"));
    systemGreen.update(new UIColor(52, 199, 89, 255, "systemGreen"));
    systemIndigo.update(new UIColor(88, 86, 214, 255, "systemIndigo"));
    systemOrange.update(new UIColor(255, 149, 0, 255, "systemOrange"));
    systemPink.update(new UIColor(255, 45, 89, 255, "systemPink"));
    systemPurple.update(new UIColor(175, 82, 222, 255, "systemPurple"));
    systemRed.update(new UIColor(255, 59, 48, 255, "systemRed"));
    systemTeal.update(new UIColor(90, 200, 250, 255, "systemTeal"));
    systemYellow.update(new UIColor(255, 204, 0, 255, "systemYellow"));
    systemGray.update(new UIColor(142, 142, 147, 255, "systemGray"));
    systemGray2.update(new UIColor(174, 174, 178, 255, "systemGray2"));
    systemGray3.update(new UIColor(199, 199, 204, 255, "systemGray3"));
    systemGray4.update(new UIColor(209, 209, 214, 255, "systemGray4"));
    systemGray5.update(new UIColor(229, 229, 234, 255, "systemGray5"));
    systemGray6.update(new UIColor(242, 242, 247, 255, "systemGray6"));

    // Update name database
    if (colors != null) {
      for (String name : colors.keySet()) {
        UIColor color = colors.get(name);
        color.name = name;
        colors.put(name, color);
      }
    }
  }

  /**
   * Sets a predefined color
   * 
   * @param name  Name of the predefined color.
   * @param color Value of the predefined color.
   */
  public static void set(String name, UIColor color) {
    colors.put(name, color);
  }

  private Color awtColor;
  private String name;
  private boolean system = false;

  /**
   * Loads a predefined color to the UIColor object.
   * 
   * @param name Name of the predefined color.
   */
  public UIColor(String name) {
    awtColor = colors.get(name).toAWT();
  }

  /**
   * @param red   0-255
   * @param green 0-255
   * @param blue  0-255
   */
  public UIColor(int red, int green, int blue) {
    awtColor = new Color(red, green, blue);
  }

  /**
   * @param red   0-255
   * @param green 0-255
   * @param blue  0-255
   * @param alpha 0-255
   */
  public UIColor(int red, int green, int blue, int alpha) {
    awtColor = new Color(red, green, blue, alpha);
  }

  /**
   * @param red   0-255
   * @param green 0-255
   * @param blue  0-255
   * @param alpha 0-255
   * @param name  System name
   */
  private UIColor(int red, int green, int blue, int alpha, String name) {
    awtColor = new Color(red, green, blue, alpha);
    system = true;
    this.name = name;
  }

  /**
   * @param red   0-1
   * @param green 0-1
   * @param blue  0-1
   */
  public UIColor(float red, float green, float blue) {
    awtColor = new Color(red, green, blue);
  }

  /**
   * @param red   0-1
   * @param green 0-1
   * @param blue  0-1
   * @param alpha 0-1
   */
  public UIColor(float red, float green, float blue, float alpha) {
    awtColor = new Color(red, green, blue, alpha);
  }

  /**
   * Convert UIColor to awt Color.
   * 
   * @see Color
   * @return Awt Color.
   */
  public Color toAWT() {
    updateConstantConstraints();
    return awtColor;
  }

  private void update(UIColor color) {
    awtColor = color.awtColor;
  }

  // MARK: - Chaining methods

  /**
   * Sets alpha to 0
   */
  public UIColor solidify() {
    return new UIColor(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue(), 255);
  }

  public UIColor transpire() {
    return this.transpire(2);
  }

  public UIColor transpire(float factor) {
    int red = awtColor.getRed();
    int green = awtColor.getGreen();
    int blue = awtColor.getBlue();
    int alpha = awtColor.getAlpha();
    return new UIColor(red, green, blue, (int) (alpha / factor));
  }

  public UIColor brighten() {
    awtColor = awtColor.brighter();
    return this;
  }

  public UIColor darken() {
    this.awtColor = awtColor.darker();
    return this;
  }

  public UIColor darken(int multiplier) {
    for (int i = 0; i < multiplier; i++) {
      this.awtColor = awtColor.darker();
    }
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    return awtColor.equals(obj);
  }

  @Override
  public String toString() {
    String description = "UIColor";
    if (system && name != null) {
      description += "." + name;
    } else {
      description += "(red: " + awtColor.getRed() + ", green: " + awtColor.getGreen() + ", blue: " + awtColor.getBlue();
      int alpha = awtColor.getAlpha();
      if (alpha < 255) {
        description += ", alpha: " + alpha;
      }
      description += ") ";
      if (name != null) {
        description += "[" + name + "]";
      }
    }
    return description;
  }

}
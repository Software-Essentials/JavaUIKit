package UIKit;

import java.awt.Toolkit;

public class UIScreen {

  public final float width = (float) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  public final float height = (float) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  public static final UIScreen main = new UIScreen();

}
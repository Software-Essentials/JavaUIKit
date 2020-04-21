package UIKit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * UILabel
 * 
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
@SuppressWarnings("serial")
public class UILabel extends UIView {

  public JLabel component;

  public UILabel() {
    this(null);
  }

  public UILabel(String text) {
    super();
    this.setOpaque(true);
    this.setBackground(Color.BLACK);
    component = new JLabel(text, SwingConstants.CENTER);
    component.setUI(UITextListener.labelUI);
    add(component);
  }

  public UILabel background(UIColor color) {
    super.background(color);
    return this;
  }

  public UILabel foreground(UIColor color) {
    component.setForeground(color.toAWT());
    return this;
  }

}
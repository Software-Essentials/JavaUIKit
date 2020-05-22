package UIKit;

import java.awt.Cursor;

/**
 * UIButton
 * 
 * @since 0.1.0
 */
@SuppressWarnings("serial")
public class UIButton extends UIText {

  public UIButton() {
    this(null);
  }

  public UIButton(String title) {
    this(title, true);
  }

  public UIButton(String title, boolean enabled) {
    super(title);
    setCornerRadius(16);
    padding(8);
    bold();
    background(UIColor.clear);
    foreground(UIColor.systemBlue);
    onHoverStart((uiButton) -> {
      setCursor(new Cursor(Cursor.HAND_CURSOR));
    });
    onHoverEnd((uiButton) -> {
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    });
  }

  // MARK: - Chaining methods

  public UIButton background(UIColor color, UIView.State state) {
    switch (state) {
      case normal:
        this.background(color);
        break;
      case hover:
        hoverBackgroundColor = color;
        break;
      case active:
        pressedBackgroundColor = color;
        break;
      case disabled:
        disabledBackgroundColor = color;
        break;
    }
    return this;
  }

  public UIButton background(UIColor color) {
    try {
      super.background(color);
    } catch (NullPointerException exception) {
      String simpleName = this.getClass().getSimpleName();
      UI.print("NilGuard: " + simpleName + ".background(UIColor) received a nil parameter. That's illegal!");
    }
    return this;
  }

  public UIButton foreground(UIColor color, UIView.State state) {
    switch (state) {
      case normal:
        this.foreground(color);
        break;
      case hover:
        hoverForegroundColor = color;
        break;
      case active:
        pressedForegroundColor = color;
        break;
      case disabled:
        disabledForegroundColor = color;
        break;
    }
    return this;
  }

  public UIButton foreground(UIColor color) {
    try {
      super.foreground(color);
      pressedForegroundColor = color.transpire(3);
      hoverForegroundColor = color.transpire(1.5f);
    } catch (NullPointerException exception) {
      String simpleName = this.getClass().getSimpleName();
      UI.print("NilGuard: " + simpleName + ".foreground(UIColor) received a nil parameter. That's illegal!",
          exception.getStackTrace());
    }
    return this;
  }

  @Override
  public UIButton disabled() {
    super.disabled();
    return this;
  }

  @Override
  public UIButton disabled(boolean state) {
    super.disabled(state);
    return this;
  }

}
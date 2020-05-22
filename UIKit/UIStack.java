package UIKit;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.SpringLayout;

/**
 * UIStack
 * 
 * @since 0.1.0
 */
@SuppressWarnings("serial")
public class UIStack extends UIView {

  private int spacing = 0;
  private float percentage = 0;
  private UI division = UI.divideEqually;
  private GridLayout gridLayout = new GridLayout(1, 0, 0, spacing);
  private SpringLayout springLayout = new SpringLayout();
  private Component[] views;
  private UI orientation = UI.horizontal;

  public UIStack(Component... views) {
    this(null, 0, views);
  }

  public UIStack(int spacing, Component... views) {
    this(null, spacing, views);
  }

  public UIStack(float spacingPercentage, Component... views) {
    this(null, spacingPercentage, views);
  }

  public UIStack(UI orientation, Component... views) {
    this(orientation, 0, views);
  }

  public UIStack(UI orientation, float spacingPercentage, Component... views) {
    super();
    this.orientation = orientation == null ? this.orientation : orientation;
    percentage = spacingPercentage;
    for (Component component : views) {
      if (component instanceof UIView) {
        // ((UIView) component).accessibilityWindow(window);
        ((UIView) component).accessibilityParent(this);
      }
    }
    this.views = views;
    viewWillLoad();
  }

  public UIStack(UI orientation, int spacing, Component... views) {
    super();
    this.orientation = orientation == null ? this.orientation : orientation;
    this.spacing = spacing;
    for (Component component : views) {
      if (component instanceof UIView) {
        // ((UIView) component).accessibilityWindow(window);
        ((UIView) component).accessibilityParent(this);
      }
    }
    this.views = views;
    viewWillLoad();
  }

  @Override
  protected void viewWillLoad() {
    if (orientation == UI.horizontal) {
      if (division == UI.divideEqually) {
        gridLayout = new GridLayout(1, 0, 0, spacing);
        setLayout(gridLayout);
      } else {
        springLayout = new SpringLayout();
        setLayout(springLayout);
      }
      shouldLayoutSubviews();
      // UIStackUtilities.makeCompactGrid(this, 1, views.length, 3, 3, 3, 3);
    } else if (orientation == UI.vertical) {
      if (division == UI.divideEqually) {
        gridLayout = new GridLayout(0, 1, spacing, 0);
        setLayout(gridLayout);
      } else {
        springLayout = new SpringLayout();
        setLayout(springLayout);
      }
      shouldLayoutSubviews();
      // UIStackUtilities.makeCompactGrid(this, views.length, 1, 3, 3, 3, 3);
    } else if (orientation == UI.depth) {
      setLayout(null);
      shouldLayoutSubviews();
    }
  }

  private int calculateSpacing(int viewCount, float percentage) {
    float size = orientation == UI.horizontal ? getWidth() : getHeight();
    return (int) ((size / viewCount) * percentage);
  }

  protected void shouldLayoutSubviews() {
    for (Component view : views) {
      if (view instanceof UIView && normalForegroundColor != null) {
        ((UIView) view).accessibilityForeground(normalForegroundColor);
      }
      add(view);
    }
    revalidate();
    repaint();
  }

  public void setSubview(int index, Component view) {
    try {
      views[index] = view;
      remove(index);
      add(view, index);
      shouldLayoutSubviews();
    } catch (ArrayIndexOutOfBoundsException exception) {
      UI.print(
          "Illegal operation warning: Trying to change a subview that is not in the view hierarchy. That's illegal!");
    }
  }

  @Override
  public Component add(Component component) {
    Component[] newViews = new Component[views.length + 1];
    for (int i = 0; i < views.length; i++) {
      newViews[i] = views[i];
    }
    if (component instanceof UIView) {
      // ((UIView) component).accessibilityWindow(window);
      ((UIView) component).accessibilityParent(this);
    }
    newViews[views.length] = component;
    views = newViews;
    return super.add(component);
  }

  public int getSpacing() {
    return spacing;
  }

  public void setSpacing(int spacing) {
    this.percentage = 0;
    this.spacing = spacing;
  }

  public float getSpacingPercentage() {
    return percentage;
  }

  public void setSpacingPercentage(float percentage) {
    this.percentage = percentage;
  }

  @Override
  protected void render(Graphics2D g) {
    super.render(g);
    if (orientation == UI.depth) {
      for (Component view : views) {
        Rectangle bounds = getBounds();
        view.setBounds(0, 0, bounds.width, bounds.height);
      }
    } else {
      if (percentage > 0) {
        spacing = calculateSpacing(views.length, percentage);
      }
      if (orientation == UI.horizontal) {
        // gridLayout.setHgap(spacing);
      } else if (orientation == UI.vertical) {
        // gridLayout.setVgap(spacing);
      }
    }
  }

}
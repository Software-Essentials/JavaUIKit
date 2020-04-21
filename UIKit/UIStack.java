package UIKit;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;

/**
 * UIStackView
 * 
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
@SuppressWarnings("serial")
public class UIStack extends UIView {

  private int spacing = 0;
  private float percentage = 0;
  private GridLayout gridLayout = new GridLayout(1, 0, 0, spacing);
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
    this.views = views;
    viewWillLoad();
  }

  public UIStack(UI orientation, int spacing, Component... views) {
    super();
    this.orientation = orientation == null ? this.orientation : orientation;
    this.spacing = spacing;
    this.views = views;
    for (Component component : views) {
      if (component instanceof UIView) {
        ((UIView) component).accessibilityParent(this);
      }
    }
    viewWillLoad();
  }

  @Override
  protected void viewWillLoad() {
    if (orientation == UI.horizontal) {
      gridLayout = new GridLayout(1, 0, 0, spacing);
      setLayout(gridLayout);
      for (Component view : views) {
        if (view instanceof UIView && foreground != null) {
          ((UIView) view).accessibilityForeground(foreground);
        }
        add(view);
      }
    } else if (orientation == UI.vertical) {
      gridLayout = new GridLayout(0, 1, spacing, 0);
      setLayout(gridLayout);
      for (Component view : views) {
        if (view instanceof UIView && foreground != null) {
          ((UIView) view).accessibilityForeground(foreground);
        }
        add(view);
      }
    } else if (orientation == UI.depth) {
      setLayout(null);
      for (Component view : views) {
        if (view instanceof UIView && foreground != null) {
          ((UIView) view).accessibilityForeground(foreground);
        }
        add(view);
      }
    }
  }

  private int calculateSpacing(int viewCount, float percentage) {
    float size = orientation == UI.horizontal ? getWidth() : getHeight();
    return (int) ((size / viewCount) * percentage);
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
        gridLayout.setHgap(spacing);
      } else if (orientation == UI.vertical) {
        gridLayout.setVgap(spacing);
      }
    }
  }

}
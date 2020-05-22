package UIKit;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 * UIStackView
 * 
 * @since 0.1.0
 */
@SuppressWarnings("serial")
public class UIDivided extends UIView {

  public JSplitPane component;

  private UI orientation = UI.horizontal;
  private Component[] views;

  public UIDivided(Component... views) {
    this(UI.horizontal, views);
  }

  public UIDivided(UI orientation, Component... views) {
    super();
    this.orientation = orientation;
    this.setOpaque(true);
    background(UIColor.white);
    this.views = views;
    shouldLayoutSubviews();
  }

  // @Override
  // public Component add(Component component) {
  // Component[] newViews = new Component[views.length + 1];
  // for (int i = 0; i < views.length; i++) {
  // newViews[i] = views[i];
  // }
  // newViews[views.length] = component;
  // views = newViews;
  // return super.add(component);
  // return null;
  // }

  public void shouldLayoutSubviews() {
    if (views.length == 2) {
      for (Component component : views) {
        if (component instanceof UIView) {
          // ((UIView) component).accessibilityWindow(window);
          ((UIView) component).accessibilityParent(this);
        }
      }
      int swingOrientation = this.orientation == UI.vertical ? SwingConstants.HORIZONTAL : SwingConstants.VERTICAL;
      component = new JSplitPane(swingOrientation, views[0], views[1]);
      component.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      component.setOpaque(true);
      removeAll();
      add(component);
      revalidate();
      repaint();
    } else if (views.length > 2) {
      UI.print("Illegal operation warning: Trying lay out UIDivided with more than 2 subviews. That's illegal!");
    } else {
      UI.print("Illegal operation warning: Trying lay out UIDivided with less than 2 subviews. That's illegal!");
    }
  }

  public void setSubview(int index, UIView view) {
    try {
      // view.accessibilityWindow(window);
      view.accessibilityParent(this);
      views[index] = view;
      shouldLayoutSubviews();
    } catch (ArrayIndexOutOfBoundsException exception) {
      UI.print("Trying to change a subview that is not in the view hierarchy");
    }
  }

  public void setSubview(int index, Component view) {
    try {
      if (view instanceof UIView) {
        // ((UIView) view).accessibilityWindow(window);
        ((UIView) view).accessibilityParent(this);
      }
      views[index] = view;
      // remove(index);
      // add(view, index);
      shouldLayoutSubviews();
    } catch (ArrayIndexOutOfBoundsException exception) {
      UI.print("Trying to change a subview that is not in the view hierarchy");
    }
  }

  // MARK: - Chaining methods

  public UIDivided background(UIColor color) {
    super.background(color);
    return this;
  }

  public UIDivided divider(int location) {
    return divider(location, -1);
  }

  public UIDivided divider(int location, int size) {
    if (component == null) {
      UI.print("Trying to set property 'divider' while view is not properly initialized");
    } else {
      component.setDividerLocation(location);
      if (size >= 0) {
        component.setDividerSize(size);
      }
    }
    return this;
  }

  public UIDivided resizeWeight(double value) {
    component.setResizeWeight(value);
    return this;
  }

  // MARK: - Accessibility methods

  public JSplitPane accessibilityComponent() {
    return component;
  }

}
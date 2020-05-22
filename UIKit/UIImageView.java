package UIKit;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

/**
 * UIImageView
 * 
 * @see UIImageView
 * @since 0.1.0
 */
@SuppressWarnings("serial")
public class UIImageView extends UIView {

  private UIImage image;

  public UIImageView(UIImage image) {
    this.image = image;
  }

  @Override
  protected void render(Graphics2D g) {
    super.render(g);
    if (image != null) {
      BufferedImage bImage = image.toAWTBuffered();
      if (bImage != null) {
        float panelWidth = getWidth();
        float panelHeight = getHeight();
        float imageWidth = bImage.getWidth();
        float imageHeight = bImage.getHeight();
        float widthFactor = panelWidth / imageWidth;
        float heightFactor = panelHeight / imageHeight;
        float factor = widthFactor < heightFactor ? heightFactor : widthFactor;
        int width = (int) (imageWidth * factor);
        int height = (int) (imageHeight * factor);
        int widthMargin = (int) (panelWidth - width) / 2;
        int heightMargin = (int) (panelHeight - height) / 2;
        g.drawImage(bImage, widthMargin, heightMargin, width, height, this);
      }
    }
    update();
  }

}
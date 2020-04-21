package UIKit;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * UIImage
 * 
 * @since 0.1.0
 * @author Lawrence Bensaid <lawrencebensaid@icloud.com>
 */
public class UIImage {

  private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
  private Image image;

  public UIImage(String path) {
    image = toolkit.getImage(path);
  }

  public Image toAWT() {
    return image;
  }

  public BufferedImage toAWTBuffered() {
    return toBufferedImage(this);
  }

  /**
   * Converts a given Image into a BufferedImage.
   *
   * @param image The Image to be converted
   * @return The converted BufferedImage
   */
  public static BufferedImage toBufferedImage(UIImage uiImage) {
    Image image = uiImage.toAWT();
    if (image instanceof BufferedImage) {
      return (BufferedImage) image;
    }
    BufferedImage bimage = null;
    try {
      bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
      Graphics2D bGr = bimage.createGraphics();
      bGr.drawImage(image, 0, 0, null);
      bGr.dispose();
    } catch (IllegalArgumentException e) {
    }
    return bimage;
  }

}
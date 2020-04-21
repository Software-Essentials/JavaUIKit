package UIKit;

@SuppressWarnings("unused")
public class UI {

  public static final UI horizontal = new UI((byte) 0);
  public static final UI vertical = new UI((byte) 1);
  public static final UI depth = new UI((byte) 2);
  public static final UI left = new UI((byte) 3);
  public static final UI right = new UI((byte) 4);
  public static final UI top = new UI((byte) 5);
  public static final UI bottom = new UI((byte) 6);
  public static final UI leading = new UI((byte) 7);
  public static final UI trailing = new UI((byte) 8);
  private final byte code;

  private UI(byte code) {
    this.code = code;
  }

}
package UIKit;

import java.util.regex.Pattern;

import Foundation.*;

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
  public static final UI center = new UI((byte) 9);
  public static final UI divideProportionally = new UI((byte) 10);
  public static final UI divideEqually = new UI((byte) 11);
  private final byte code;

  private UI(byte code) {
    this.code = code;
  }

  /**
   * Should only be used by UIKit.
   */
  static void print(Object message) {
    FD.print(FD.TERMCOL_BLUE + "[", "UIKit", "]", FD.TERMCOL_YELLOW + message + FD.TERMCOL_RESET);
  }

  /**
   * Should only be used by UIKit.
   */
  static void print(Object message, StackTraceElement[] stackTrace) {
    for (StackTraceElement element : stackTrace) {
      if (!element.toString().startsWith("UIKit.")) {
        String[] parts = element.getClassName().split(Pattern.quote("."));
        String path = "";
        for (String part : parts) {
          path += "/" + part;
        }
        path = (path + ".java").substring(1);
        if (FileSystem.fileExists("src/" + path)) {
          Device.main.executeSync("code -g " + "src/" + path + ":" + element.getLineNumber());
        }
        break;
      }
    }
    print(message);
  }

}
package Foundation;

@SuppressWarnings("unused")
public class FD {

  public class KeyStroke {

    public KeyStroke(final int key, final int keyMask) {
      super();
    }

  }

  public static final class Key {

    public static final class Mask {
  
      public static final Mask shift = new Mask(1);
      public static final Mask control = new Mask(2);
      public static final Mask command = new Mask(4);
      public static final Mask option = new Mask(8);

      public final int code;
  
      private Mask(int code) {
        this.code = code;
      }
      
    }

    public static final Key _0 = new Key(48);
    public static final Key _1 = new Key(49);
    public static final Key _2 = new Key(50);
    public static final Key _3 = new Key(51);
    public static final Key _4 = new Key(52);
    public static final Key _5 = new Key(53);
    public static final Key _6 = new Key(54);
    public static final Key _7 = new Key(55);
    public static final Key _8 = new Key(56);
    public static final Key _9 = new Key(57);
    public static final Key A = new Key(65);
    public static final Key B = new Key(66);
    public static final Key C = new Key(67);
    public static final Key D = new Key(68);
    public static final Key E = new Key(69);
    public static final Key F = new Key(70);
    public static final Key G = new Key(71);
    public static final Key H = new Key(72);
    public static final Key I = new Key(73);
    public static final Key J = new Key(74);
    public static final Key K = new Key(75);
    public static final Key L = new Key(76);
    public static final Key M = new Key(77);
    public static final Key N = new Key(78);
    public static final Key O = new Key(79);
    public static final Key P = new Key(80);
    public static final Key Q = new Key(81);
    public static final Key R = new Key(82);
    public static final Key S = new Key(83);
    public static final Key T = new Key(84);
    public static final Key U = new Key(85);
    public static final Key V = new Key(86);
    public static final Key W = new Key(87);
    public static final Key X = new Key(88);
    public static final Key Y = new Key(89);
    public static final Key Z = new Key(90);
    public static final Key enter = new Key('\n');
    public static final Key backspace = new Key('\b');
    public static final Key tab = new Key('\t');
    public static final Key shift = new Key(16);
    public static final Key control = new Key(17);
    public static final Key option = new Key(18);
    public static final Key capslock = new Key(20);
    public static final Key escape = new Key(27);
    public static final Key space = new Key(32);
    public static final Key end = new Key(35);
    public static final Key home = new Key(36);

    public final int code;

    private Key(int code) {
      this.code = code;
    }
    
  }

  public static final String TERMCOL_RESET = "\u001B[0m";
  public static final String TERMCOL_BOLD = "\u001B[1m";

  public static final String TERMCOL_BLACK = "\u001B[30m";
  public static final String TERMCOL_RED = "\u001B[31m";
  public static final String TERMCOL_GREEN = "\u001B[32m";
  public static final String TERMCOL_YELLOW = "\u001B[33m";
  public static final String TERMCOL_BLUE = "\u001B[34m";
  public static final String TERMCOL_PURPLE = "\u001B[35m";
  public static final String TERMCOL_CYAN = "\u001B[36m";
  public static final String TERMCOL_WHITE = "\u001B[37m";

  public static final String TERMCOL_BLACK_BG = "\u001B[40m";
  public static final String TERMCOL_RED_BG = "\u001B[41m";
  public static final String TERMCOL_GREEN_BG = "\u001B[42m";
  public static final String TERMCOL_YELLOW_BG = "\u001B[43m";
  public static final String TERMCOL_BLUE_BG = "\u001B[44m";
  public static final String TERMCOL_PURPLE_BG = "\u001B[45m";
  public static final String TERMCOL_CYAN_BG = "\u001B[46m";
  public static final String TERMCOL_WHITE_BG = "\u001B[47m";

  public enum Notation {
    SI, IEC
  }

  public static final FD.Notation notationSI = Notation.SI;
  public static final FD.Notation notationIEC = Notation.IEC;
  private final byte code;

  private FD(final byte code) {
    this.code = code;
  }

  public static void print(final String message) {
    System.out.println(message);
  }

  public static void print(final String prefix, final String sender, final String postfix, final Object... messages) {
    String line = FD.TERMCOL_BOLD + prefix + sender + postfix + FD.TERMCOL_RESET + " ";
    for (final Object message : messages) {
      line += message;
    }
    print(line);
  }

  public static void print(final Object... messages) {
    String line = "";
    for (final Object message : messages) {
      line += message;
    }
    print(line);
  }

}
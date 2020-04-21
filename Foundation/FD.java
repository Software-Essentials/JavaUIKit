package Foundation;

@SuppressWarnings("unused")
public class FD {

    public enum Notation {
        SI, IEC
    }

    public static final FD.Notation notationSI = Notation.SI;
    public static final FD.Notation notationIEC = Notation.IEC;
    private final byte code;
  
    private FD(byte code) {
      this.code = code;
    }

    public static void print(Object message) {
        System.out.println(message);
    }

}
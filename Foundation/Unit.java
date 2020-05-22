package Foundation;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Unit {

  public static String toByte(long bytes, FD.Notation notation) {
    if (notation == FD.notationSI) {
      return byteToSINotation(bytes);
    } else if (notation == FD.notationIEC) {
      return byteToIECNotation(bytes);
    }
    return null;
  }

  private static String byteToSINotation(long bytes) {
    if (-1000 < bytes && bytes < 1000) {
      return bytes + " B";
    }
    CharacterIterator ci = new StringCharacterIterator("kMGTPE");
    while (bytes <= -999_950 || bytes >= 999_950) {
      bytes /= 1000;
      ci.next();
    }
    return String.format("%.1f %cB", bytes / 1000.0, ci.current());
  }

  private static String byteToIECNotation(long bytes) {
    long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
    if (absB < 1024) {
      return bytes + " B";
    }
    long value = absB;
    CharacterIterator ci = new StringCharacterIterator("KMGTPE");
    for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
      value >>= 10;
      ci.next();
    }
    value *= Long.signum(bytes);
    return String.format("%.1f %ciB", value / 1024.0, ci.current());
  }

}
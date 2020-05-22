package Foundation;

/**
 * Version
 * 
 * @since 0.1.0
 */
public class Version {

  private int major;
  private int minor;
  private int patch;
  private int beta = Integer.MAX_VALUE;

  public Version(int major, int minor, int patch) {
    this.major = major;
    this.minor = minor;
    this.patch = patch;
  }

  public int compareTo(Version o) {
    if (this.major != o.major) {
      return Integer.compare(this.major, o.major);
    }
    if (this.minor != o.minor) {
      return Integer.compare(this.minor, o.minor);
    }
    if (this.patch != o.patch) {
      return Integer.compare(this.patch, o.patch);
    }
    if (this.beta != o.beta) {
      return Integer.compare(this.beta, o.beta);
    }
    return 0;
  }

  // TODO: implement parsing
  // public static Version parse(String version) {
  // // 1.1.1 - beta = MAX_VALUE
  // // 1.1.1beta - beta = 1
  // // 1.1.1beta2 - beta = 2
  // return new Version();
  // }

  @Override
  public String toString() {
    String version = major + "." + minor + "." + patch;
    return "Version " + version
        + (major > 0 ? "" : " (" + (minor == 1 ? "Pre-alpha" : minor == 2 ? "Alpha" : minor == 3 ? "Beta" : "") + ")");
    // (beta == Integer.MAX_VALUE ? "" : (beta == 1 ? "beta" : ("beta" + beta)));
  }

}
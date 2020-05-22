package Foundation;

public interface ApplicationProtocol {

  enum LaunchMode {
    normal, debug, development
  }

  public void didLaunch(String[] arguments, LaunchMode mode);

}

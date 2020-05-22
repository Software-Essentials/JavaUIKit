package Foundation;

import java.sql.Connection;
import java.sql.DriverManager;

@SuppressWarnings("unused")
public class Database {

  public static final Database main = new Database();

  private Database() {
    try {
      String url = "jdbc:mysql://db.se0.dev:3306/nerdygadgets";
      String username = "nerdygadgets";
      String password = "nerdygadgets";
      System.out.println("Connecting database...");
      Connection connection = DriverManager.getConnection(url, username, password);
      System.out.println("Connected to database!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
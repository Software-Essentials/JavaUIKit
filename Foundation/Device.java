package Foundation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Device {

  public class Processor {

    public int count() {
      return Runtime.getRuntime().availableProcessors();
    }
    
  }

  public class Memory {

    public long total() {
      return Runtime.getRuntime().maxMemory();
    }

    public String total(FD.Notation byteNotation) {
      return Unit.toByte(Runtime.getRuntime().maxMemory(), byteNotation);
    }
  
    public long free() {
      return Runtime.getRuntime().freeMemory();
    }
  
    public String free(FD.Notation byteNotation) {
      return Unit.toByte(Runtime.getRuntime().freeMemory(), byteNotation);
    }
  
    public long totalJVM() {
      return Runtime.getRuntime().totalMemory();
    }
  
    public String totalJVM(FD.Notation byteNotation) {
      return Unit.toByte(Runtime.getRuntime().totalMemory(), byteNotation);
    }
    
  }

  public class Storage {
    
    public Long total() {
      for (File root : File.listRoots()) {
        return root.getTotalSpace();
      }
      return (long) -1;
    }
    
    public String total(FD.Notation byteNotation) {
      for (File root : File.listRoots()) {
        return Unit.toByte(root.getTotalSpace(), byteNotation);
      }
      return "?";
    }

    public Long free() {
      for (File root : File.listRoots()) {
        return root.getUsableSpace();
      }
      return (long) -1;
    }

    public String free(FD.Notation byteNotation) {
      for (File root : File.listRoots()) {
        return Unit.toByte(root.getUsableSpace(), byteNotation);
      }
      return "?";
    }

  }

  public static final Device main = new Device();

  public final String os;
  public final String osVersion;
  public final String architecture;
  public final Processor processor;
  public final Memory memory;
  public final Storage storage;

  private Device() {
    os = System.getProperty("os.name");
    osVersion = System.getProperty("os.version");
    architecture = System.getProperty("os.arch");
    processor = new Processor();
    memory = new Memory();
    storage = new Storage();
  }

  public String execute(String ttyCommand) {
    try {
      Process proc = Runtime.getRuntime().exec(ttyCommand);
      BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
      String lines = "";
      String line = "";
      while ((line = reader.readLine()) != null) {
        lines += line + "\n";
      }
      proc.waitFor();
      return lines;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } catch (InterruptedException e) {
      e.printStackTrace();
      return null;
    }

  }

}
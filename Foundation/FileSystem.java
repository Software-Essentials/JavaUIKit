package Foundation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem {

    public static boolean exists(String path) {
        File f = new File(path);
        return f.exists();
    }

    public static boolean fileExists(String path) {
        File f = new File(path);
        return f.isFile();
    }

    public static boolean directoryExists(String path) {
        File f = new File(path);
        return f.isDirectory();
    }

    public static boolean deleteFile(String path) {
        try {
            return Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            return false;
        }
    }

}
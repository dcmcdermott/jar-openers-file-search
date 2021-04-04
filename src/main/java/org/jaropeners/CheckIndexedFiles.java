package org.jaropeners;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckIndexedFiles {
    public static void main(String[] args) {

        try {
            String userHome = System.getProperty("user.home");
            Path storageFile = Paths.get(userHome, "jarOpenersStorage.txt");

            if (Files.exists(storageFile)) {
                System.out.print("File does exist.\nFile was last modified on: " + (Files.getLastModifiedTime(storageFile)));
            }

            else
                System.out.println("File no longer exists.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
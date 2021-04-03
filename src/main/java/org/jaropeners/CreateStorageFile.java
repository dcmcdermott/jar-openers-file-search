package org.jaropeners;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateStorageFile {
    public static void main(String[] args) {

        try
        {
            String userHome = System.getProperty("user.home");
            Path storageFile =  Paths.get(userHome, "jarOpenersStorage.txt");

            if (Files.exists(storageFile)) {
                System.out.println("Storage file already exists");
            }
            else {
                File newStorageFile = new File(userHome, "jarOpenersStorage.txt");
                BufferedWriter out = new BufferedWriter(new FileWriter(newStorageFile));
                System.out.println("JarOpenersStorage.txt created successfully");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

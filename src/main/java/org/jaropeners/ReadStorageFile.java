package org.jaropeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReadStorageFile {
    public static void main(String[] args) throws FileNotFoundException {

        String userHome = System.getProperty("user.home");
        Scanner s = new Scanner(new File(String.valueOf(Paths.get(userHome, "jarOpenersStorage.txt"))));
        ArrayList<String> fileList = new ArrayList<>();

        // Read in each line of the .txt file and add it to the list
        while (s.hasNextLine()){
            fileList.add(s.nextLine());
        }
        System.out.println("Storage file scanned successfully");

        // print the list to the console
        for(String file : fileList) {
            System.out.println(file);
        }
        s.close();
    }
}

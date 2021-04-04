package org.jaropeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class API {

    // FILE LIST
    static ArrayList<String> fileList = new ArrayList<>();

    // CREATE STORAGE FILE IF IT DOESN'T ALREADY EXIST
    public static void createStorageFile() {
        try
        {
            // get user's home directory and append file name
            String userHome = System.getProperty("user.home");
            Path storageFile =  Paths.get(userHome, "jarOpenersStorage.txt");

            // if file already exists, do nothing
            if (Files.exists(storageFile)) {
                System.out.println("Storage file already exists");
            }
            // if file doesn't exist, create it
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

    // IF STORAGE FILE EXISTS, READ EACH LINE AND STORE THEM IN THE FILE LIST
    public static void readStorageFile() throws FileNotFoundException {

        String userHome = System.getProperty("user.home");
        Scanner s = new Scanner(new File(String.valueOf(Paths.get(userHome, "jarOpenersStorage.txt"))));


        // Read in each line of the .txt file and add it to the list
        while (s.hasNextLine()){
            fileList.add(s.nextLine());
        }
        System.out.println("Storage file scanned successfully");

        // print the list to the console for testing
        for(String file : fileList) {
            System.out.println(file);
        }
        s.close();
    }

    // ADD FILE METHOD


    // DELETE FILE METHOD


    // SEARCH FILE METHOD


}

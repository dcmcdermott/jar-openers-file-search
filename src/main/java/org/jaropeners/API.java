/* * * * * * * * * * * *
 * Devin McDermott     *
 * Amanda Camelio      *
 * Joaquin Rojas Chang *
 * Justin Meek         *
 * * * * * * * * * * * */

package org.jaropeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class API {

    // FILE LIST
    static List<String> fileList = new ArrayList<>();

    // CREATE STORAGE FILE IF IT DOESN'T ALREADY EXIST
    public static void createStorageFile() {
        try {
            // get user's home directory and append file name
            String userHome = System.getProperty("user.home");
            Path storageFile = Paths.get(userHome, "jarOpenersStorage.txt");

            // if file already exists, do nothing
            if (Files.exists(storageFile)) {
                System.out.println("✅ Storage file found");
            }
            // if file doesn't exist, create it
            else {
                File newStorageFile = new File(userHome, "jarOpenersStorage.txt");
                BufferedWriter out = new BufferedWriter(new FileWriter(newStorageFile));
                System.out.println("✅ Storage file created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // IF STORAGE FILE EXISTS, READ EACH LINE AND STORE THEM IN THE FILE LIST
    public static void readStorageFile() throws FileNotFoundException {

        String userHome = System.getProperty("user.home");
        Scanner s = new Scanner(new File(String.valueOf(Paths.get(userHome, "jarOpenersStorage.txt"))));

        // Read in each line of the .txt file and add it to the list
        while (s.hasNextLine()) {
            fileList.add(s.nextLine());
        }
        System.out.println("✅ Storage file scanned");
        System.out.println();
        System.out.println("💾 File content 💾");

        // print the list to the console for testing
        for (String file : fileList) {
            System.out.println(file);
        }
        System.out.println();

        s.close();
    }

    // UPDATE STORAGE FILE METHOD
    // * Clear the storage file and write the current fileList to it *
    //
public static void updateStorageFile(String filepath) {
}

    // ADD FILE METHOD
    // *Needs fix*  Needs to add to the fileList
    //              Should implement Update Storage File method
    public static void addFile(){
        try
        {
            String userHome = System.getProperty("user.home");
            Path storageFile = Paths.get(userHome, "jarOpenersStorage.txt");

            if (Files.notExists(storageFile)){
            System.out.print("File no longer exists.");
        }
            else{

                FileWriter newPhrase = new FileWriter( "jarOpenersStorage.txt",true);
                PrintWriter printWriter = new PrintWriter(newPhrase);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Add a word to the list");
                String message = scanner.nextLine();
                newPhrase.write(message + "\n");
                newPhrase.close();
                System.out.println("Successfully Added");
        }
        } catch (IOException e) {
            System.out.println("Unable to add.");
            e.printStackTrace();
        }
    }

    // DELETE FILE METHOD
    // *Needs fix*  Needs to add to the fileList
    //              Should implement Update Storage File method
    public static void deleteFile(String f) {
        File deleteFile = new File(f);
        if (deleteFile.delete()) {
            System.out.println("File was successfully deleted: " + deleteFile.getName());
        } else {
            System.out.println("File was NOT successfully deleted.");
        }
    }

    // LAST MODIFIED METHOD
    public static void checkIndexedFiles() {
        try {
            String userHome = System.getProperty("user.home");
            Path storageFile = Paths.get(userHome, "jarOpenersStorage.txt");

            //if file doesn't exist
            if (Files.notExists(storageFile)) {
                System.out.print("File not found");
            }
            // if file exists, prints modified date
            else {
                Files.exists(storageFile);
                System.out.println("Last modified: " + (Files.getLastModifiedTime(storageFile)));
            }
    } catch(Exception e) {
        e.printStackTrace();
    }
  }

}

package org.app;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        File myObj = new File("filename.jar");
        Path path = Paths.get(myObj.getPath());
        BasicFileAttributes attr;
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                attr = Files.readAttributes(path, BasicFileAttributes.class);
                // SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                SimpleDateFormat hoursSDF = new SimpleDateFormat("HH");
                int returnHours = Integer.parseInt(hoursSDF.format(attr.creationTime().toMillis()));
                System.out.println("Hours of creating file: " + returnHours);

                String filename = myObj.getName();
                if(filename.contains("xml"))
                    if(myObj.renameTo(new File("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\HOME\\filename.xml")))
                        System.out.println("File moved to HOME successfully");

                    if (returnHours % 2 == 0) {
                        if (myObj.renameTo(new File("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\DEV\\filename.jar"))) {
                            System.out.println("File moved DEV successfully");
                        } else {
                            System.out.println("Failed to move the file");
                        }
                    } else{

                        if( myObj.renameTo(new File("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\TEST\\filename.jar"))) {
                            System.out.println("File moved TEST successfully");
                        } else {
                            System.out.println("Failed to move the file");
                        }
                    }

            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("oops error! " + e.getMessage());
        }
    }
}
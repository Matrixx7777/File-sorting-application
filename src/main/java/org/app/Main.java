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
            boolean createFile = myObj.createNewFile();
            attr = Files.readAttributes(path, BasicFileAttributes.class);
            // SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
             SimpleDateFormat hoursSDF = new SimpleDateFormat("HH");
                int returnHours = Integer.parseInt(hoursSDF.format(attr.creationTime().toMillis()));
                String filename = myObj.getName();

                if(createFile && filename.contains(".xml")) {
                    if (myObj.renameTo(new File("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\HOME\\" + myObj)))
                        System.out.println("File moved to to the HOME successfully");
                    else if(myObj.exists())
                        System.out.println("The same file exists already in the HOME folder -- > " + myObj.delete());
                }
                else if (createFile && returnHours % 2 == 0 && filename.contains(".jar")) {
                    if (myObj.renameTo(new File("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\DEV\\" + myObj)))
                        System.out.println("File moved to the DEV successfully");
                    else if(myObj.exists())
                        System.out.println("The same file exists already in the DEV folder -- > " + myObj.delete());

                } else if(createFile && returnHours % 2 != 0 && filename.contains(".jar")) {
                    if(myObj.renameTo(new File("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\TEST\\" + myObj)))
                        System.out.println("File moved to the TEST successfully");
                    else if(myObj.exists())
                        System.out.println("The same file exists already in the TEST folder -- > " + myObj.delete());
                }
        } catch (IOException e) {
            System.out.println("oops error! " + e.getMessage());
        }
    }
}
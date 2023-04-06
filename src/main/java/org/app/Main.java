package org.app;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Formatter;

public class Main {
    public static void main(String[] args) {
        File file = new File("file4.jar");
        getCreatedFile(file);
    }

    public static void getCreatedFile(File file) {
        Path path = Paths.get(file.getPath());
        BasicFileAttributes attr;
        try {
            boolean createFile = file.createNewFile();
            attr = Files.readAttributes(path, BasicFileAttributes.class);
            // SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            SimpleDateFormat hoursSDF = new SimpleDateFormat("HH");
            int returnHours = Integer.parseInt(hoursSDF.format(attr.creationTime().toMillis()));
            String filename = file.getName();
            int fileExtension = filename.lastIndexOf('.');

            if (createFile && fileExtension > 0) {
                if (filename.contains(".xml")) {
                    Path pathHOME = Paths.get("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\HOME\\");
                    Path createFolderHOME = Files.createDirectories(pathHOME);
                    Path HOME = createFolderHOME.getName(7);
                    boolean bringFileToFolderHOME = file.renameTo(new File(createFolderHOME.toUri().getPath() + file));
                    if (bringFileToFolderHOME) {
                        System.out.println(filename + " moved to the " + HOME + " folder successfully --> " + bringFileToFolderHOME);
                        saveAllLines(file);
                    } else System.out.println("The same file exists already in the folder -- > " + file.delete());
                } else if (returnHours % 2 == 0 && filename.contains(".jar")) {
                    Path pathDEV = Paths.get("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\DEV\\");
                    Path createFolderDEV = Files.createDirectories(pathDEV);
                    Path DEV = createFolderDEV.getName(7);
                    boolean bringToFolderDEV = file.renameTo(new File(createFolderDEV.toUri().getPath() + file));
                    if (bringToFolderDEV) {
                        System.out.println(filename + " moved to the " + DEV + " folder successfully --> " + bringToFolderDEV);
                        saveAllLines(file);
                    } else System.out.println("The same file exists already in the folder -- > " + file.delete());
                } else if (returnHours % 2 != 0 && filename.contains(".jar")) {
                    Path pathTEST = Paths.get("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\TEST\\");
                    Path createFolderTEST = Files.createDirectories(pathTEST);
                    Path TEST = createFolderTEST.getName(7);
                    boolean bringToFolderDEV = file.renameTo(new File(createFolderTEST.toUri().getPath() + file));
                    if (bringToFolderDEV) {
                        System.out.println(filename + " moved to the " + TEST + " folder successfully --> " + bringToFolderDEV);
                        saveAllLines(file);
                    } else System.out.println("The same file exists already in the folder -- > " + file.delete());
                }
            } else System.out.println("Something is wrong with " + filename + " --> " + file.delete());
        } catch (IOException ex) {
            throw new RuntimeException("Ops Error !");
        }
    }

    private static void saveAllLines(File file) {
        File count_txt = new File("C:\\Users\\kocik\\OneDrive\\Dokumenty\\Programowanie\\Java\\Small project\\count.txt");
        try {
            FileWriter fileWriter = new FileWriter(count_txt, true);
            Formatter formatter = new Formatter(fileWriter);
            formatter.format("%s \r\n", file.getName());
            formatter.close();
            fileWriter.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
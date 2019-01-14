package com.epam.training;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectorySearch {

    public static void main(String[] args) throws IOException {
        StringBuilder path = new StringBuilder();
        for (String s : args) {
            path.append(s);
            path.append(File.separator);
        }
        Path p = Paths.get(path.toString());
        DirectoryOpener dr = new DirectoryOpener(p);
        dr.findFiles();
        DirectorySearch ds = new DirectorySearch();
        ds.print(dr.getFilesList());
    }


    List<Path> findEquals(Path p, List<Path> fileList) throws IOException {
        List<Path> eqList = new ArrayList<>();
        for (Path file: fileList) {
            if (!Files.isSameFile(file, p)) {
                if (Files.size(file) == Files.size(p) && Arrays.equals(Files.readAllBytes(file), Files.readAllBytes(p))) {
                    eqList.add(file);
                }
            }
        }
        return eqList;
    }

    void print(List<Path> filesList) throws IOException {
        for (Path file : filesList) {
            List<Path> temp = findEquals(file, filesList);
            if (!temp.isEmpty()) {
                System.out.println(file);
                System.out.println("------------------------------");
                for (Path f : temp) {
                    System.out.printf("\t %s \n", f);
                }
                System.out.println("Size = " + Files.size(file) + " байт");
                System.out.println();
            }
            //System.out.print(Files.size(file));
        }
    }
}

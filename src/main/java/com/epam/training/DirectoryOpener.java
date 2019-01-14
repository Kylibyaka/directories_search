package com.epam.training;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


public class DirectoryOpener {

    private Path path;
    private List<Path> filesList;

    public DirectoryOpener(String path) {
        this.path = Paths.get(path);
        filesList = new ArrayList<>();
    }
    public DirectoryOpener(Path path) {
        this.path = path;
        filesList = new ArrayList<>();
    }

    void findFiles() throws IOException {
        MyFileVisitor fv = new MyFileVisitor();
        Files.walkFileTree(path, fv);
    }

    public List<Path> getFilesList() {
        return filesList;
    }

    class MyFileVisitor implements FileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            filesList.add(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }
}

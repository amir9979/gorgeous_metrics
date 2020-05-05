package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import builder.ProgramBuilder;
import readers.LocalDirectoryReader;

public class MainMetrics {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException, IOException {
        String project_dir = args[0];
        String out_dir = args[1];

        LocalDirectoryReader reader = new LocalDirectoryReader();
        try {
            ProgramBuilder builder = new ProgramBuilder();
            reader.readMetrics(project_dir, out_dir, builder);
        } catch (IOException ex) {

        }

    }

}
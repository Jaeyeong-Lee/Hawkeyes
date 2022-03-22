package com.samsung.iomanager;

import java.io.*;
import java.util.*;

public class FileIOManager implements IOManager<String> {

    @Override
    public List<String> readInput(String fileName) {
        List<String> fileContents = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = br.readLine()) != null) {
                fileContents.add(str);
            }

        } catch (IOException e) {
            fileContents = new ArrayList<>();
        }
        return fileContents;
    }

    @Override
    public void writeOutput(String fileName, List<String> fileLines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (String line : fileLines) {
                if (line.length() == 0) {
                    continue;
                }
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

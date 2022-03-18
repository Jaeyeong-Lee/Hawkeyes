package com.samsung.main;

public class Main {

    public static void main(String[] args) {

        if (isValidFile(args[0]) || isValidFile(args[1])) {
            System.out.println("Invalid file");
        }

    }

    static boolean isValidFile(String arg) {
        if (arg == null) {
            System.out.println("Input file is null.");
            return false;
        }

        if (arg.length() == 0) {
            System.out.println("Input file length is zero.");
            return false;
        }

        return true;
    }
}

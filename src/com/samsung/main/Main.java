package com.samsung.main;

import com.samsung.employee.EmployeeManager;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        if (!isValidFile(args[0]) || !isValidFile(args[1])) {
            System.out.println("Invalid file");
            return ;
        }

        EmployeeManager manager = new EmployeeManager();
        manager.process(args[0], args[1]);
    }

    static boolean isValidFile(String arg) {
        if (arg == null) {
            System.out.println("Input file name is null.");
            return false;
        }

        if (arg.length() <= 3) {
            System.out.println("Input file name length is under 3.");
            return false;
        }

        if (!Arrays.asList("txt", "TXT").contains(arg.substring(arg.length()-3))) {
            System.out.println("Input file name is not in white list.");
            return false;
        }

        // TODO: Secure Coding 기반 directory check 계속 /
        return true;
    }
}

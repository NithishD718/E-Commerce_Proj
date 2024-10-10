package Util;

import java.util.Scanner;

public class GlobalScanner {

    private static final Scanner scanner = new Scanner(System.in);

    public static Scanner getInstance()
    {
        return scanner;
    }

    public static void close()
    {
        scanner.close();
    }
}

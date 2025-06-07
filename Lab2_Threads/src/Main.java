package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int dim = 10_000_000;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Threads: ");
        int threadNum = scanner.nextInt();
        ArrClass arrClass = new ArrClass(dim, threadNum);
        System.out.println(arrClass.partMin(0, dim));
        System.out.println(arrClass.threadMin());
        scanner.close();
    }
}
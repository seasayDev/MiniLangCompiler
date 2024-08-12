package inf5153.miniLang.javaGeneration;

import java.util.Scanner;

public class GenExemple2 {
    public static void main(String[] args) {        int a;
        int b;
        int c;

        a = 15;
        b = a + 2;
        if (a == 2) {
        c = 1;
        } else {
        c = (a + b) / 2;
        }
        System.out.println(c);
    }
}


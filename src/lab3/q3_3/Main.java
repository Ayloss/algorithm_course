package lab3.q3_3;

import java.util.Scanner;

public class Main {

    public static void mainElem(int[] elems,int n) {
        int currentNumCount = 1;
        int currentNum = elems[0];

        for(int i=1;i<n;i++) {
            if(currentNumCount == 0) {
                currentNum = elems[i];
            }

            if(currentNum == elems[i]) {
                currentNumCount++;
            } else {
                currentNumCount--;
            }
        }

        if(currentNumCount == 0) {
            System.out.println("null");
        } else {
            int count = 0;
            for(int i = 0;i < n;i++) {
                if(currentNum == elems[i]) {
                    count++;
                }
            }

            System.out.println(count > n/2 ? currentNum:"null");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] elems = new int[n];

        for(int i=0;i<n;i++) {
            elems[i] = scanner.nextInt();
        }

        mainElem(elems,n);
    }
}
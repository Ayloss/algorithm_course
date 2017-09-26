package lab1.q1_1.method1;

import java.util.Scanner;

/**
 * 最笨的方法
 * Created by status200 on 2017/9/26.
 */
public class Main {

    private static int[] numberAppearanceTimes;

    public static void init() {
        numberAppearanceTimes = new int[10];
    }

    public static void countNumber(int number) {

        while(number > 0) {
            numberAppearanceTimes[number%10]++;
            number/=10;
        }
    }

    public static void print() {
        for(int i =0;i<10;i++) {
            System.out.println(numberAppearanceTimes[i]);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int pagesTotal = scanner.nextInt();

        long t1 = System.currentTimeMillis();

        init();

        while(pagesTotal > 0) {
            countNumber(pagesTotal);
            pagesTotal--;
        }

        print();

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }
}

package lab2.q2_3;

import java.util.Scanner;

/**
 * 直接递归。
 *
 * Created by status200 on 2017/10/11.
 */
public class Main {

    public static int numberAmount = 1;

    public static void main(String[] args) {
        int n = 0;

        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        halfNumberSet(n);

        System.out.println(numberAmount);
    }

    // 在左边添加一个数
    public static void halfNumberSet(int lastAddedNumber) {

        for(int i=1;i<=lastAddedNumber/2;i++) {
            numberAmount++;
            halfNumberSet(i);
        }
    }
}

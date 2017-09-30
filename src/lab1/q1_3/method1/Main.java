package lab1.q1_3.method1;

import java.util.Scanner;

import static java.lang.Math.*;
/**
 * 暴力解法
 *
 * Created by status200 on 2017/9/29.
 */
public class Main {

    public static int div(int num) {

        if(num == 1) {
            return 1;
        }

        int divisorNum = 0;
        int root = (int) sqrt(num);
        for(int i = 2;i <= root;i++) {
            if(num % i == 0) {
                divisorNum+=2;
            }
        }

        if (root * root == num) {
            return divisorNum + 1;
        } else {
            return divisorNum + 2;
        }
    }

    public static int maxDiv(int a, int b) {
        int maxNum = 2;

        int num = a;
        for(int i = a;i<=b;i++) {
            int divNum = div(i);
            if(maxNum < divNum) {
                maxNum = divNum;
                num = i;
            }
        }


        return maxNum;
    }

    public static void main(String[] args) {

        int begin = 0;
        int end = 0;

        Scanner scanner = new Scanner(System.in);

        begin = scanner.nextInt();
        end = scanner.nextInt();

        System.out.println(maxDiv(begin,end));

    }
}

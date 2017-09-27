package lab1.q1_1.method2;

import java.util.Scanner;

/**
 * Created by status200 on 2017/9/27.
 */
public class Main {

    private static int[] numberAppearanceTimes;

    public static void init() {
        numberAppearanceTimes = new int[10];
        for (int i = 0; i < 10; i++) {
            numberAppearanceTimes[i] = 0;
        }
    }


    public static void count(int num, int beginLevel, int currentLevel) {

        // 只有1位的情况
        if (currentLevel == 1) {
            for (int i = 0; i <= num; i++) {
                numberAppearanceTimes[i]++;
            }
            return;
        }

        int firstNumber = num / currentLevel;
        int numExceptFirstPart = num % currentLevel;
        if (num < currentLevel) {
            numberAppearanceTimes[0] += (num + 1);
            count(num, beginLevel, currentLevel / 10);

        } else {
            numberAppearanceTimes[firstNumber] += (numExceptFirstPart + 1);
            count(numExceptFirstPart, beginLevel, currentLevel / 10);

            int beginNum = beginLevel > currentLevel ? 0 : 1;
            for (int i = beginNum; i < firstNumber; i++) {
                numberAppearanceTimes[i] += currentLevel;
                if(i != 0) {
                    count(currentLevel - 1, beginLevel, currentLevel / 10);
                }
            }

            count(currentLevel - 1, beginLevel / 10, currentLevel / 10);
        }
    }

    public static void print() {
        for (int i = 0; i < 10; i++) {
            System.out.println(numberAppearanceTimes[i]);
        }
    }

    public static void testPrint() {
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("[%d] : %d", i, numberAppearanceTimes[i]));
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long beginTime = System.currentTimeMillis();

        int pagesTotal = scanner.nextInt();

        init();

        int level = 1;

        int t = pagesTotal;
        while (t >= 10) {
            level *= 10;
            t /= 10;
        }

        count(pagesTotal,level, level);
        numberAppearanceTimes[0]--;

        long endTime = System.currentTimeMillis();

        System.out.println(String.format("Calculate complete, use %d ms",endTime - beginTime));
//        print();
        testPrint();
    }
}

package lab1.q1_1.method2;

import java.util.Scanner;

/**
 * 思路：最高位出现次数容易看出规律，去掉最高位后所得数字+1即为最高位出现的次数。比如2999中,最高位2的出现次数为999 + 1 = 1000次
 * 比如对于三位数abc,可以先计算
 * abc --- a00。
 * 然后计算(a-1)99 --- (a-1)00,(a-2)99 -- (a-2)00 ,,,,,,
 * 可以发现这几个的计算方法都是一致的，先算最高位，再算去掉最高位的情况。
 *
 * Created by status200 on 2017/9/27.
 */
public class Main {

    private static int[] numberAppearanceTimes;

    // 初始化数据
    public static void init() {
        numberAppearanceTimes = new int[10];
        for (int i = 0; i < 10; i++) {
            numberAppearanceTimes[i] = 0;
        }
    }


    /**
     * 根据开始时的位数、当前的位数进行计算
     * @param num 数字
     * @param beginLevel 代表开始时的位数,为10的倍数
     * @param currentLevel 代表当前的位数,为10的倍数
     */
    public static void count(int num, int beginLevel, int currentLevel) {

        // 只有1位的情况
        if (currentLevel == 1) {
            for (int i = 0; i <= num; i++) {
                numberAppearanceTimes[i]++;
            }
            return;
        }

        // 首位数字
        int firstNumber = num / currentLevel;
        // 去除首位数字的部分
        int numExceptFirstPart = num % currentLevel;

        // 出现10023这样的情况
        // 去除首位后,结果为0023
        if (num < currentLevel) {
            numberAppearanceTimes[0] += (num + 1);
            count(num, beginLevel, currentLevel / 10);

        }
        // 计算首位数字,并依次减一进行计算
        else {
            // 首位数字
            numberAppearanceTimes[firstNumber] += (numExceptFirstPart + 1);
            count(numExceptFirstPart, beginLevel, currentLevel / 10);

            // 决定开始循环的数
            // 对于比如1237这样的情况,计算完最高位1,计算237时,应当把0的次数考虑进去。比如页码为1037
            int beginNum = beginLevel > currentLevel ? 0 : 1;
            for (int i = beginNum; i < firstNumber; i++) {
                numberAppearanceTimes[i] += currentLevel;

                // 计算(a-1)99-(a-1)00
                if(i != 0) {
                    count(currentLevel - 1, beginLevel, currentLevel / 10);
                }
            }

            // 以当前的位数-1作为开始的位数,计算减去一位的结果
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

package lab4.q3_6;

import java.util.Scanner;

/**
 * 本质上还是矩阵连乘问题
 *
 * Created by status200 on 2017/11/8.
 */
public class Main {

    public static int[][] minMoney;

    public static int rentShip(int n) {

        // 距离+
        for (int distance = 2; distance <= n - 1; distance++) {

            // 开始站
            for (int beginStation = 1; beginStation <= n - distance; beginStation++) {

                int tempMin;

                // 分割位置
                for (int divPos = beginStation + 1; divPos < beginStation + distance; divPos++) {

                    tempMin = minMoney[beginStation][divPos] + minMoney[divPos][beginStation + distance];

                    if(tempMin < minMoney[beginStation][beginStation + distance]) {
                        minMoney[beginStation][beginStation + distance] = tempMin;
                    }
                }
            }
        }

        return minMoney[1][n];
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        minMoney = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            minMoney[i][i] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                minMoney[i][j + 1] = scanner.nextInt();
//                System.out.println(minMoney[i][j+1]);
            }
        }


        System.out.println(rentShip(n));
    }
}

package lab3.q3_1;

import java.util.Scanner;

/**
 * 方法类似于矩阵连乘。但需要注意的是，该问题中说是“圆形操场”，石头连成一个环。
 * 因此必须对以每个元素开头的情况进行计算，再得出最大值。
 *
 * Created by status200 on 2017/10/24.
 */
public class Main {

    // 存储1到k的最小分数
    public static int[][] minScore;

    // 存储1到k的最大分数
    public static int[][] maxScore;

    // 相邻两堆石子的分数和
    public static int[][] sumOfNeighbor;

    public static int parseMin(int[] stones, int n) {
        minScore = new int[n + 1][n + 1];

        // 长度为1时，得分就是0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                minScore[i][j] = 0;
            }
        }

        // 长度从2开始，依次进行划分
        for (int len = 2; len <= n; len++) {
            int endPos = n - len + 1;

            // 对每个长度的划分，再依次进行划分，求得最小分数
            for (int beginPos = 1; beginPos <= endPos; beginPos++) {
                int k = beginPos + len - 1;

                if (sumOfNeighbor[beginPos][k] == 0) {
                    sumOfNeighbor[beginPos][k] = sumOfNeighbor[beginPos][k - 1] + stones[k - 1];
                }

                minScore[beginPos][k] = sumOfNeighbor[beginPos][k] + minScore[beginPos][beginPos] + minScore[beginPos + 1][k];

                // 从中间某个地方划分，存储最小分数
                for (int divPos = beginPos + 1; divPos < k; divPos++) {
                    if (sumOfNeighbor[beginPos][divPos] == 0) {
                        sumOfNeighbor[beginPos][divPos] = sumOfNeighbor[beginPos][divPos - 1] + stones[divPos - 1];
                    }

                    int tempScore = sumOfNeighbor[beginPos][k] + minScore[beginPos][divPos] + minScore[divPos + 1][k];

                    if (minScore[beginPos][k] > tempScore) {
                        minScore[beginPos][k] = tempScore;
                    }
                }
            }
        }

        return minScore[1][n];
    }

    public static void printMin() {
        for (int[] i : minScore) {
            for (int j : i) {
                System.out.printf("%8d ", j);
            }
            System.out.println();
        }

    }

    public static void initSum(int[] stones, int n) {
        sumOfNeighbor = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sumOfNeighbor[i][j] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            sumOfNeighbor[i + 1][i + 1] = stones[i];
        }
    }

    public static int parseMax(int[] stones,int n) {
        maxScore = new int[n + 1][n + 1];

        // 长度为1时，得分就是0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                maxScore[i][j] = 0;
            }
        }

        // 长度从2开始，依次进行划分
        for (int len = 2; len <= n; len++) {
            int endPos = n - len + 1;

            // 对每个长度的划分，再依次进行划分，求得最小分数
            for (int beginPos = 1; beginPos <= endPos; beginPos++) {
                int k = beginPos + len - 1;

                if (sumOfNeighbor[beginPos][k] == 0) {
                    sumOfNeighbor[beginPos][k] = sumOfNeighbor[beginPos][k - 1] + stones[k - 1];
                }

                maxScore[beginPos][k] = sumOfNeighbor[beginPos][k] + maxScore[beginPos][beginPos] + maxScore[beginPos + 1][k];

                // 从中间某个地方划分，存储最大分数
                for (int divPos = beginPos + 1; divPos < k; divPos++) {
                    if (sumOfNeighbor[beginPos][divPos] == 0) {
                        sumOfNeighbor[beginPos][divPos] = sumOfNeighbor[beginPos][divPos - 1] + stones[divPos - 1];
                    }

                    int tempScore = sumOfNeighbor[beginPos][k] + maxScore[beginPos][divPos] + maxScore[divPos + 1][k];

                    if (maxScore[beginPos][k] < tempScore) {
                        maxScore[beginPos][k] = tempScore;
                    }
                }
            }
        }

        return maxScore[1][n];
    }

    public static void printMax() {
        for (int[] i : maxScore) {
            for (int j : i) {
                System.out.printf("%8d ", j);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] stones = new int[n];

        for (int i = 0; i < n; i++) {
            stones[i] = scanner.nextInt();
        }

        initSum(stones, n);

        int min = parseMin(stones, n);
        int max = parseMax(stones, n);
        // 改变为以k开头，长度为n
        for (int beginPos = 2; beginPos <= n; beginPos++) {

            // 交换数组，改变其开头
            int firstElem = stones[0];
            for (int i = 0; i < n - 1; i++) {
                stones[i] = stones[i + 1];
            }
            stones[n - 1] = firstElem;

            initSum(stones, n);
            int tempMin = parseMin(stones, n);
            int tempMax = parseMax(stones, n);

            if (tempMin < min) {
                min = tempMin;
            }

            if(tempMax > max) {
                max = tempMax;
            }


        }

        System.out.println(min);
        System.out.println(max);
    }
}

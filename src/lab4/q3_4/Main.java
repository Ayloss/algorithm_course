package lab4.q3_4;

import java.util.Scanner;

/**
 * 和阶段性的最短路径问题类似。
 *
 * Created by status200 on 2017/11/8.
 */
public class Main {

    // 原三角形
    public static int[][] tri;

    // 存储到达每一个节点的最大值
    public static int[][] maxVal;

    public static int numberTriangle(int n) {

        maxVal = new int[n + 1][n + 1];

        maxVal[1][1] = tri[1][1];

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {

                // 三角形两侧
                if (j == 1) {
                    maxVal[i][j] = maxVal[i - 1][j] + tri[i][j];
                } else if (j == i) {
                    maxVal[i][j] = maxVal[i - 1][j - 1] + tri[i][j];
                } else {
                    // 中间的每个节点，都有两条路径会达到它
                    // 比较这两条路径的最大值
                    // 即为
                    int val1 = maxVal[i - 1][j - 1] + tri[i][j];
                    int val2 = maxVal[i - 1][j] + tri[i][j];

                    maxVal[i][j] = val1 > val2 ? val1 : val2;

                }
                System.out.print(maxVal[i][j] + " ");
            }
            System.out.println();
        }

        int max = maxVal[n][1];

        for (int i = 2; i <= n; i++) {

            if (max < maxVal[n][i]) {
                max = maxVal[n][i];
            }
        }

        return max;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        tri = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                tri[i][j] = scanner.nextInt();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(tri[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(numberTriangle(n));
    }
}

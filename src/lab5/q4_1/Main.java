package lab5.q4_1;

import java.util.Scanner;

/**
 * 最简单的思路。仿照涂色问题。
 * 将活动时间的输入转换为图，再依次对每个节点涂色。
 * 输入样例很大时GG。
 *
 * Created by status200 on 2017/11/22.
 */
public class Main {

    // 无向图，代表每个活动和其他活动的相容性
    // 0代表连通，即相容
    // 1代表不连通，即不相容
    public static int G[][];

    // 每个节点使用的颜色
    public static int C[];

    // 已经使用过的颜色
    public static int colorStack[];

    // 使用的颜色种数
    public static int colorNum;

    // 输入转化为图
    public static void initGraph(int startTime[], int endTime[], int n) {
        G = new int[n][n];
        colorStack = new int[1000];
        C = new int[n];

        for (int i = 0; i < n; i++) {
            // 颜色设为0,0代表未访问
            C[i] = 0;

            int start = startTime[i];
            int end = endTime[i];

            for (int j = 0; j < n; j++) {

                if(i == j) {
                    G[i][j] = 0;
                } else {
                    // 开始时间或者截止时间处在当前活动的时间范围内
                    if((start >= startTime[j] && start <= endTime[j])
                            || (end >= startTime[j] && end <= endTime[j])) {
                        G[i][j] = G[j][i] = 1;
                    } else {
                        G[i][j] = G[j][i] = 0;
                    }
                }
            }
        }
    }

    // 图是否联通
    public static boolean isConnected(int i,int j) {
        return G[i][j] == 1;
    }

    // 点是否已经被访问
    public static boolean isVisited(int i) {
        return C[i] > 0;
    }

    // 标识某种颜色不可用
    // colorUsable表示每种颜色是否可用,color为要划去的颜色
    public static void markColorUnusable(boolean[] colorUsable,int color) {

        for(int i =0;i<colorNum;i++) {
            if(colorStack[i] == color) {
                colorUsable[i] = false;
                break;
            }
        }
    }

    // 获取当前可用的第一种颜色
    public static int getFirstAvailableColor(boolean[] colorUsable) {
        for(int i=0;i<colorNum;i++) {
            if(colorUsable[i]) {
                return colorStack[i];
            }
        }

        return 0;
    }

    public static int mColor(int n) {

        // 先给第一个节点涂色
        C[0] = 1;
        colorStack[0] = 1;
        colorNum = 1;

        for (int i = 1; i < n; i++) {

            // 表示每种颜色是否可用
            boolean[] colorUsable = new boolean[colorNum];

            for(int k = 0;k<colorNum;k++) {
                colorUsable[k] = true;
            }

            for (int j = 0; j < n; j++) {
                if (isConnected(i, j) && isVisited(j)) {
                    int c = C[j];
                    markColorUnusable(colorUsable,c);
                }
            }

            int firstColor = getFirstAvailableColor(colorUsable);

            if (firstColor <= 0) {
                colorNum++;
                colorStack[colorNum - 1] = colorNum;
                C[i] = colorNum;
            } else {
                C[i] = firstColor;
            }
        }

        return colorNum;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] startTime = new int[n];
        int[] endTime = new int[n];

        for (int i = 0; i < n; i++) {
            startTime[i] = scanner.nextInt();
            endTime[i] = scanner.nextInt();
        }

        initGraph(startTime, endTime, n);
        System.out.println(mColor(n));
    }
}

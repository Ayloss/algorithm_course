package lab5.q4_1;

import java.util.Scanner;

/**
 * Created by status200 on 2017/11/22.
 */
public class Main2 {

    // 每个节点使用的颜色
    public static int C[];

    // 已经使用过的颜色
    public static int colorStack[];

    // 使用的颜色种数
    public static int colorNum;

    // 活动开始时间
    public static int startTime[];

    // 活动结束时间
    public static int endTime[];

    public static boolean isConnected(int i,int j) {
        int start = startTime[i];
        int end = endTime[i];

        return (start >= startTime[j] && start <= endTime[j])
                || (end >= startTime[j] && end <= endTime[j]);
    }

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

        startTime = new int[n];
        endTime = new int[n];

        for (int i = 0; i < n; i++) {
            startTime[i] = scanner.nextInt();
            endTime[i] = scanner.nextInt();
        }

        System.out.println(mColor(n));
    }
}

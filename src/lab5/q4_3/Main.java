package lab5.q4_3;

import java.util.Scanner;

/**
 * 贪心选择方法。把读取时间最小的放前边。
 *
 * Created by status200 on 2017/11/23.
 */
public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double minTime = 0;
        int n = scanner.nextInt();
        int[] L = new int[n];
        int[] A = new int[n];
        int aTotal = 0;
        double[] S = new double[n];

        for (int i = 0; i < n; i++) {
            L[i] = scanner.nextInt();
            A[i] = scanner.nextInt();
            aTotal+=A[i];
        }

        // 计算磁带上仅放程序Li时，Li的读取时间
        for (int i = 0; i < n; i++) {
            S[i] = L[i] * (A[i] + 0.0) / aTotal;
        }

        // 排序,相当于贪心选择
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if(S[i] > S[j]) {
                    double temp = S[i];
                    S[i] = S[j];
                    S[j] = temp;
                }
            }

            minTime += (n-i) * S[i];
        }

        System.out.printf("%.1f",minTime);
    }
}

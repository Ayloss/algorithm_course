package lab1.q1_2;

import java.util.Scanner;

/**
 * <p>思路：实际上是个排列组合问题。</p>
 * <p>以eil为例。需要先计算出1位和2位时共有多少种情况，实际上是1位字母、2位字母的组合情况。
 * 因此有C(26,1)+C(26,2)种。</p>
 * <p>然后考虑最高位。e之前有a,b,c,d，
 * <ul>
 * <li>对于a，后两位不能取a，因此有C(25,2)种组合。</li>
 * <li>对于b,后两位不能取a和b,因此有C(24,2)种组合。</li>
 * </ul>
 * 以此类推。
 * </p>
 * <p>接着考虑次高位。e和i之间有f，g，h。
 * <ul>
 * <li>对于f,后一位不能取a,b,c,d,e,f,因此有C(20,1)种组合。</li>
 * <li>对于g,后一位不能取a,b,c,d,e,f,g,因此有C(19,1)种组合。</li>
 * </ul>
 * 以此类推。
 * </p>
 * <p>最后考虑最后一位,加上i到l之间的情况即可。</p>
 * <br>
 * Created by status200 on 2017/9/29.
 */
public class Main {

    public static int C(int n, int a) {
        int top = 1;
        int bottom = 1;

        if (a == 0) {
            return 1;
        }

        for (int i = n; i > n - a; i--) {
            top *= i;
        }

        for (int i = a; i >= 1; i--) {
            bottom *= i;
        }

        return top / bottom;
    }

    public static int calOrder(String str) {

        int length = str.length();
        int sum = 0;

        // 只有一位的情况
        if (length == 1) {
            return str.charAt(0) - 'a' + 1;
        }

        // 加上当前位数之前的所有情况
        for (int i = 0; i < length - 1; i++) {
            sum += C(26, i + 1);
        }

        // 最高位开始考虑到倒二位
        for (int i = 0; i < length - 1; i++) {
            int end = 0;

            if (i == 0) {
                end = 'a';
            } else {
                end = str.charAt(i - 1) + 1;
            }
            for (int j = str.charAt(i) - 1; j >= end; j--) {
                sum += C(26 - (j - 'a' + 1), length - (i + 1));
            }
        }

        // 最低位
        sum += str.charAt(length - 1) - str.charAt(length - 2);

        return sum;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();

        String[] strs = new String[num];

        for (int i = 0; i < num; i++) {
            strs[i] = scanner.next();
        }

        for (String str : strs) {
            System.out.println(calOrder(str));
        }
    }
}

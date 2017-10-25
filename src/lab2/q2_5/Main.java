package lab2.q2_5;

import java.util.Scanner;

/**
 * 先考虑全排列的情况，利用首位数依次与后边的数进行交换，缩减位数，重复执行此过程，就可以输出所有全排列情况。
 * 随后，考虑存在重复数的情况。实际上是根据全排列，考虑如何删除重复的情况。
 *
 * 详细解释参考 http://blog.csdn.net/caigen1988/article/details/7760177
 *
 * Created by status200 on 2017/10/11.
 */
public class Main {

    public static int number = 0;

    public static void swap(char[] arr, int i,int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean isSwap(char[] arr, int begin, int end) {
        for(int i = begin;i < end;i++) {
            if (arr[i] == arr[end]) {
                return false;
            }
        }

        return true;
    }

    public static void allRange(char[] arr, int k, int m) {

        // 递归到最后一位，输出
        if (k == m) {
            number++;
            System.out.println(String.valueOf(arr));
            return;
        }

        for(int i = k;i<=m;i++) {
            if (isSwap(arr, k,i)) {
                // 第k个数依次与后边的数交换
                swap(arr, k, i);
                allRange(arr, k + 1, m);
                // 交换回来，供回溯时使用
                swap(arr, k, i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String s = scanner.next();

        allRange(s.toCharArray(), 0, s.length() - 1);

        System.out.println(number);
    }
}

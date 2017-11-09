package lab3.q3_2;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * 线性时间选择。
 *
 * Created by status200 on 2017/10/25.
 */
public class Main {

    public static Random random = new Random(new Date().getTime());

    public static int getRandomInteger(int bound) {
        return random.nextInt(bound);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 随机划分，仿照快速排序。
    // p为数组开头位置,r为数组结束位置
    // 返回划分基准所在的位置
    public static int randomizedPartition(int[] elems, int p, int r) {

        // 随机选一个元素作为划分基准
        int pos = p + getRandomInteger(r - p + 1);
        int divElem = elems[pos];

        // 将该元素与首元素交换
        swap(elems,pos,p);

        while (p < r) {
            while (p < r && elems[r] >= divElem) {
                r--;
            }

            elems[p] = elems[r];

            while (p < r && elems[p] <= divElem) {
                p++;
            }

            elems[r] = elems[p];
        }

        elems[p] = divElem;

        return r;
    }

    // p为开头位置,r为结束位置,选择第k小的元素(k>0)
    public static int randomizedSelect(int[] elems, int p, int r, int k) {
        if (p == r) return elems[p];
        // 划分
        int i = randomizedPartition(elems, p, r);
        // 计算基准的位置处于数组的第几位
        int j = i - p + 1;
        if (k <= j) return randomizedSelect(elems, p, i, k);
        else return randomizedSelect(elems, i + 1, r, k - j);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] elems = new int[n];

        for(int i=0;i<n;i++) {
            elems[i] = scanner.nextInt();
        }

        System.out.println(randomizedSelect(elems, 0, n - 1, 1));
    }
}

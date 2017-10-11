package lab2.q2_1;

import java.util.Scanner;

/**
 * 最笨的办法。遇到一个新的数就加入列表，遇到已经出现的数就增加其次数。
 * 改进方案可以实现一个哈希表，利用哈希表来检查数据。
 *
 * Created by status200 on 2017/10/11.
 */
public class Main {

    public static int n;

    public static int findValueIndex(int[] arr, int length, int val) {
        for(int i=0;i<length;i++) {
            if (arr[i] == val) {
                return i;
            }
        }

        return -1;
    }

    public static void findMode(int[] numbers,int length) {
        // 记录每个数出现的次数
        int[] times = new int[length];

        // 记录每个序号对应的数
        int[] values = new int[length];

        int nValues = 0;

        // 众数及其重数
        int mode = 0;
        int modeTimes = 0;

        for(int i = 0;i< length;i++) {
            times[i] = 0;
            values[i] = 0;
        }

        // 遍历数表
        for(int i=0;i < length;i++) {
            // 在已经扫描过的数表中寻找该数
            int index = findValueIndex(values, nValues, numbers[i]);
            if(index >= 0) {
                times[index]++;

                if(times[index] > modeTimes) {
                    mode = numbers[i];
                    modeTimes = times[index];
                }
            } else {
                values[nValues] = numbers[i];
                times[nValues]++;
                
                if(times[nValues] > modeTimes ) {
                    mode = numbers[i];
                    modeTimes = times[nValues];
                }
            }

            nValues++;
        }


        System.out.println(mode + " " + modeTimes);
    }

    public static void main(String[] args) {

        int n;
        int m;

        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();

        for(int i = 0;i < n;i++) {
            int[] numbers = new int[m];

            for(int j = 0;j < m;j++) {
                numbers[j] = scanner.nextInt();
            }

            findMode(numbers,m);
        }
    }
}

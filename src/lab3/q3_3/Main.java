package lab3.q3_3;

import java.util.Scanner;

/**
 * 采用抵消法。
 * 先拿起第一个元素，然后往后看。如果遇到相同的元素，将其拿起。
 * 如果遇到不相同的元素，丢掉一个已经拿起的元素和这个不相同的元素。
 * 如果元素是主元素，那么即使执行丢弃操作，这个元素也依旧是主元素。
 * 遍历到最后结束时，再遍历一遍数组，计算最后一次拿起的元素的次数。大于n/2即为主元素。
 *
 * Created by status200 on 2017/10/25.
 */
public class Main {

    public static void mainElem(int[] elems,int n) {

        // 拿起第一个元素
        int currentNumCount = 1;
        int currentNum = elems[0];

        for(int i=1;i<n;i++) {
            if(currentNumCount == 0) {
                currentNum = elems[i];
            }

            if(currentNum == elems[i]) {
                currentNumCount++;
            } else {
                currentNumCount--;
            }
        }

        int count = 0;
        for(int i = 0;i < n;i++) {
            if(currentNum == elems[i]) {
                count++;
            }
        }

        System.out.println(count > n/2 ? currentNum:"null");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] elems = new int[n];

        for(int i=0;i<n;i++) {
            elems[i] = scanner.nextInt();
        }

        mainElem(elems,n);
    }
}
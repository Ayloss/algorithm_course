package lab4.q3_5;

import java.util.Scanner;

/**
 * 矩阵连乘问题的变体。
 * 利用三维数组记录等于每个值的方式数。
 *
 * Created by status200 on 2017/11/9.
 */
public class Main {


    public static char numberArray[];

    // A[i][j][k] 表示 i到j加括号，其计算结果为k的方式数
    public static int A[][][];

    public static int val(char c) {
        return c - 'a';
    }

    public static int multiplicationTable(int n) {

        A = new int[n][n][3];

        // 包含一个字符的位置设1
        for(int i = 0;i < n;i++) {
            A[i][i][val(numberArray[i])] = 1;
        }

//        for(int i =0;i<n;i++) {
//            for(int j =0;j<n;j++) {
//                for(int k=0;k<3;k++) {
//                    System.out.print(A[i][j][k] + " ");
//                }
//                System.out.print("|");
//            }
//            System.out.println();
//        }

        // 括号包含的字符长度 - 1
        for(int bracketWidth = 1;bracketWidth < n;bracketWidth++) {

            // 开始位置
            for(int beginPos = 0;beginPos < n - bracketWidth;beginPos++) {

                int i = beginPos;
                int j = beginPos + bracketWidth;

                // 分割的位置
                for(int divPos = beginPos;divPos < beginPos + bracketWidth;divPos++) {

                    A[i][j][val('a')] +=
                            // ca=a
                            A[i][divPos][val('c')] * A[divPos+1][j][val('a')]
                            // ac=a
                            + A[i][divPos][val('a')] * A[divPos+1][j][val('c')]
                            // bc=a
                            + A[i][divPos][val('b')] * A[divPos+1][j][val('c')];

                    A[i][j][val('b')] +=
                            // aa=b
                            A[i][divPos][val('a')] * A[divPos+1][j][val('a')]
                            // ab=b
                            + A[i][divPos][val('a')] * A[divPos+1][j][val('b')]
                            // bb=b
                            + A[i][divPos][val('b')] * A[divPos+1][j][val('b')];

                    A[i][j][val('c')] +=
                            // ba=c
                            A[i][divPos][val('b')] * A[divPos+1][j][val('a')]
                            // cb=c
                            + A[i][divPos][val('c')] * A[divPos+1][j][val('b')]
                            // cc=c
                            + A[i][divPos][val('c')] * A[divPos+1][j][val('c')];
                }
            }
        }


//        for(int i =0;i<n;i++) {
//            for(int j =0;j<n;j++) {
//                for(int k=0;k<3;k++) {
//                    System.out.print(A[i][j][k] + " ");
//                }
//                System.out.print("|");
//            }
//            System.out.println();
//        }

        return A[0][n -1][val('a')];
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String number = scanner.next();

        numberArray = number.toCharArray();

        System.out.println(multiplicationTable(numberArray.length));
    }
}

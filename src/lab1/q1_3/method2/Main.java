package lab1.q1_3.method2;

/**
 * Created by status200 on 2017/9/30.
 */
public class Main {

    // 需要的素数表中最大的数
    public static int maxPrime = 65536;

    // 素数表
    public static long[] primes;

    // 素数个数
    public static int primeNum = 0;

    // 埃拉托斯特尼筛法生成素数表。
    // 先假定所有数为素数。
    // 然后删去1。
    // 接着以遇到的第一个素数为倍数基准，删去所有其倍数.重复此步，直到到范围。
    public static void genPrimesList() {

        primes = new long[maxPrime];

        // 辅助判断是否为素数的数组,下标代表具体的数
        boolean[] isPrimes = new boolean[maxPrime + 1];

        // 初始时假定所有数都是素数
        for(int i=2;i<=maxPrime;i++) {
            isPrimes[i] = true;
        }

        // 从2开始,将辅助数组里的素数的倍数删去
        for(int i=2;i<=maxPrime;i++) {
            if (isPrimes[i]) {
                int j = i + i;
                while (j <= maxPrime) {
                    isPrimes[j] = false;
                    j+=i;
                }
            }
        }

        // 从辅助数组里拿出所有素数
        for(int i=2;i<=maxPrime;i++) {
            if (isPrimes[i]) {
                primes[primeNum] = i;
                primeNum++;
            }
        }
    }

    public static void iterate(int curPrime, int curVal, int curDivsorNum) {


    }

    public static void main(String[] args) {
        genPrimesList();
    }
}

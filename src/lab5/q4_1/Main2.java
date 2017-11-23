package lab5.q4_1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 巧妙的思路。
 * 不考虑究竟每个活动安排哪个会场，只考虑当前需不需要增加新的会场。
 * 对所有时间（开始或者结束）从小到大排序。
 * 扫描所有时间，当时间为开始时间，则增加一个会场。当时间为结束时间，则减少一个会场。
 * 记录这个过程中的最大会场数。
 *
 * Created by status200 on 2017/11/22.
 */
public class Main2 {

    // 活动的时间
    // 既包含开始时间，也包含结束时间
    public static class ActivityTime {
        public int time;// 时间
        public int type;// 时间的类型 1表示开始 -1表示结束
    }

    // 活动的时间
    public static ActivityTime[] activityTimes;

    public static int mColor(int n) {
        // 某个时间段需要的会场数
        int roomNum = 0;
        // 需要的会场的最大数量
        int roomMax = 0;

        // 对活动时间进行排序,不区分开始与结束时间
        Arrays.sort(activityTimes, Comparator.comparingInt(e -> e.time));

        // 扫描全部时间
        for (int i = 0; i < n * 2; i++) {

            // 如果是开始的时间,会场数加一
            // 如果是结束的时间,会场数减一
            roomNum += activityTimes[i].type;

            // 每次计算都记录是否为最多的会场数
            if(roomNum > roomMax) {
                roomMax = roomNum;
            }
        }

        return roomMax;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        activityTimes = new ActivityTime[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            activityTimes[i] = new ActivityTime();
            activityTimes[i].time = scanner.nextInt();
            activityTimes[i].type = i % 2 == 0 ? 1 : -1;
        }

        System.out.println(mColor(n));
    }
}

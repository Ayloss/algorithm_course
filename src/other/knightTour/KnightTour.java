package other.knightTour;

/**
 * Warnsdorf法求一个解。
 * 思路是往窄的路走。
 * 每一步，都先假设把棋子移动到周围八个方向。然后计算在这八个位置的通路数，选择通路数最小的一条路走。
 *
 * Created by status200 on 2017/11/14.
 */
public class KnightTour {

    public static final int boardSize = 8;

    public static int[][] board;

    public static int[] directionX;

    public static int[] directionY;

    public static boolean foundPath = false;

    public static void init() {

        board = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }

        directionX = new int[]{1, -1, -2, -2, -1, 1, 2, 2};
        directionY = new int[]{2, 2, 1, -1, -2, -2, -1, 1};

    }

    // 计算某一个点周围可通行的出路数
    public static int calDirectionWays(int x,int y) {
        int count = 0;

        for (int i = 0; i < 8; i++) {
            int x1 = x + directionX[i];
            int y1 = y + directionY[i];

            if (!boundary(x1, y1) && !passed(x1, y1)) {
                count++;
            }
        }

        return count;
    }

    public static void printPath() {
        for (int i = 0; i < boardSize; i++) {

            for (int j = 0; j < boardSize; j++) {
                System.out.print(String.format("%4d", board[i][j]));
            }
            System.out.println();
        }

    }

    public static boolean boundary(int x, int y) {
        return x < 0 || x > boardSize - 1 || y < 0 || y > boardSize - 1;
    }

    public static boolean passed(int x, int y) {
        return board[x][y] > 0;
    }

    public static void move(int x, int y, int step) {

        if(foundPath) {
            return;
        }

        if (step >= boardSize * boardSize) {
            board[x][y] = step;
            printPath();
            foundPath = true;
            return;
        }

        int bestDirectionWaysCount = 9;
        int bestX = -1;
        int bestY = -1;

        // 移动到该位置
        board[x][y] = step;
        // 寻找下个位置出路最少的方向
        for (int i = 0; i < 8; i++) {
            int x1 = x + directionX[i];
            int y1 = y + directionY[i];

            // 保证下一个位置没有超出边界，且没有被经过
            if (boundary(x1, y1) || passed(x1, y1)) {
                continue;
            }

            // 计算下个位置的出路数
            int directionWaysCount = calDirectionWays(x1, y1);

            // 如果是当前是倒数第二步,且下一位置没有被经过的话,
            // 填入下一位置即得到解
            if(directionWaysCount == 0 && step + 1 == boardSize * boardSize) {
                move(x1,y1,step +1);
                break;
            }

            // 比较得到最窄的方向
            // 多个方向出路数一致的时候取第一个遇到的方向
            if(directionWaysCount > 0 && directionWaysCount < bestDirectionWaysCount) {
                bestDirectionWaysCount = directionWaysCount;
                bestX = x1;
                bestY = y1;
            }
        }

        // 如果存在可通行的最窄方向，往下个方向移动
        if(step + 1 < boardSize * boardSize && bestDirectionWaysCount <= 8) {
            move(bestX, bestY, step + 1);
        }

        // 回溯
        board[x][y] = 0;
    }

    public static void main(String[] args) {

        init();

        move(4, 5, 1);

    }
}

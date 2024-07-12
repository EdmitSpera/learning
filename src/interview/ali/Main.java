package interview.ali;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] lamps = {
                {1, 4},
                {2, 5},
                {4, 100},
                {50, 30},
                {1000, 1}
        };
        System.out.println(maxScore(lamps));
    }

    public static int maxScore(int[][] lamps) {
        int n = lamps.length;

        // 只有一个灯的情况
        if (n == 1) {
            return Math.max(lamps[0][0], lamps[0][1]);
        }

        return Math.max(tryColor(lamps, 1), tryColor(lamps, 2));
    }

    private static int tryColor(int[][] lamps, int firstColor) {
        int n = lamps.length;
        int[][] dp = new int[n][3];  // 0: 不点亮, 1: 点黄灯, 2: 点蓝灯

        // 初始化第一盏灯的状态
        if (firstColor == 1) {
            dp[0][1] = lamps[0][0];  // 点亮黄灯
            dp[0][2] = 0;            // 不允许点蓝灯
        } else {
            dp[0][2] = lamps[0][1];  // 点亮蓝灯
            dp[0][1] = 0;            // 不允许点黄灯
        }

        // 填充 dp 表
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + lamps[i][0];
            dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + lamps[i][1];
        }

        // 处理最后一盏灯的约束
        if (firstColor == 1) {
            return Math.max(dp[n - 1][0], dp[n - 1][2]);  // 最后一盏灯不能点黄
        } else {
            return Math.max(dp[n - 1][0], dp[n - 1][1]);  // 最后一盏灯不能点蓝
        }
    }
//    //第一行输入一个整数 n（1 ≤ n ≤ 10^5）表示节点数量。
//    //
//    //第二行输入一个长度为 n 的字符串 s 表示节点的颜色，第
//    // i 个节点的颜色为 S_i，若 S_i 为 ‘B’ 表示节点的颜色为黑色，若 S_i 为 ‘R’ 则表示节点的颜色为红色。
//    //
//    //接下来 n-1行，每行输入两个整数 u, v 表示树上的边 （1 <= u, v <= n）
//    //3
//    //BRB
//    //1 2
//    //2 3
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        boolean[] node = new boolean[n];
//        ArrayList<int[]> vertices = new ArrayList<>();
//        int ans = 0;
//        String color = in.next();
//        // 对颜色解析
//        for (int i = 0; i < color.length(); i++) {
//            if (color.charAt(i) == 'B') {
//                node[i] = false;
//            } else if (color.charAt(i) == 'R') {
//                node[i] = true;
//            }
//        }
//        for (int i = 0; i < n - 1; i++) {
//            int x = in.nextInt();
//            int y = in.nextInt();
//            vertices.add(new int[]{x, y});
//        }
//        while (vertices.size() != 0) {
//            int[] remove = vertices.remove(0);
//            if ((node[remove[0] - 1] && !node[remove[1] - 1]) || (!node[remove[0] - 1] && node[remove[1] - 1])) {
//                ans++;
//            }
//        }
//        System.out.println(ans);
//    }
}

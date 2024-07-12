package 算法课程复习;

public class CompleteKnapsack {

    /**
     * 求解完全背包问题的最大装载数量
     *
     * @param weights  集装箱的重量数组
     * @param capacity 轮船的载重量
     * @return 能装载的最大集装箱数量
     */
    public static int completeKnapsack(int[] weights, int capacity) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < n; i++) {
            for (int j = weights[i]; j <= capacity; j++) {
                if (weights[i] <= j) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + 1);
                }
            }
        }
        return dp[capacity];
    }


    public static void main(String[] args) {
        // 定义集装箱的重量
        int[] weights = {5, 10, 3, 2, 8, 7, 6};

        int C = 20; // 轮船的载重量

        // 获取能够装载的最大集装箱数量
        int maxContainers = completeKnapsack(weights, C);

        // 输出结果
        System.out.println("能够装载的最大集装箱数量是：" + maxContainers);
    }
}

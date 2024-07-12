package 算法课程复习;

public class packsack {
    /**
     * 求解0/1背包问题的最大价值
     *
     * @param weight   物品的重量数组
     * @param value    物品的价值数组
     * @param capacity 背包的容量
     * @return 背包能装下的最大价值
     */
    public static int knapsack(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= capacity; j++){
                if(weight[i - 1] <= j){
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }


    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int capacity = 4;

        int maxValue = knapsack(weight, value, capacity);
        System.out.println("背包能装下的最大价值是：" + maxValue);
    }
}

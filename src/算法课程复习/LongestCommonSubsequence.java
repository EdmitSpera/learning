package 算法课程复习;

public class LongestCommonSubsequence {

    /**
     * 求解最长公共子序列的长度
     *
     * @param X 序列X
     * @param Y 序列Y
     * @return 最长公共子序列的长度
     */
    public static int lcs(String X, String Y) {
        int n = X.length();
        int m = Y.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }


    /**
     * 测试最长公共子序列算法
     */
    public static void main(String[] args) {
        String X = "ABCBDAB";
        String Y = "BDCAB";

        int result = lcs(X, Y);
        System.out.println("最长公共子序列的长度是：" + result);
    }
}

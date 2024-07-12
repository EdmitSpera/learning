package forth;

import com.sun.source.tree.Tree;

import java.util.*;

public class Main {

    public String LCS(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];

        // base case
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }

        for (int i = 0; i < str2.length(); i++) {
            if (str2.charAt(i) == str1.charAt(0)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
            }
        }

        // 状态转移
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;

                }
            }
        }
        return null;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;

        if (preLen != inLen) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>(preLen);
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preLen - 1, map, 0, inLen - 1);
    }

    // 递归
    private TreeNode buildTree(int[] preorder, int preLeft,
                               int preRight, Map<Integer, Integer> map, int inLeft, int inRight) {
        // 结束条件
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        // 根据前序遍历找到根节点
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);

        // 找到中序遍历中的根节点索引，划分两半区域，左子树和右子树
        int pIndex = map.get(rootVal);

        root.left = buildTree(preorder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex - 1);
        root.right = buildTree(preorder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static ArrayList<Integer> res;
    public static boolean[] buy;
    public static int ans = 0;


    // 买二赠一
    public static boolean backtrack(int[] track) {
        // 结束条件
        // 购买完
        if (check(buy)) {
            for (int i = 0; i < track.length; i++) {
                if (!buy[i]) {
                    ans += track[i];
                }
            }
            res.add(ans);
            return true;
        }

        // 做出选择
        for (int i = 0; i < track.length; i++) {// 选择第一个购买的产品
            if (!buy[i]) {
                for (int j = i + 1; j < track.length; j++) {// 选择第二个产品
                    if (!buy[j]) {
                        int P = Math.min(track[i], track[j]);
                        int temp = 0;
                        int max = 0;
                        int index = 0;
                        int tempSum = 0;
                        for (int k = 0; k < track.length; k++) {
                            if (!buy[k] && track[k] < (double) P / 2) {
                                temp = track[k];
                                if (temp >= max) {
                                    max = temp;
                                    index = k;
                                }
                            }
                        }
                        if (temp == 0) {// 说明该方案太小了，没有拿到免费物品
                            continue;
                        }
                        // 将选择的商品标记
                        buy[i] = true;
                        buy[j] = true;
                        buy[index] = true;

                        tempSum = track[i] + track[j];
                        ans += tempSum;
                        if (backtrack(track)) {
                            return true;
                        }

                        // 回溯
                        buy[i] = false;
                        buy[j] = false;
                        buy[index] = false;
                        ans -= tempSum;
                    }
                }
            }
        }
        return false;
    }

    public static boolean check(boolean[] buy) {
        int ans = 0;
        for (int i = 0; i < buy.length; i++) {
            if (!buy[i]) {
                ans++;
            }
        }
        if (ans <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int countPass(double[] nums) {
        int ans = 0;
        for (double curScore : nums) {
            if (curScore >= 60) {
                ans++;
            }
        }
        return ans;
    }

    public static int countExcellent(double[] nums) {
        int ans = 0;
        for (double curScore : nums) {
            if (curScore >= 85) {
                ans++;
            }
        }
        return ans;
    }

    public static int[][] init(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int maxX = Math.max(Math.max(Math.max(x1, x2), x3), x4);
        int maxY = Math.max(Math.max(Math.max(y1, y2), y3), y4);
        return new int[maxY + 1][maxX + 1];
    }

    public static void sign(int[][] nums, int x1, int y1, int x2, int y2) {
        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                nums[i][j]++;
            }
        }
    }

    public static int[] found(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int minX = Math.min(Math.min(Math.min(x1, x2), x3), x4);
        int minY = Math.min(Math.min(Math.min(y1, y2), y3), y4);
        int maxX = Math.max(Math.max(Math.max(x1, x2), x3), x4);
        int maxY = Math.max(Math.max(Math.max(y1, y2), y3), y4);
        return new int[]{minX, minY, maxX, maxY};
    }

    public static int check(int[][] nums, int x1, int y1, int x2, int y2) {
        int ans = 0;
        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                if (nums[i][j] > 0) {
                    ans++;
                }
            }
        }
        return ans;
    }


//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] x = new int[n];
//        int[][] nums = new int[n - 1][2];
//        for (int i = 0; i < n; i++) {
//            x[i] = scanner.nextInt();
//        }
//        for (int i = 0; i < n - 1; i++) {
//            nums[i][0] = scanner.nextInt();
//            nums[i][1] = scanner.nextInt();
//        }
//        double dprograming = dprograming(n, nums, x);
//        DecimalFormat df = new DecimalFormat("#.00");
//        df.setRoundingMode(RoundingMode.HALF_UP);
//
//        System.out.println(df.format(dprograming));
//    }

    public static double dprograming(int n, int[][] nums, int[] x) {
        double[] dp = new double[n];
        dp[0] = x[0];
        int curX = x[0];
        int curY = 0;
        for (int i = 1; i < n; i++) {
            double move = move(curX, curY, x[i]);
            double v = tpMove(curY, nums[i - 1][0]);
            if (i != n - 1) {
                if (move > v) {
                    curX = x[i];
                    curY = nums[i][1];
                } else if (move < v) {
                    curX = x[i];
                    curY = 0;
                }
            }
            if (i == n - 1) {
                dp[i] += dp[i - 1] + Math.min(move, v + nums[n - 2][1]);
            } else
                dp[i] += dp[i - 1] + Math.min(move, v);
        }
        return dp[n - 1];
    }

    public static double move(int x1, int y1, int x2) {
        if (y1 == 0) {
            return x2 - x1;
        } else {
            return (x2 - x1) + y1 / 1.3;
        }
    }

    public static double tpMove(int y1, int a) {
        if (y1 > a) {//需要下降
            return (y1 - a) / 1.3;
        } else if (y1 < a) {//需要上升
            return (a - y1) / 0.7;
        }
        return 0;
    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int x1 = scanner.nextInt();
//        int y1 = scanner.nextInt();
//        int x2 = scanner.nextInt();
//        int y2 = scanner.nextInt();
//        int x3 = scanner.nextInt();
//        int y3 = scanner.nextInt();
//        int x4 = scanner.nextInt();
//        int y4 = scanner.nextInt();
//        int[] bound = found(x1, y1, x2, y2, x3, y3, x4, y4);
//        int[][] nums = init(x1, y1, x2, y2, x3, y3, x4, y4);
//        sign(nums, x1, y1, x2, y2);
//        sign(nums, x3, y3, x4, y4);
//        int check = check(nums, bound[0], bound[1], bound[2], bound[3]);
//        System.out.println(check);
//    }

//    // 砍树
//    public static List<List<Integer>> graph;
//    public static Vertice[] vertices;   // 边集
//
//    // 回溯算法参数
//    public static List<List<Integer>> res = new LinkedList<>();
//    public static boolean[] visited;
//
//    public static List<List<Integer>> entry(int start, int target) {
//        LinkedList<Integer> track = new LinkedList<>();
//        backtrack(start, target, track);
//        return res;
//    }

//    public static void backtrack(int cur, int target, LinkedList<Integer> track) {
//        // 结束条件
//        if (cur == target) {
//            track.add(cur);
//            LinkedList<Integer> tempTrack = new LinkedList<>(track);
//            res.add(tempTrack);
//            return;
//        }
//        if (visited[cur]) {
//            return;
//        }
//        track.add(cur);
//        visited[cur] = true;
//
//        // 扩散
//        for (Integer neighbor : graph.get(cur)) {
//            // 剪枝
//            if (track.contains(neighbor) || visited[neighbor]) {
//                continue;
//            }
//            backtrack(neighbor, target, track);
//            track.removeLast();
//            visited[neighbor] = false;
//        }
//    }
//
//    static class Vertice {
//        HashSet<Integer> track;
//        int id, weight;
//
//        public Vertice(int id, int x, int y) {    // 传入一个索引 从 0 开始，所以加一
//            this.id = id + 1;
//            weight = 0;
//            track = new HashSet<>();
//            track.add(x);
//            track.add(y);
//        }
//    }
//
//    public static int getRes() {
//        for (List<Integer> tempList : res) {
//            for (int i = 0; i < tempList.size() - 1; i++) {
//                for (int j = i + 1; j < tempList.size(); j++) {
//                    for (Vertice vertice : vertices) {
//                        if (vertice == null) {
//                            continue;
//                        }
//                        if (vertice.track.contains(tempList.get(i)) && vertice.track.contains(tempList.get(j))) {
//                            vertice.weight++;
//                        }
//                    }
//                }
//            }
//        }
//        int index = 0, max = 0;
//        for (Vertice vertice : vertices) {
//            if (vertice == null) {
//                continue;
//            }
//            if (vertice.weight >= max) {
//                index = vertice.id;
//                max = vertice.weight;
//            }
//        }
//        return index;
//    }
//
//    public static void input() {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//
//        // 初始化边集和图
//        vertices = new Vertice[n];
//        graph = new LinkedList<>();
//        for (int i = 0; i < n + 1; i++) {
//            graph.add(new LinkedList<>());
//        }
//
//        for (int i = 0; i < n - 1; i++) {
//            int x = scanner.nextInt();
//            int y = scanner.nextInt();
//            vertices[i] = new Vertice(i, x, y);
//            graph.get(x).add(y);
//            graph.get(y).add(x);
//        }
//
//        for (int i = 0; i < m; i++) {
//            visited = new boolean[n + 1];
//            int start = scanner.nextInt();
//            int target = scanner.nextInt();
//            entry(start, target);
//        }
//    }

    // 题三
//    private static List<List<Integer>> res = new LinkedList<>();
//
//    public static List<List<Integer>> getR1(int[] nums) {
//        LinkedList<Integer> track = new LinkedList<>();
//        boolean[] visited = new boolean[nums.length];
//        backtrack(nums, 0, track, visited);
//        return res;
//    }
//
//    public static void backtrack(int[] nums, int cur, LinkedList<Integer> track, boolean[] visited) {
//        res.add(new LinkedList<>(track));
//
//        for (int i = cur; i < nums.length; i++) {
//            visited[i] = true;
//            track.add(nums[i]);
//            backtrack(nums, i + 1, track, visited);
//            track.removeLast();
//            visited[i] = false;
//        }
//    }
//
//    public static int getSum(int[] nums) {
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i];
//        }
//        return sum;
//    }
//
//    public static void luckyNum() {
//        int ans = 0;
//        for (int i = 1; i < 1000000; i++) {
//            String binaryString = Integer.toBinaryString(i);
//            String octalString = Integer.toOctalString(i);
//            String decmalString = String.valueOf(i);
//            String hexString = Integer.toHexString(i);
//
//            if (check(binaryString, i, 2) && check(octalString, i, 8) && check(decmalString, i, 10) && check(hexString, i, 16)) {
//                ans++;
//                System.out.println(ans + ":" + i);
//            }
//        }
//        System.out.println("done");
//    }
//
//    public static boolean check(String numString, int num, int radio) {
//        int res = 0;
//        for (int i = 0; i < numString.length(); i++) {
//            res += Integer.parseInt(String.valueOf(numString.charAt(i)), radio);
//        }
//        return num % res == 0;
//    }

//
//    // problem 1
//    public static void count() {
//        BigInteger res = new BigInteger("0");
//        for (int i = 1; i < 100; i++) {
//            BigInteger cur = BigInteger.valueOf(i);
//            res = res.add(n_fang(cur)).mod(BigInteger.TEN.pow(9));
//            System.out.println(res);
//        }
//    }
//
//    public static BigInteger n_fang(BigInteger num) {
//        BigInteger res = new BigInteger("1");
//        while (!num.equals(BigInteger.ZERO)) {
//            res = res.multiply(num);
//            num = num.subtract(BigInteger.ONE);
//        }
//        return res;
//    }

}

import java.util.*;

public class Main {
    static int endNums = 0;
    static int dayNums = 0;
    static int[] res = new int[7];

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

    public static void levelOrder(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        ArrayDeque<TreeNode> levelQueue = new ArrayDeque<>();

        levelQueue.add(root);
        queue.add(root);

        // bfs
        while (!queue.isEmpty()) {
            queue.poll();

            // 层序
        }
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        main.maxSlidingWindow(nums, 3);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 特殊情况
        if(nums.length == 0){
            return new int[1];
        }else if(nums.length == 1 && k == 1){
            return new int[]{1};
        }
        int n = nums.length - k + 1;    // n = 6
        int[] res = new int[n];
        int max;
        for(int i = 0; i < n; i++){  // i 最大 5
            max = Integer.MIN_VALUE;
            for(int j = i; j < i + k; j++){ // j 最大 7
                max = Math.max(max,nums[j]);
            }
            res[i] = max;
        }
        return res;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> tempList = new ArrayList<>();
            // base case
            if (i == 0) {
                tempList.add(1);
                res.add(tempList);
                continue;
            }

            // 构造每一层的List
            for (int j = 0; j <= i; j++) {
                // base case
                if (j == 0 || j == i) {
                    tempList.add(1);
                }else{
                    // 获取前一行的list
                    List<Integer> preList = res.get(i - 1);
                    int L = preList.get(j - 1), R = preList.get(j);
                    tempList.add(L + R);
                }
            }
            res.add(tempList);
        }
        return res;
    }

    public int[][] merge(int[][] intervals) {
        // 特殊情况处理
        if (intervals.length == 0) {
            return new int[0][2];
        }

        // 实现匿名的比较器，按照第一个来排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> res = new ArrayList<int[]>();
        // 遍历
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            if (res.size() == 0 || res.get(res.size() - 1)[1] < L) {
                res.add(new int[]{L, R});
            } else {
                res.get(res.size() - 1)[1] = Math.max(R, res.get(res.size() - 1)[1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    public int maxSubArray(int[] nums) {
        // 特殊情况
        if (nums.length == 1) {
            return nums[0];
        }
        // 贪心
        int ans = 0, maxSum = 0;
        ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = Math.max(nums[i], ans + nums[i]);
            maxSum = Math.max(ans, maxSum);
        }
        return maxSum;
    }

    static int ans = 0;
    static boolean[][] visited;

    public int numIslands(char[][] grid) {
        // 初始化visited
        int n = grid.length;
        int m = grid[0].length;

        visited = new boolean[n][m];

        // 扫描岛屿
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {   // 这里需要保证为'1'的节点还未访问
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }

        return ans;
    }

    public void dfs(char[][] grid, int row, int col) {
        if (!check(grid, row, col) || visited[row][col] || grid[row][col] == '0') {   // 三种返回情况：越界、已访问该节点、不为陆地
            return;
        }

        // 能够进行到这一步逻辑的节点都是未访问的
        visited[row][col] = true;

        // 上下左右扩散
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
    }

    public boolean check(char[][] grid, int row, int col) {
        // 边界条件检查
        int n = grid.length;
        int m = grid[0].length;
        if (row < 0 || row >= n || col < 0 || col >= m) {
            return false;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head, pre = head;
        ListNode cur = head;

        while (fast == null || fast.next == null) {
            fast = fast.next.next;
            slow = slow.next;

            if (pre.next != slow) {    // 跳过第一次
                pre = pre.next;
            } else {
                continue;
            }
        }

        // 此时slow指向中间（奇数）|| 指向中间偏右（偶数）  pre指向slow前一个
        pre.next = reverseListNode(slow);
        pre = pre.next;
        ListNode flag = pre;

        // 回文判断
        while (cur != flag) {
            if (cur.val != pre.val) {
                return false;
            }
            cur = cur.next;
            pre = pre.next;
        }

        return true;
    }

    public ListNode reverseListNode(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public ListNode ReverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;

        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            map.put(sum, map.getOrDefault(nums[i], 0) + 1);
            if (map.containsKey(sum - k)) {
                res++;
            }
        }
        return res;
    }


    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }

        // 初始化 pCount 和 sCount
        HashMap<Character, Integer> pCount = new HashMap<>();
        HashMap<Character, Integer> sCount = new HashMap<>();

        for (char c : p.toCharArray()) {
            pCount.put(c, pCount.getOrDefault(c, 0) + 1);
        }

        int pLen = p.length();
        int sLen = s.length();

        for (int i = 0; i < pLen; i++) {
            char c = s.charAt(i);
            sCount.put(c, sCount.getOrDefault(c, 0) + 1);
        }

        if (pCount.equals(sCount)) {
            res.add(0);
        } else if (sLen == pLen && !pCount.equals(sCount)) {
            return res;
        }

        // 滑动窗口
        for (int i = pLen; i < sLen; i++) {
            char newChar = s.charAt(i);
            char oldChar = s.charAt(i - pLen);

            sCount.put(newChar, sCount.getOrDefault(newChar, 0) + 1);
            if (sCount.get(oldChar) == 1) {
                sCount.remove(oldChar);
            } else {
                sCount.put(oldChar, sCount.get(oldChar) - 1);
            }

            if (pCount.equals(sCount)) {
                res.add(i - pLen + 1);
            }
        }

        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 跳过重复的元素
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            List<List<Integer>> tempRes = twoSum(nums, i + 1, nums.length - 1, -nums[i]);
            for (List<Integer> list : tempRes) {
                list.add(nums[i]);
                res.add(list);
            }
        }

        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int left, int right, int target) {
        List<List<Integer>> res = new ArrayList<>();

        // 这里的难点是，需要考虑找出多个结果，并且需要考虑重复的元素
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                ArrayList<Integer> tempRes = new ArrayList<>();
                tempRes.add(nums[left]);
                tempRes.add(nums[right]);
                res.add(tempRes);

                // 去除重复的元素，并且继续往下找
                while (left < right && nums[left + 1] == nums[left]) {
                    left++;
                }
                while (left < right && nums[right - 1] == nums[right]) {
                    right--;
                }
                left++;
                right--;

            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String key = String.valueOf(charArr);
            if (map.containsKey(key)) {
                ArrayList<String> tempRes = map.get(key);
                tempRes.add(strs[i]);
            } else {
                ArrayList<String> tempRes = new ArrayList<>();
                tempRes.add(strs[i]);
                map.put(key, tempRes);
            }

        }

        for (ArrayList<String> tempRes : map.values()) {
            res.add(tempRes);
        }
        return res;
    }


//    public static void equip(String property){
//        String[] splitStr = property.split(" ");
//        if(map.containsKey(splitStr[0])){
//            map.put(splitStr[0],map.get(splitStr[0]) + Integer.parseInt(splitStr[1],10));
//        }else {
//            map.put(splitStr[0],Integer.parseInt(splitStr[1]));
//        }
//    }


    public int lengthOfLongestSubstring(String s) {
        // 处理特殊情况
        if (s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }
        int res = 0;
        HashSet<Character> set = new HashSet<>();
        // 滑动窗口
        int left = 0, right = 1;
        set.add(s.charAt(left));

        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
            } else {
                // 遇到重复字符的情况
                res = Math.max(res, right - left);
                left++;
                right = left + 1;
                set.clear();
                set.add(s.charAt(left));
            }
        }
        return Math.max(res, right - left);
    }

    public int trap(int[] height) {
        // 变量声明
        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;

        while (left < right) {
            // 计算当前左右边界最大值
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 移动滑动窗口 -> 小的先走
            if (height[left] < height[right]) {
                // 计算雨水值
                res += leftMax - height[left++];
            } else {
                res += rightMax - height[right--];
            }
        }
        return res;
    }

    public int maxArea(int[] height) {
        int[] dp = new int[height.length];
        int left = 0;
        int right = height.length - 1;

        for (int i = 0; i < height.length; i++) {
            dp[i] = count(left, right, height);

            // 移动滑动窗口
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        Arrays.sort(dp);
        return dp[dp.length - 1];
    }

    public int count(int indexA, int indexB, int[] height) {
        return Math.min(height[indexA], height[indexB]) * Math.max(Math.abs(indexA - indexB), Math.abs(indexB - indexA));
    }

//    static int res = 0;


    private static int num = 0;
    private static Object lock = new Object();


    static int height = 1;
    static int weight = 0;
//    static Map<Character,Integer> map = new HashMap<>();

//    public static void initMap(String s){
//        String[] splitStr = s.split(" ");
//        char temp = 'a';
//        for(String curStr:splitStr){
//            map.put(temp,Integer.parseInt(curStr,10));
//            temp = (char)(temp+1);
//        }
//    }

//    public static void countRes(String str){
//        for(char c:str.toCharArray()){
//            Integer curWeight = 0;
//            if(map.containsKey(c)){
//                curWeight = map.get(c);
//            }
//            if(weight + curWeight <= 100){
//                weight += curWeight;
//            }else{
//                weight = curWeight;
//                height++;
//            }
//        }
//    }

    public int handle(int num) {
        int result = 0;
        int i = num;
        while (i != 0) {
            i = i / 10 * 10;
            result = result * 10 + num - i;
            i = i / 10;
            num = num / 10;
        }
        return result;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 处理特殊情况
        if (head.next == null) {
            return null;
        }
        // 双指针 找到当前结点
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return deleteNode(head, slow);
    }

    public ListNode deleteNode(ListNode head, ListNode preDelete) {
        ListNode temp = preDelete.next.next;
        preDelete.next.next = null;
        preDelete.next = temp;
        return head;
    }


    public int[] twoSum(int[] nums, int target, int curIndex) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (i == curIndex) {
                continue;
            }
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp) && map.get(temp) != i) {
                res[0] = nums[i];
                res[1] = nums[map.get(temp)];
                break;
            }
        }
        return res;
    }


    public void moveZeroes(int[] nums) {
        // 特殊情况
        if (nums.length == 1 && nums[0] == 0) {
            return;
        }

        // 双指针
        int i = 0;  // 指向0的指针
        int j = 0;  // 指向非0的指针

        while (j < nums.length) {
            if (nums[j] == 0) {
                j++;
                continue;
            }
            swap(nums, i, j);
            i++;
            j++;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public int longestConsecutive(int[] nums) {
        // 特殊情况
        if (nums.length == 0) {
            return 0;
        }
        // O(n)
        HashSet<Integer> set = new HashSet<>();
        // 去重
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int max = 1;
        int curCount = 1;
        // 扫描
        for (Integer cur : set) {
            if (set.contains(cur - 1)) {
                continue;
            }
            while (set.contains(++cur)) {
                curCount++;
            }
            max = Math.max(max, curCount);
            curCount = 1;
        }
        return max;
    }

//    public List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> res = new ArrayList<>();
//        HashMap<String, ArrayList<String>> map = new HashMap<>();
//
//
//        for (int i = 0; i < strs.length; i++) {
//            char[] charArray = strs[i].toCharArray();
//            Arrays.sort(charArray);
//            String temp = new String(charArray);
//            if (!map.containsKey(temp)) {
//                ArrayList<String> value = new ArrayList<>();
//                value.add(strs[i]);
//                map.put(temp, value);
//            } else {
//                map.get(temp).add(strs[i]);
//            }
//        }
//        for (ArrayList<String> values : map.values()) {
//            ArrayList<String> strings = new ArrayList<>();
//            for (String value : values) {
//                strings.add(value);
//            }
//            res.add(strings);
//        }
//        return res;
//    }


    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public String largestNumber(int[] nums) {
        String[] str = new String[nums.length];
        String res = "";
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(str, new Comparator<String>() {
            public int compare(String s1, String s2) {
                String order1 = s1 + s2;
                String order2 = s2 + s1;
                return order2.compareTo(order1);
            }
        });
        for (String strTemp : str) {
            res += strTemp;
        }
        return res;
    }

//    public int maxArea(int[] height) {
//        int indexA = 0;
//        int indexB = height.length - 1;
//        int[] res = new int[height.length];
//        for (int i = 0; i < res.length; i++) {
//            if (height[indexA] <= height[indexB]) {
//                res[i] = count(indexA++, indexB, height);
//            } else {
//                res[i] = count(indexA, indexB--, height);
//            }
//        }
//        Arrays.sort(res);
//        return res[res.length - 1];
//    }

//    public int count(int indexA, int indexB, int[] height) {
//        int minNum = min(indexA, indexB, height);
//        return minNum * (indexB - indexA);
//    }

    public int min(int indexA, int indexB, int[] height) {
        return height[indexA] >= height[indexB] ? height[indexB] : height[indexA];
    }

    public static int singleNonDuplicate(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    // 打印偶数
    public static class Task_1 implements Runnable {

        @Override
        public void run() {
            while (num < 100) {
                synchronized (lock) {
                    // 获取当前锁，进行类型检查，不满足则阻塞
                    while (num % 2 == 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // 执行操作
                    System.out.println(Thread.currentThread().getName() + ":A");
                    num++;
                    // 释放当前锁
                    lock.notifyAll();
                }
            }
        }
    }

    // 打印奇数
    public static class Task_2 implements Runnable {

        @Override
        public void run() {

            while (num < 100) {
                synchronized (lock) {
                    // 获取当前锁，进行类型检查，不满足则阻塞
                    while (num % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // 执行操作
                    System.out.println(Thread.currentThread().getName() + ":B");
                    num++;
                    // 释放当前锁
                    lock.notifyAll();
                }
            }

        }
    }

    public static HashSet<Integer> checkRepeat(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> res = new HashSet<>();

        for (int curNum : nums) {
            if (hashSet.contains(curNum)) {
                res.add(curNum);
            } else {
                hashSet.add(curNum);
            }
        }
        return res;
    }
}
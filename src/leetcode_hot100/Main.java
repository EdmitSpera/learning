package leetcode_hot100;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        Main main = new Main();
        main.firstMissingPositive(nums);

    }

    public int firstMissingPositive(int[] nums) {
        // 记录当前最小的正数
        int minNum = 1, n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        // 更新规则：过滤小于1的数 贪心取最小 依次记录数
        for (int i = 0; i < n; i++) {
            if (nums[i] < 1) {
                continue;
            }
            set.add(nums[i]);
            minNum = Math.min(minNum, nums[i]);
        }

        // 三种情况
        // minNum数不在set内 是最小
        // minNum数在set内 加一不在set内 加一是最小
        // minNum数在set内 加一在set内  说明存在连续 找到第二种情况
        if(!set.contains(minNum)){
            return minNum;
        }else if(set.contains(minNum) && !set.contains(minNum + 1)){
            return minNum++;
        }else if(set.contains(minNum) && set.contains(minNum + 1)){
            while(set.contains(minNum + 1)){
                minNum++;
            }
            return minNum++;
        }
        return 1;
    }
}

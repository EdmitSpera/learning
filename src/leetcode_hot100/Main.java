package leetcode_hot100;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            // 生成“特征键”的过程
            char[] tempChar = strs[i].toCharArray();
            Arrays.sort(tempChar);
            String key = String.valueOf(tempChar);

            // 结果加入map的过程
            if (map.containsKey(key)) {
                ArrayList<String> tempList = map.get(key);
                tempList.add(key);
            } else {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(strs[i]);
                map.put(key, tempList);
            }
        }

        res.addAll(map.values());
        return res;
    }
}

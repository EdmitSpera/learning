package 算法课程复习;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> backtrackEntry(int[] arr) {
        ArrayList<Integer> track = new ArrayList<>();
        backtrack(arr, track, 0);
        return res;
    }

    public static void backtrack(int[] arr, List<Integer> track, int start) {
        res.add(new ArrayList<>(track));

        for (int i = start; i < arr.length; i++) {
            track.add(arr[i]);
            backtrack(arr, track, i + 1);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] s = {1, 2, 3, 4, 5, 6, 7};
        List<List<Integer>> subsets = backtrackEntry(s);
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }
}

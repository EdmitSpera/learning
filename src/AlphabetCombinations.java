import java.util.ArrayList;
import java.util.List;

public class AlphabetCombinations {

    /**
     * 递归方法获取所有组合
     *
     * @param str 需要被组合的字符串
     * @return List<String> 所有组合的列表
     */
    public static List<String> getAllCombinations(String str) {
        List<String> result = new ArrayList<>();
        generateCombinations(str, "", 0, result);
        return result;
    }

    /**
     * 递归生成组合
     *
     * @param str    输入字符串
     * @param current 当前组合
     * @param index   当前索引
     * @param result  存储结果的列表
     */
    private static void generateCombinations(String str, String current, int index, List<String> result) {
        if (index == str.length()) {
            if (!current.isEmpty()) {
                result.add(current);
            }
            return;
        }

        // 包含当前字符
        generateCombinations(str, current + str.charAt(index), index + 1, result);

        // 不包含当前字符
        generateCombinations(str, current, index + 1, result);
    }

    public static void main(String[] args) {
        String alphabet = "abc";
        List<String> combinations = getAllCombinations(alphabet);

        for (String combination : combinations) {
            System.out.println(combination);
        }
    }
}

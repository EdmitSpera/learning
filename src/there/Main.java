package there;

public class Main {

    public static void main(String[] args) {
        System.out.println(Josephus(6));
        System.out.println(Josephus(9));
        System.out.println(Josephus(13));
    }


    static boolean checkPowerOfTwo(int n) { // 判断二次幂
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    static int Josephus(int n) {
        if (checkPowerOfTwo(n) || n == 1) {
            return 1;
        } else {
            int temp = (int) (Math.log(n) / Math.log(2));    // 计算次幂的值 这里使用了对数函数的换底公式
            int maxPowerOfTwo = (int) Math.pow(2, temp);
            return 2 * (n - maxPowerOfTwo) + 1;
        }
    }
    //    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//    }

//    // 第六题
//    static ArrayList<ArrayList<int[]>> xyMap ;
//    static ArrayList<Integer> list ;
//    static ArrayList<ArrayList<Integer>> tree ;
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        int[][] nums = new int[n][m];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                nums[i][j] = scanner.nextInt();
//            }
//        }
//        int limit = scanner.nextInt();
//
//        xyMap = new ArrayList<>(9);
//        for (int i = 0; i < 10; i++) {
//            xyMap.add(new ArrayList<int[]>());
//        }
//        list = new ArrayList<>();
//
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                xyMap.get(nums[i][j]).add(new int[]{i,j});
//            }
//        }
//        for (int i = 0; i < 10; i++) {
//            if(xyMap.get(i).size() != 0){
//                list.add(i);
//            }
//        }
//
//        Collections.sort(list);
//        tree = new ArrayList<>(list.size());
//        for (int i = 0; i < 10; i++) {
//            tree.add(new ArrayList<>());
//        }
//        initTree(list,limit);
//        int res = entryCount();
//        System.out.println(res);
//    }
//
//    public static void initTree(ArrayList<Integer> list,int limit){
//        for (int i = list.size() - 1; i >= 0; i--) {
//            for (int j = i - 1; j >= 0 ; j--) {
//                // 特殊情况处理
//                if(j < 0){
//                    break;
//                }
//
//                int cur = list.get(i);
//                int pointer = list.get(j);
//
//                if(cur - pointer <= limit){
//                    tree.get(cur).add(pointer);
//                }
//            }
//        }
//    }
//    public static int count(int x1,int y1,int x2,int y2){
//        int high = Math.abs(x2 - x1);
//        int width = Math.abs(y2 - y1);
//        if(high == 0){
//            return width;
//        }else if(width == 0){
//            return high;
//        }
//        return high*width;
//    }
//
//    public static int entryCount(){
//        ArrayList<Integer> res = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ArrayList<Integer> curList = tree.get(i);
//            if(curList.size() == 0){
//                continue;
//            }
//
//            for (Integer cur_2: curList){
//                ArrayList<int[]> xy1 = xyMap.get(i);
//                ArrayList<int[]> xy2 = xyMap.get(cur_2);
//
//                for (int[] xy_1: xy1){
//                    ArrayList<Integer> temp = new ArrayList<>();
//                    for (int[] xy_2: xy2){
//                        temp.add(count(xy_1[0],xy_1[1],xy_2[0],xy_2[1]));
//                    }
//                    if(temp.size() ==2){
//                        res.add(Math.max(temp.get(0),temp.get(1)));
//                    }else res.add(temp.get(0));
//                }
//            }
//        }
//        return Collections.max(res);
//    }
//
//
//    //     第五题
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int N = scanner.nextInt();
//        nfang(N);
//    }
//
//    public static void nfang(int N) {
//        boolean flag = true;
//        for (int i = 1; i <= 100000; i++) {
//            if(flag){
//                BigInteger res = new BigInteger(String.valueOf(i));
//                for (int j = i - 1; j > 0; j--) {
//                    BigInteger m = new BigInteger(String.valueOf(j));
//                    res = res.multiply(m);
//                }
//                String s = res.toString(10);
//                for (int j = s.length() - 1; j >= s.length() - N; j--) {
//                    // 终止条件
//                    if (j == s.length() - N && s.charAt(j) == '0' && s.charAt(j - 1) != '0') {
//                        System.out.println(i);
//                        flag = false;
//                    }
//
//                    // 跳出条件
//                    if (s.charAt(j) != '0') {
//                        break;
//                    }
//                }
////                System.out.println(i + "!="+res);
//            }
//        }
//    }
//
//
//// 第四题
////    public static void main(String[] args) {args
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = scanner.nextInt();
//        }
//
////        5
////        12 10 15 20 6
//        int[] rank = Arrays.copyOf(nums, nums.length);
//        Arrays.sort(rank);
//        int[] res = count(rank);
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < res.length; i++) {
//            map.put(rank[i],res[i]);
//        }
//
//        // 打印结果
//        for (int i = 0; i < res.length; i++) {
//            Integer integer = map.get(nums[i]);
//            System.out.print(integer + " ");
//        }
//    }
//
//    public static int[] count(int[] rank){
//        int mid = rank.length / 2;
//        int point = rank[mid] + 1;
//
//        int[] res = new int[rank.length];
//
//        for (int i = 0; i < res.length; i++) {
//            if(i < mid){
//                res[i] = point - rank[i];
//            }else {
//                res[i] = 0;
//            }
//        }
//        return res;
//    }


//    // 第三题
//    public static void main(String[] args) {
//        String s;
//        Scanner scanner = new Scanner(System.in);
//        s = scanner.nextLine();
//        HashMap<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char curChar = s.charAt(i);
//            if (!map.containsKey(curChar)) {
//                map.put(curChar, 1);
//            } else {
//                Integer sourceVal = map.get(curChar);
//                map.put(curChar, sourceVal + 1);
//            }
//        }
//
//        Integer max = Collections.max(map.values());
//        ArrayList<Character> res = new ArrayList<>();
//        for (Map.Entry<Character,Integer> curEntry: map.entrySet()){
//            if(curEntry.getValue() == max){
//                res.add(curEntry.getKey());
//            }
//        }
//        Collections.sort(res);
//        for (char c: res){
//            System.out.print(c);
//        }
//
////        BABBACAC
//    }


// 第二题 双指针 暴力
// 644
//    public static void main(String[] args) {
//        int ans = 0;
////        2022,2022222022
//        for (int i = 2022; i <= 2022222022; i++) {
//            String s = String.valueOf(i);
//            if(s.length() % 2 == 0){// 偶数情况
//                int j = 0;
//                int k = s.length() - 1;
//                int mid = j + (k - j) / 2;
//                for (int l = 0; l <= mid ; l++) {
//                    // 结束情况
//                    if(l == mid && s.charAt(l) == s.charAt(mid + 1)){
//                        ans++;
//                        System.out.println(s);
//                    }
//
//                    // 双指针移动
//                    if(s.charAt(j) == s.charAt(k) && s.charAt(j) < s.charAt(j + 1)){
//                        j++;
//                        k--;
//                    }else break;
//                }
//            }else {// 奇数情况
//                int j = 0;
//                int k = s.length() - 1;
//                int mid = j + (k - j) / 2;
//                for (int l = 0; l < mid ; l++) {
//                    // 结束情况
//                    if(l == mid - 1 && s.charAt(l) == s.charAt(k) && s.charAt(l) < s.charAt(l + 1)){
//                        ans++;
//                        System.out.println(s);
//                    }
//
//                    // 双指针移动
//                    if(s.charAt(j) == s.charAt(k) && s.charAt(j) < s.charAt(j + 1)){
//                        j++;
//                        k--;
//                    }else break;
//                }
//            }
//
//        }
//
//        System.out.println(ans);
//    }

// 第一题 暴力取模运算
// 7
//    public static void main(String[] args) {
//        int a = 6;
//        int temp = 1;
//        for (int i = 1; i <= 22; i++) {
//            temp *= 20;
//            temp %= 7;
//        }
//        System.out.println((a + temp) % 7);
//    }

}

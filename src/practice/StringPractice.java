package practice;

public class StringPractice {
    public final static String str_1 = "this is a string";
    public final static String str_2 = "THIS IS A STRING";
    public final static String str_3 = "  this is a string ";
    public final static String str_4 = "a good   example";
    public static void main(String[] args) {
        myAtoi("abc-0012318asc");
    }

    public static int myAtoi(String str) {
        //读入字符串并丢弃无用的前导空格
        String trim = str.trim();
        String temp = "";
        //检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
        if( trim.contains("-")){
            // 如果有负号
            int indexOfFlag = trim.indexOf("-");

            temp = new String("-").concat(getNum(trim, indexOfFlag));


        }else {
            temp = getNum(trim, 0);
        }
        //如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
        //返回整数作为最终结果。
        return 0;
    }
    public static String getNum(String str,int startIndex){
        String temp = "";
        for (int i = startIndex; i < str.length(); i++) {
            if(Character.isDigit(str.charAt(i))){
                temp = temp.concat(String.valueOf(str.charAt(i)));
            }
        }
        if(temp.startsWith("0")){
            //将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
            int lastIndex = 0;
            for (int i = 0; i < temp.length(); i++) {
                if(!String.valueOf(temp.charAt(i)).equals("0")){
                    break;
                }
                lastIndex = i;
            }
            lastIndex++;
            temp = temp.substring(lastIndex);
        }
        return temp;
    }

    public static String reverseMessage(String message) {
        String[] s = message.trim().split(" ");
        String res = "";
        for (int i = s.length - 1; i > 0; i--) {
            // 去除空项目
            if(s[i].equalsIgnoreCase("")){
                continue;
            }
            res = res.concat(s[i] + " ");
        }
        return res.trim();
    }
    // 1. 字符与字符串
    //写一个函数，接受一个字符串和一个整数索引，返回该索引位置的字符。
    public static char getChar(String str,int index) {
        return str.charAt(index);
    }
    //创建一个方法，将输入的字符串转换为字符数组，并打印出来。
    public static char[] getChars(String str) {
        return str.toCharArray();
    }
    //2. 比较
    //编写一个程序，比较两个字符串是否相等。
    public static boolean compareString(String str1,String str2) {
        return str1.equals(str2);
    }
    //编写一个程序，不区分大小写比较两个字符串是否相等。
    public static boolean compareStringIngnoreCase(String str1,String str2) {
        return str1.equalsIgnoreCase(str2);
    }
    //编写一个程序，比较两个字符串的大小，并解释返回值的意义。
    public static String compareSize(String str1,String str2) {
        int flag = str1.compareTo(str2);
        return returnRes(flag);
    }

    public static String returnRes(int flag){
        if(flag==0) {
            return "equal";
        }else if(flag<0) {
            return "less";
        }else {
            return "more";
        }
    }
    //创建一个程序，不区分大小写比较两个字符串的大小，并解释返回值的意义。
    public static String compareSizeIngnoreCase(String str1,String str2) {
        int flag = str1.compareToIgnoreCase(str2);
        return returnRes(flag);
    }
    //3. 查找
    //编写一个程序，检查一个字符串是否包含另一个字符串。
    public static boolean isContains(String str1,String str2) {
        return str1.contains(str2);
    }
    //编写一个程序，找到一个子字符串在主字符串中的首次出现的索引。
    public static int getFirstIndex(String str1,String str2) {
        return str1.indexOf(str2);
    }
    //编写一个程序，从指定的索引开始，找到一个子字符串在主字符串中的首次出现的索引。
    public static int getFirstIndex(String str1,String str2,int index) {
        return str1.indexOf(str2,index);
    }
    //创建一个程序，找到一个子字符串在主字符串中的最后出现的索引。
    public static int getLastIndex(String str1,String str2) {
        return str1.lastIndexOf(str2);
    }
    //创建一个程序，从指定的索引开始，找到一个子字符串在主字符串中的最后出现的索引。
    public static int getLastIndex(String str1,String str2,int index) {
        return str1.lastIndexOf(str2,index);
    }
    //编写一个程序，判断一个字符串是否以指定的前缀开始。
    //编写一个程序，判断一个字符串是否以指定的后缀结束。
    //4. 截取
    //编写一个程序，从给定索引开始截取字符串直到末尾。
    public static String getSub(String str1) {
        return str1.substring(3);
    }
    //创建一个方法，从给定的开始索引到结束索引截取字符串（不包括结束索引）。
    //5. 替换
    //编写一个程序，替换字符串中的某个字符为另一个字符。
    //编写一个程序，使用正则表达式替换字符串中满足条件的部分。
    //6. 拆分
    //编写一个程序，根据正则表达式拆分字符串。
    //创建一个方法，根据正则表达式拆分字符串，并限制结果的分割数。
    //7. 其他操作方法
    //编写一个程序，返回字符串的长度。
    //创建一个方法，判断一个字符串是否为空。
    //编写一个程序，将字符串转换为全部大写。
    //编写一个程序，将字符串转换为全部小写。
    //创建一个方法，去除字符串首尾的空白字符。
}

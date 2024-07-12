package forth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class dateTest {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("GMT"));
//        long dateLong = scanner.nextLong();
//        Date date = new Date(dateLong);
//        String s = df.format(date);
//        System.out.println(s);

        // 日期问题
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] split = input.split("/");
        // 年月日
        String format1 = f(split[0],split[1],split[2]);
        // 月日年
        String format2 = f(split[2],split[0],split[1]);
        // 日月年
        String format3 = f(split[2],split[1],split[0]);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<Date> dates = new ArrayList<>();
            if (!format1.equals("error")) {
                Date date1 = sdf.parse(format1);
                if(sdf.format(date1).equals(format1)){
                    dates.add(date1);
                }
            }
            if (!format2.equals("error")) {
                Date date2 = sdf.parse(format2);
                if(sdf.format(date2).equals(format2)){
                    dates.add(date2);
                }
            }
            if (!format3.equals("error")) {
                Date date3 = sdf.parse(format3);
                if(sdf.format(date3).equals(format3)){
                    dates.add(date3);
                }
            }
            Collections.sort(dates);
            TreeSet<String> treeSet = new TreeSet<>();
            for (Date curDate : dates) {
                treeSet.add(sdf.format(curDate));
            }
            for (String cur:treeSet ){
                System.out.println(cur);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static String f(String year, String month, String day) {
        StringBuilder dateString = new StringBuilder();

        // 确定年份
        if (Integer.parseInt(year, 10) >= 60 && Integer.parseInt(year, 10) < 100) {
            // 19开头的情况
            dateString.append("19").append(year).append("-");
        } else if (Integer.parseInt(year, 10) >= 0 && Integer.parseInt(year, 10) < 60) {
            // 20开头的情况
            dateString.append("20").append(year).append("-");
        }

        // 处理月份和日期错误情况
        if (month.equals("00") || day.equals("00") || Integer.parseInt(month, 10) > 12 || Integer.parseInt(day, 10) > 31) {
            return "error";
        }

        // 处理闰月的情况
        if(month.equals("02") && Integer.parseInt(day,10)>29){
            return "error";
        }
        dateString.append(month).append("-").append(day);
        return dateString.toString();
    }
}

package 算法课程复习;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ActivitySelection {

    static class Activity{
        int start;
        int finish;

        public Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

    }

    static class ActivityComparator implements Comparator<Activity>{
        @Override
        public int compare(Activity o1, Activity o2) {
            return o1.finish - o2.finish;
        }
    }

    public static List<Activity> selectActivities(Activity[] activities){
        ArrayList<Activity> res = new ArrayList<>();
        Arrays.sort(activities, new ActivityComparator());
        // 加入第一个活动
        res.add(activities[0]);
        int endTime = activities[0].finish;
        for(int i = 1; i < activities.length; i++){
            if(activities[i].start >= endTime){
                res.add(activities[i]);
                endTime = activities[i].finish;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        // 给定活动的开始时间和结束时间
        int[] startTimes = {0, 1, 2, 3, 3, 5, 5, 6, 8, 8, 12};
        int[] finishTimes = {6, 4, 13, 5, 8, 7, 9, 10, 11, 12, 14};

        // 创建活动数组
        Activity[] activities = new Activity[startTimes.length];
        for (int i = 0; i < startTimes.length; i++) {
            activities[i] = new Activity(startTimes[i], finishTimes[i]);
        }

        // 获取选择的活动列表
        List<Activity> selectedActivities = selectActivities(activities);

        // 输出选择的活动
        System.out.println("选择的活动如下：");
        for (Activity activity : selectedActivities) {
            System.out.println("开始时间：" + activity.start + ", 结束时间：" + activity.finish);
        }
    }
}

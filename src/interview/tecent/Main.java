package interview.tecent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n;
    static int l;
    static int[] lamp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        l = scanner.nextInt();
        lamp = new int[n];
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lamp[i] = scanner.nextInt();
        }
        Arrays.sort(lamp);


        for (int i = 0; i < lamp.length; i++) {
            // 处理特殊情况
            if ((i == 0 && lamp[i] != 0) || (i == lamp.length - 1 && lamp[i]!= l) ) {
                if(i == 0){
                    temp.add(lamp[i]);
                }
                if(i == lamp.length - 1){
                    temp.add(l - lamp[i]);
                }
            }
            if((i == 0 && lamp[i] == 0)){
                continue;
            }
            if((i == lamp.length - 1&& lamp[i]== l)){

            }

            // 一般情况
            temp.add(lamp[i] - lamp[i - 1]);
        }
    }
}

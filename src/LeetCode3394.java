import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeetCode3394 {
    // split consider in x-axis and y-axis, If the intervals intersect, merge
    // if one-axis can split in three part or above, means it is possible to make either two horizontal or two vertical cuts
    public boolean checkValidCuts(int n, int[][] rectangles) {
        // x-axis comparison
        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[0])); // important, after sort it can avoid complicate situation when merge interval
        List<int[]> xMerge = new ArrayList<>();
        for (int[] rectangle : rectangles) {
            if (xMerge.isEmpty()) {
                xMerge.add(new int[]{rectangle[0], rectangle[2]});
            } else {
                int[] last = xMerge.getLast();
                if (rectangle[0] < last[1]) {
                    last[1] = Math.max(last[1], rectangle[2]);
                } else {
                    xMerge.add(new int[]{rectangle[0], rectangle[2]});
                }
            }
        }
        if (xMerge.size() >= 3) {
            return true;
        }
        // y-axis comparison
        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[1]));
        List<int[]> yMerge = new ArrayList<>();
        for (int[] rectangle : rectangles) {
            if (yMerge.isEmpty()) {
                yMerge.add(new int[]{rectangle[1], rectangle[3]});
            } else {
                int[] last = yMerge.getLast();
                if (rectangle[1] < last[1]) {
                    last[1] = Math.max(last[1], rectangle[3]);
                } else {
                    yMerge.add(new int[]{rectangle[1], rectangle[3]});
                }
            }
        }
        return yMerge.size() >= 3;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] rectangle = {{0,2,2,4},{1,0,3,2},{2,2,3,4},{3,0,4,2},{3,2,4,4}};
        System.out.println(new LeetCode3394().checkValidCuts(n, rectangle));

    }
}

import java.util.*;

public class LeetCode3169 {
    // 暴力标记算法不可行，days<1e9，meetings.length<1e5, 遍历的话时间复杂度超了
//    public int countDays(int days, int[][] meetings) {
//        boolean[] meetingDays = new boolean[days + 1];
//        for (int[] meeting : meetings) {
//            for (int i = meeting[0]; i <= meeting[1]; i++) {
//                meetingDays[i] = true;
//            }
//        }
//        int result = 0;
//        for (int i = 1; i < meetingDays.length; i++) {
//            if (!meetingDays[i]) {
//                result++;
//            }
//        }
//        return result;
//    }

    // 先对meetings按开始时间进行排序，然后对其进行日期合并，然后计算会议时间
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0])); // 这一步排序是最为重要的一步，排序后，后续遍历就不需要考虑日期插在两个日期中间这种较难处理的问题
        List<int[]> merge = new ArrayList<>();
        for (int[] meeting : meetings) {
            if (merge.isEmpty()) {
                merge.add(meeting);
            } else {
                int[] last = merge.getLast();
                if (last[1] >= meeting[0]) {
                    last[1] = Math.max(last[1], meeting[1]);
                } else {
                    merge.add(meeting);
                }
            }
        }
        int meetingDays = 0;
        for (int[] meeting : merge) {
            meetingDays += (meeting[1] - meeting[0] + 1);
        }
        return days - meetingDays;
    }

    public static void main(String[] args) {
        int days = 14;
        int[][] meetings = {{6,11},{7,13},{8,9},{5,8},{3,13},{11,13},{1,3},{5,10},{8,13},{3,9}};
        System.out.println(new LeetCode3169().countDays(days, meetings));

    }
}

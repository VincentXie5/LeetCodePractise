public class LeetCode2874 {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long[] preMax = new long[n];
        long[] suffixMax = new long[n];
        preMax[0] = nums[0];
        suffixMax[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
            suffixMax[n - i - 1] = Math.max(suffixMax[n - i], nums[n - i - 1]);
        }
        long answer = 0;
        for (int i = 1; i < n - 1; i++) {
            answer = Math.max(answer, (preMax[i - 1] - nums[i]) * suffixMax[i + 1]);
        }
        return answer;
    }
}

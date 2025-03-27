import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCode2780 {
    // find the dominant num and the count of it, then calculate if it is dominant num of sub array
    public int minimumIndex(List<Integer> nums) {
        Map<Integer, Long> collect = nums.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Integer mostFrequent = collect.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).get();


        Long dominantNumCount = collect.get(mostFrequent);
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (mostFrequent.equals(nums.get(i))) {
                count++;
            }
            // this is the condition of dominant num of two sub array
            if (2 * count > i + 1 && (dominantNumCount - count) * 2 > nums.size() - i - 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};

        System.out.println(new LeetCode2780().minimumIndex(Arrays.stream(nums).boxed().collect(Collectors.toList())));
    }
}

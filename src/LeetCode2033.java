import java.util.Arrays;

public class LeetCode2033 {
    // flatten and sort the grid, commonly, min operations is make number equal to the midNumber of sorted array
    public int minOperations(int[][] grid, int x) {
        int[] array = Arrays.stream(grid).flatMapToInt(Arrays::stream).sorted().toArray();
        int midNumber = array[array.length/2];
        int result = 0;
        for (int number : array) {
            if (number % x != midNumber % x) {
                return -1;
            }
            result += Math.abs(number - midNumber) / x;
        }
        return result;
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode2503 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // create a graph[x][y] means the min number of grid[x][y] to be visited
    // then count graph[x][y] will be mean the maximum number of points can get
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] graphs = new int[m][n];
        for (int[] graph : graphs) {
            Arrays.fill(graph, Integer.MAX_VALUE);
        }
        graphs[0][0] = grid[0][0];
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        queue.offer(new int[]{graphs[0][0], 0, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int number = poll[0];
            int x = poll[1];
            int y = poll[2];
            if (number > graphs[x][y]) continue; // Skip if a better path is already found

            for (int[] dir : DIRECTIONS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int newMax = Math.max(number, grid[nx][ny]);
                    if (newMax < graphs[nx][ny]) {
                        graphs[nx][ny] = newMax;
                        queue.offer(new int[]{newMax, nx, ny});
                    }
                }
            }
        }
        // use bi search to increase speed
        int[] results = new int[queries.length];
//        List<Integer> flattenGraphs = Arrays.stream(graphs).flatMap(arr -> Arrays.stream(arr).boxed()).toList();
//        for (int i = 0; i < queries.length; i++) {
//            int query = queries[i];
//            results[i] = (int) flattenGraphs.stream().filter(x -> query >= x).count();
//        }
        List<Integer> flattenGraphs = Arrays.stream(graphs).flatMap(arr -> Arrays.stream(arr).boxed()).sorted().toList();
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            int left = 0;
            int right = flattenGraphs.size();
            while (right > left) {
                int mid = (right + left) / 2;
                if (flattenGraphs.get(mid) < query) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            results[i] = left;
        }

        return results;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,2,3},{2,5,7},{3,5,1}};
        int[] queries = {5,6,2};
        Arrays.stream(new LeetCode2503().maxPoints(grid, queries)).forEach(System.out::println);
    }

//    static class Location {
//        int x;
//        int y;
//
//        public Location(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public int getX() {
//            return x;
//        }
//
//        public int getY() {
//            return y;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (o == null || getClass() != o.getClass()) return false;
//            Location location = (Location) o;
//            return x == location.x && y == location.y;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(x, y);
//        }
//    }

    // Brutal search will time limit
//    public int[] maxPoints(int[][] grid, int[] queries) {
//        Set<Location> set = new HashSet<>();
//        Queue<Location> queue = new LinkedList<>();
//        Map<Integer, int[]> map =
//        int[] results = new int[queries.length];
//        for (int i = 0; i < queries.length; i++) {
//            set.add(new Location(0, 0));
//            queue.offer(new Location(0, 0));
//            int result = 0;
//            while (!queue.isEmpty()) {
//                Location poll = queue.poll();
//                if (queries[i] > grid[poll.getX()][poll.getY()]) {
//                    result++;
//                } else {
//                    continue;
//                }
//                if (poll.getX() - 1 >= 0) {
//                    Location up = new Location(poll.getX() - 1, poll.getY());
//                    if (!set.contains(up)) {
//                        queue.offer(up);
//                        set.add(up);
//                    }
//                }
//                if (poll.getX() + 1 < grid.length) {
//                    Location down = new Location(poll.getX() + 1, poll.getY());
//                    if (!set.contains(down)) {
//                        queue.offer(down);
//                        set.add(down);
//                    }
//                }
//                if (poll.getY() - 1 >= 0) {
//                    Location left = new Location(poll.getX(), poll.getY() - 1);
//                    if (!set.contains(left)) {
//                        queue.offer(left);
//                        set.add(left);
//                    }
//                }
//                if (poll.getY() + 1 < grid[0].length) {
//                    Location right = new Location(poll.getX(), poll.getY() + 1);
//                    if (!set.contains(right)) {
//                        queue.offer(right);
//                        set.add(right);
//                    }
//                }
//            }
//            results[i] = result;
//            set.clear();
//        }
//        return results;
//    }
}

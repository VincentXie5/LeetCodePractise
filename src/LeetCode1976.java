import java.util.*;

public class LeetCode1976 {
//    解题思路：
//    构建图：首先，我们需要将城市中的交叉口和道路构建成一个图。每个交叉口是图中的一个节点，每条道路是图中的一条边，边的权重是道路的通行时间。
//
//    Dijkstra算法：使用 Dijkstra 算法来找到从起点 0 到所有其他节点的最短路径。Dijkstra 算法是一种贪心算法，适用于非负权重的图。
//
//    记录路径数：在 Dijkstra 算法的过程中，我们不仅要记录每个节点的最短路径长度，还要记录有多少条不同的路径可以达到这个最短路径长度。
//
//    返回结果：最终，我们只需要返回从起点 0 到终点 n-1 的最短路径的路径数。
    public int countPaths(int n, int[][] roads) {
        int MOD = 1000000007;

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        long[] dist = new long[n]; // 表示从0点到其他点的最短距离
        int[] count = new int[n]; // 表示从0点到其他点的最短距离的路的条数
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        count[0] = 1;

        Queue<long[]> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a[1])); // 优先遍历刷新最短的路径，贪心算法
        queue.offer(new long[]{0, 0});
        while(!queue.isEmpty()) {
            long[] head = queue.poll();
            int currentPoint = (int) head[0];
            long currentDist = head[1];
            if (dist[currentPoint] < currentDist) {
                continue;
            }
            for (int[] pair : graph.get(currentPoint)) {
                int v = pair[0];
                int w = pair[1];
                if (dist[v] > dist[currentPoint] + w) {
                    dist[v] = dist[currentPoint] + w;
                    count[v] = count[currentPoint];
                    queue.offer(new long[]{v, dist[v]}); // 因为可能遍历其他的节点时，也会产生{v, dist[v]}， 传递这个就可以提前去除旧的长的队列元素，减少遍历次数
                } else if (dist[v] == dist[currentPoint] + w){
                    count[v] = (count[v] + count[currentPoint]) % MOD;
                }
            }
        }
        return count[n - 1];
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        System.out.println(new LeetCode1976().countPaths(n, roads));
    }
}

import java.util.HashMap;
import java.util.Map;

public class LeetCode2685 {
    // 染色法聚类的思想：有边连接的点都视为同一种颜色，然后计算同颜色的点和边的数量，因为这道题是全连接图，所以同颜色的点和边的数量满足n : n(n-1)/2
    // 满足的颜色就算是全连接图了，因为题目说给的边不会重复
    public int countCompleteComponents(int n, int[][] edges) {
        int[] colors = new int[n];
        Map<Integer, Integer> vCount = new HashMap<>();
        int color = 1;
        // 遍历边列表，给点染色，并给对应颜色计边的数量
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (colors[a] == 0 && colors[b] == 0) { // 这个边的两个点都未被染色，赋予颜色
                colors[a] = color;
                colors[b] = color;
                color++;
                vCount.put(colors[a], 1);
            } else if (colors[a] == 0 && colors[b] != 0) { // 有一点被染色，赋予同样的颜色
                colors[a] = colors[b];
                vCount.put(colors[a], vCount.get(colors[a]) + 1);
            } else if (colors[a] != 0 && colors[b] == 0) { // 有一点被染色，赋予同样的颜色
                colors[b] = colors[a];
                vCount.put(colors[a], vCount.get(colors[a]) + 1);
            } else if (colors[a] == colors[b]) { // 已经染过色了
                vCount.put(colors[a], vCount.get(colors[a]) + 1);
            } else { // 这条边把原来两种颜色连接了起来，需要将其变成同样的颜色
                int removeColor = colors[b];
                for (int i = 0; i < colors.length; i++) {
                    if (colors[i] == removeColor) {
                        colors[i] = colors[a];
                    }
                }
                vCount.put(colors[a], vCount.get(colors[a]) + vCount.get(removeColor) + 1);
                vCount.remove(removeColor);
            }
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : vCount.entrySet()) {
            int pointCount = 0; // 对应颜色的点数
            for (int c : colors) {
                if (c == entry.getKey()) {
                    pointCount++;
                }
            }
            // 判断是否满足全连接图的点边等式
            if (pointCount * (pointCount - 1) == 2 * entry.getValue()) {
                result++;
            }
        }
        // 孤立点也算一种全连接图
        for (int c : colors) {
            if (c == 0) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1,2},{3,4},{1,4},{2,3},{1,3},{2,4}};
        System.out.println(new LeetCode2685().countCompleteComponents(n, edges));
    }
}

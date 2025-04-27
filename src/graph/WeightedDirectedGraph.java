package graph;
import java.util.*;

public class WeightedDirectedGraph {
    private final Map<Integer, Map<Integer, Integer>> graph; // key: u -> value: (v -> weight)
    private final Map<Integer, Map<Integer, Integer>> distances; // key u -> value: (v -> shortestDistance else -1)
    private final Map<Integer, Integer> prev;

    public WeightedDirectedGraph() {
        graph = new HashMap<>();
        distances = new HashMap<>();
        prev = new HashMap<>();
    }

    public WeightedDirectedGraph(int[][] edges) {
        graph = new HashMap<>();
        distances = new HashMap<>();
        prev = new HashMap<>();
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];
            graph.computeIfAbsent(u, k -> new HashMap<>());
            graph.get(u).put(v, weight);
            distances.put(u, new HashMap<>());
            distances.get(u).put(u, 0);
            prev.put(u, u);
        }
    }

    public int shortestDistance(int u, int v) {
        if(!distances.containsKey(u)) {
            return -1;
        }
        PriorityQueue<int[]> distanceQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        distanceQueue.offer(new int[]{u, 0});
        Set<Integer> visited = new HashSet<>();
        while(!distanceQueue.isEmpty()) {
            int[] curPair = distanceQueue.poll();
            int curNode = curPair[0], curWeight = curPair[1];
            if(curNode == v) {
                System.out.println(shortestPathString(u, v));
                return curWeight;
            }
            visited.add(curNode);
            if(!graph.containsKey(curNode)) {
                continue;
            }
            for(int neighbor : graph.get(curNode).keySet()) {
                // System.out.println("curNode: " + curNode + " neighbor: " + neighbor);
                if(visited.contains(neighbor)) continue;
                int newDistance = distances.get(u).get(curNode) + graph.get(curNode).get(neighbor);
                if(!distances.get(u).containsKey(neighbor) || distances.get(u).get(neighbor) > newDistance) {
                    prev.put(neighbor, curNode);
                    distances.get(u).put(neighbor, newDistance);
                }  
                distanceQueue.add(new int[]{neighbor, newDistance});
            }
        }
        System.out.println("no such path exists between " + String.valueOf(u) + " and " + String.valueOf(v));
        return -1;
    }

    private String shortestPathString(int u, int v) {
        String baseString = "->" + v;
        while(prev.get(v) != u) {
            int node = prev.get(v);
            baseString = node + "->" + baseString;
        }
        return u + baseString;
    }
}

package graph;

import java.util.*;

public class TopologicalGraph {
    final Map<Integer, List<Integer>> neighbors;
    final Map<Integer, Integer> inDegree;

    public TopologicalGraph() {
        neighbors = new HashMap<>();
        inDegree = new HashMap<>();
    }
    public TopologicalGraph(int[][] edges) {
        neighbors = new HashMap<>();
        inDegree = new HashMap<>();
        for(int[] edge : edges) {
            int parent = edge[0], child = edge[1];
            inDegree.computeIfAbsent(parent, k -> 0);
            inDegree.put(child, inDegree.getOrDefault(child, 0) + 1);
            neighbors.computeIfAbsent(parent, k -> new ArrayList<>());
            neighbors.get(parent).add(child);
            neighbors.computeIfAbsent(child, k -> new ArrayList<>());
        }
    }

    public void topologicalSort() {
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        Queue<Integer> vertices = new LinkedList<>();
        List<Integer> sorted = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if(entry.getValue() == 0) {
                vertices.offer(entry.getKey());
            }
        }
        while(!vertices.isEmpty()) {
            int curVertice = vertices.poll();
            sorted.add(curVertice);
            for(int neighbor : neighbors.get(curVertice)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if(inDegree.get(neighbor) == 0) {
                    vertices.add(neighbor);
                }
            }
        }

        if (sorted.size() != inDegree.size()) {
            System.out.println("Cycle detected, no valid topological order.");
        }

        else {
            System.out.println(sorted.toString());
        }
    }
}

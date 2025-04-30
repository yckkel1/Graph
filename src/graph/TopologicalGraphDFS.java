package graph;
import java.util.*;

public class TopologicalGraphDFS {
    final Map<Integer, Set<Integer>> graph;

    public TopologicalGraphDFS() {
        graph = new HashMap<>();
    }

    public TopologicalGraphDFS(int[][] edges) {
        graph = new HashMap<>();
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, k->new HashSet<>());
            graph.get(u).add(v);
            graph.computeIfAbsent(v, k->new HashSet<>());
        }
    }

    public void topologicalSort() {
        List<Integer> sorted = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();
        for(int v : graph.keySet()) {
            if(!sortHelper(visited, sorted, recursionStack, v)) {
                System.out.println("cycle detected. Exiting sorting");
                return;
            }
        }
        
        Collections.reverse(sorted);
        System.out.println(sorted.toString());
    }

    private boolean sortHelper(Set<Integer> visited, List<Integer> sorted, Set<Integer> recursionStack, int v) {
        if(recursionStack.contains(v)) return false;
        if(visited.contains(v)) return true;
        recursionStack.add(v);
        visited.add(v);
        for(int neighbor : graph.get(v)) {
            if(!sortHelper(visited, sorted, recursionStack, neighbor)) return false;
        }
        recursionStack.remove(v);
        sorted.add(v);
        return true;
    }
}

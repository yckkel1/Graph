package graph;
import java.util.*;

public class UnionFind {
    final Map<Integer, Integer> root;
    boolean hasCycle = false;
    int mst = 0;

    public UnionFind() {
        root = new HashMap<>();
    }
    
    public UnionFind(int[][] edges, int n) {
        root = new HashMap<>();
        for(int i=1; i<=n; i++) {
            root.put(i, i);
        }

        processEdges(edges);
    }

    public void addNode(int z, int[][] newEdges) {
        root.put(z, z);
        processEdges(newEdges);
    }

    public boolean isConnected(int x, int y) {
        if(!root.containsKey(x) || !root.containsKey(y)) {
            System.out.println("invalid input");
            return false;
        }
        return find(x) == find(y);
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public int processEdges(int[][] edges) {
        PriorityQueue<int[]> edgesByAscendingWeights = new PriorityQueue<>(Comparator.comparingInt(edge -> edge[2]));
        for(int[] edge : edges) {
            edgesByAscendingWeights.offer(edge);
        }

        while(!edgesByAscendingWeights.isEmpty()) {
            int[] curEdge = edgesByAscendingWeights.poll();
            int u = curEdge[0], v = curEdge[1], weight = curEdge[2];
            union(u, v, weight);
        }

        return mst;
    }

    private int find(int v) {
        if(root.get(v) != v) {
            root.put(v, find(root.get(v)));
        }
        return root.get(v);
    }

    private void union(int x, int y, int weight) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) {
            hasCycle = true;
            return;
        }

        if(rootX != rootY) {
            root.put(rootX, rootY);
            mst += weight;
        }
    }
}

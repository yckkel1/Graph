package graph;
import java.util.*;

public class UnionFind {
    final Map<Integer, Integer> root;
    boolean hasCycle = false;

    public UnionFind() {
        root = new HashMap<>();
    }
    
    public UnionFind(int[][] edges, int n) {
        root = new HashMap<>();
        for(int i=1; i<=n; i++) {
            root.put(i, i);
        }
        for(int[] edge : edges) {
            union(edge[0], edge[1]);
        }
    }

    public void addNode(int z, int[][] newEdges) {
        root.put(z, z);
        for(int[] newEdge : newEdges) {
            union(newEdge[0], newEdge[1]);
        }
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

    private int find(int v) {
        if(root.get(v) != v) {
            root.put(v, find(root.get(v)));
        }
        return root.get(v);
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) {
            hasCycle = true;
            return;
        }

        if(rootX != rootY) {
            root.put(rootX, rootY);
        }
    }
}

import graph.TopologicalGraph;
import graph.TopologicalGraphDFS;
import graph.WeightedDirectedGraph;

public class App {
    public static void main(String[] args) throws Exception {
        // WeightedDirectedGraph graph1 = new WeightedDirectedGraph(new int[][]{{1,2,5}, {2,3,7}, {1,3,10}, {2,4,15}});
        // graph1.shortestDistance(1, 3);

        // graph1.shortestDistance(1, 5);
        // graph1.shortestDistance(2, 3);
        // graph1.shortestDistance(1, 3);
        
        // int[][] edges = new int[][]{{5,0}, {5,2}, {4,0}, {4,1}, {2,1}};
        // // TopologicalGraph graph = new TopologicalGraph(edges);
        // // graph.topologicalSort();

        // TopologicalGraphDFS dfsGraph = new TopologicalGraphDFS(edges);
        // dfsGraph.topologicalSort();

        int[][] edges2 = new int[][]{{5,0}, {5,2}, {4,0}, {4,1}, {2,1}, {0,4}};
        TopologicalGraphDFS dfsGraph2 = new TopologicalGraphDFS(edges2);
        dfsGraph2.topologicalSort();
    }
}

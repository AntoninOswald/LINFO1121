package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * You are asked to implement the ConnectedComponent class given a graph.
 * The Graph class available in the code is the one of the Java class API.
 * <p>
 * public class ConnectedComponents {
 * <p>
 * public static int numberOfConnectedComponents(Graph g){
 * // TODO
 * return 0;
 * }
 * }
 * Hint: Feel free to add methods or even inner-class (private static class)
 *       to help you but don't change the class name or the signature of the numberOfConnectedComponents method.
 *
 *
 */
public class ConnectedComponents {
    private static int count = 0;
    private static boolean[] marked;



    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g) {

        marked = new boolean[g.V()];
        count = 0;
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]){

                dfs(g,s);
                count++;
            }
        }

        return count;


    }

    private static void dfs(Graph g, int s){

        marked[s] = true;
        for (int v : g.adj(s)){
            if (!marked[v]){
                dfs(g,v);
            }
        }
    }

    static class Graph {

        private List<Integer>[] edges;

        public Graph(int nbNodes)
        {
            this.edges = (ArrayList<Integer>[]) new ArrayList[nbNodes];
            for (int i = 0;i < edges.length;i++)
            {
                edges[i] = new ArrayList<>();
            }
        }

        /**
         * @return the number of vertices
         */
        public int V() {
            return edges.length;
        }

        /**
         * @return the number of edges
         */
        public int E() {
            int count = 0;
            for (List<Integer> bag : edges) {
                count += bag.size();
            }

            return count/2;
        }

        /**
         * Add edge v-w to this graph
         */
        public void addEdge(int v, int w) {
            edges[v].add(w);
            edges[w].add(v);
        }

        /**
         * @return the vertices adjacent to v
         */
        public Iterable<Integer> adj(int v) {
            return edges[v];
        }
    }

}

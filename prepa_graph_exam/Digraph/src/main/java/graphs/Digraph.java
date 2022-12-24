package graphs;

import java.util.ArrayList;

/**
 * Implement the Digraph.java interface in
 * the Digraph.java class using an adjacency list
 * data structure to represent directed graphs.
 */
public class Digraph {

    private final int V;
    private int E;
    private ArrayList<ArrayList> adj;



    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList<ArrayList>(V);
        for (int v = 0; v < V; v++) {
            adj.add(new ArrayList<>());
        }
    }

    /**
     * The number of vertices
     */
    public int V() {
        return V;


    }

    /**
     * The number of edges
     */
    public int E() {
        return E;

    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        ArrayList nodes = this.adj.get(v);
        nodes.add(w);
        E++;
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {

        return adj.get(v);

    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse() {
        Digraph reversed = new Digraph(V);
        for (int i = 0; i < V(); i++) {
            ArrayList nodes = (ArrayList) adj(i);
            for (Object node : nodes) {

                reversed.addEdge((int)node,i);
            }
        }

        return reversed;
    }

}

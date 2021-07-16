package DirectedAcyclicGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class topologicalDFS {

    static class Graph{
        int V;
        ArrayList<Integer>[] list;

        public Graph(int v){
            V = v;
            list = new ArrayList[v];
            for(int i = 0; i < v; i++){
                list[i] = new ArrayList<>();
            }
        }

        void addEdge(int i, int j){
            list[i].add(j);
        }

        void dfs(int node, boolean[] visited, ArrayList<Integer> ordering){

            visited[node] = true;

            for(int nbr : list[node]){
                if(!visited[nbr]){
                    dfs(nbr,visited,ordering);
                }
            }
            //at this point
            ordering.add( node);
        }

        //Complete this method
        void dfs_topological_sort() {

            boolean[] visited = new boolean[V];
            ArrayList<Integer> ordering = new ArrayList<>();

            //we call dfs from every node if it not visited
            for(int i=0;i<V;i++){
                if(!visited[i]){
                    dfs(i,visited,ordering);
                }
            }

            Collections.reverse(ordering);
            //finaly print the ordeirng
            for(int node:ordering){
                System.out.print(node+" ");
            }
            System.out.println();


        }
    }

    public static void main(String[] args){
        Graph g = new Graph(6);
        g.addEdge(0, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(1, 4);
        g.addEdge(1, 2);

        g.dfs_topological_sort();

    }
}

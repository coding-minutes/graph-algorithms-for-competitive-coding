package CycleDetection;

import java.util.ArrayList;

public class CycleDetectionDirected {

    static class Graph {
        int V;
        ArrayList<Integer>[] list;

        public Graph(int v) {
            V = v;
            list = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                list[i] = new ArrayList<>();
            }
        }

        void addEdge(int i, int j) {
            list[i].add(j);
        }

        boolean dfs(int node,boolean[] visited, boolean[] stack){
            //arrive at node
            visited[node] = true;
            stack[node] = true;

            //do some work at node,return true if backedge is found here itself
            for(int nbr : list[node]){

                if(stack[nbr]){
                    return true;
                }
                else if(!visited[nbr]){
                    boolean nbrFoundACycle = dfs(nbr,visited,stack);
                    if(nbrFoundACycle){
                        return true;
                    }
                }
            }

            //going back
            stack[node] = false;
            return false;
        }

        boolean contains_cycle(){
            boolean[] visited = new boolean[V];
            boolean[] stack = new boolean[V];

            for(int i=0;i<V;i++){
                if(!visited[i]){
                    if(dfs(i,visited,stack)){
                        return true;
                    }
                }

            }
            return false;
        }

    }

    public static void main(String[] args){

        Graph g = new Graph(3);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,0);

        System.out.println(g.contains_cycle());

    }

}


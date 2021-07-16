package CycleDetection;

import java.util.ArrayList;

public class CycleDetectionUndirected {

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
            list[j].add(i);
        }

        boolean dfs(int node, boolean[] visited, int parent){
            //mark that node visited
            visited[node] = true;

            for(int nbr : list[node]){

                if(!visited[nbr]){

                    boolean nbrFoundACycle = dfs(nbr,visited,node);
                    if(nbrFoundACycle){
                        return true;
                    }
                }
                //nbr is visited and its not the parent of current node in the current dfs call
                else if(nbr!=parent){
                    return true;
                }
            }
            return false;
        }

        boolean contains_cycle(){
            //Graph is single component
            boolean[] visited = new boolean[V];
            return dfs(0, visited, -1);
        }

    }

    public static void main(String[] args){

        Graph g = new Graph(3);
//        g.addEdge(0,1);
        g.addEdge(2,0);
        g.addEdge(2,0);

        System.out.println(g.contains_cycle());

    }

}


package DepthFirstSearch;

import BreadthFirstSearch.Bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class dfs {
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

        void addEdge(int i, int j, boolean unDirected){
            list[i].add(j);
            if(unDirected)
                list[j].add(i);
        }

        private void dfsHelper(int source, boolean[] visited){
            visited[source] = true;
            System.out.print(source + " ");

            for(int nbr: list[source]){
                if(!visited[nbr]){
                    dfsHelper(nbr, visited);
                }
            }
        }

        void dfs(int source){
            boolean[] visited = new boolean[V];
            Arrays.fill(visited, false);
            dfsHelper(source, visited);
        }


    }

    public static void main(String[] args){

        Graph g = new Graph(7);
        g.addEdge(0,1, true);
        g.addEdge(1,2, true);
        g.addEdge(2,3, true);
        g.addEdge(3,5, true);
        g.addEdge(5,6, true);
        g.addEdge(4,5, true);
        g.addEdge(0,4, true);
        g.addEdge(3,4, true);
        g.dfs(1);

    }
}

package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BfsShortestPath {

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

        void bfs(int source,int dest){

            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[V];
            Arrays.fill(visited, false);

            int[] dist = new int[V];
            int[] parent = new int[V];

            for(int i=0;i<V;i++){
                parent[i] = -1;
                dist[i] = 0;
            }

            q.add(source);
            visited[source] = true;
            parent[source] = source;
            dist[source] = 0;

            while(!q.isEmpty()){
                //Do some work for every node
                int f = q.poll();

                //PUsh the nbrs of current node inside q if they are not already visited
                for(int nbr : list[f]){
                    if(!visited[nbr]){
                        q.add(nbr);
                        //parent and dist
                        parent[nbr] = f;
                        dist[nbr] = dist[f] + 1;
                        visited[nbr] = true;
                    }
                }
            }
            //print the shortest distance
            for(int i=0;i<V;i++){
                System.out.println("Shortest dist to " + i + " is " + dist[i]);
            }


            //print the path from a source to any dest
            if(dest!=-1){
                int temp = dest;
                while(temp!=source){
                    System.out.print(temp + " -- ");
                    temp = parent[temp];
                }
                System.out.println(source);
            }

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
        g.bfs(1,6);
    }

}

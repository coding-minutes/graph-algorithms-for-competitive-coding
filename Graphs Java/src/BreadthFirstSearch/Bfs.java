package BreadthFirstSearch;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs {


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

        void bfs(int source){

            Queue<Integer> q = new LinkedList<>();
            Boolean[] visited = new Boolean[V];
            Arrays.fill(visited, false);

            q.add(source);
            visited[source] = true;

            while(!q.isEmpty()){
                //Do some work for every node
                int f = q.poll();
                System.out.println(f);
//            q.pop();

                //PUsh the nbrs of current node inside q if they are not already visited
                for(int nbr : list[f]){
                    if(!visited[nbr]){
                        q.add(nbr);
                        visited[nbr] = true;
                    }
                }
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
        g.bfs(1);

    }
}

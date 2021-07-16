package DirectedAcyclicGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class topologicalSort {

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

        void addEdge(int i, int j, boolean bidir){
            list[i].add(j);
            if(bidir){
                list[j].add(i);
            }
        }

        void bfs(int s){
            Queue<Integer> q = new LinkedList<>();
            q.add(s);


            int[] dist = new int[V];
            //Init all nodes with inf dist
            for(int i=0;i<V;i++){
                dist[i] = Integer.MAX_VALUE;
            }
            dist[s] = 0;

            while(!q.isEmpty()){
                int node  = q.poll();

                for(int child:list[node]){
                    if(dist[child]==Integer.MAX_VALUE){
                        q.add(child);
                        dist[child] = dist[node] + 1;
                    }
                }
            }

            //Print the dist of all nodes
            for(int i=0;i<V;i++){
                System.out.println("dist["+i+"]"+"="+dist[i]);
            }

        }

        void topologicalSort(){
            int[] indegree  = new int[V];

            //Init All Indegrees
            for(int i=0;i<V;i++){
                for(int child:list[i]){
                    indegree[child]++;
                }
            }

            //Find all nodes with 0 indegrees
            Queue<Integer> q = new LinkedList<>();
            for(int i=0;i<V;i++){
                if(indegree[i]==0)
                    q.add(i);
            }

            //Start removing elements from the queue
            while(!q.isEmpty()){
                int node = q.poll();
                System.out.print(node + " ");

                for(int child:list[node]){
                    indegree[child]--;
                    if(indegree[child]==0){
                        q.add(child);
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        Graph g = new Graph(7);

        g.addEdge(0,2,false);
        g.addEdge(1,6,false);
        g.addEdge(2,6,false);
        g.addEdge(2,3,false);
        g.addEdge(3,5,false);
        g.addEdge(6,5,false);

        g.topologicalSort();

    }
}

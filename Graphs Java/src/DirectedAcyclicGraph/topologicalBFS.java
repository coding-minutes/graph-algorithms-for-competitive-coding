package DirectedAcyclicGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class topologicalBFS {

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

        //Complete this method
        void topological_sort() {
            int[] indegree = new int[V];

            //Iterate over all the edges to find the right indegree
            for(int i=0;i<V;i++){

                for(int nbr : list[i]){
                    indegree[nbr]++;
                }
            }

            //bfs
            Queue<Integer> q = new LinkedList<>();
            //init the q with nodes having 0 indegree
            for(int i=0;i<V;i++){
                if(indegree[i]==0){
                    q.add(i);
                }
            }

            //start popping
            while(!q.isEmpty()){
                int node = q.poll();
                System.out.print(node +  " ");


                //iterate over the nbrs of this node and reducec their indegree by 1
                for(int nbr : list[node]){
                    indegree[nbr]--;
                    if(indegree[nbr]==0){
                        q.add(nbr);
                    }
                }
            }
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

        g.topological_sort();

    }
}

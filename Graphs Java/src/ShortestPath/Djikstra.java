package ShortestPath;


import java.util.*;

public class Djikstra  {

    static class Pair implements Comparable<Pair>{
        int first, second;

        Pair(int a,int b){
            first = a;
            second = b;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.first != o.first)
                return this.first - o.first;
            return this.second - o.second;
        }
    }


    static class Graph{

        int V;
        List<Pair>[] l;


        public Graph(int v){
            V = v;
            l = new ArrayList[V];
            for(int i = 0 ;i < V; i++){
                l[i] = new ArrayList<>();
            }
        }

        void addEdge(int u,int v,int wt, boolean undir){

            l[u].add(new Pair(wt,v));
            if(undir){
                l[v].add(new Pair(wt,u));
            }
        }

        int dijkstra(int src,int dest){

            //Data Structures
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);

            SortedSet<Pair> s = new TreeSet<>();

            //1. Init
            dist[src] = 0;
            s.add(new Pair(0,src));

            while(!s.isEmpty()){

                Pair it = s.first();
                int node = it.second;
                int distTillNow = it.first;
                s.remove(it); //Pop

                //Iterate over the nbrs of node
                for(Pair nbrPair : l[node]){
                    //......

                    int nbr = nbrPair.second;
                    int currentEdge = nbrPair.first;

                    if(distTillNow + currentEdge < dist[nbr]){
                        //remove if nbr already exist in the set

                        s.remove(new Pair(dist[nbr], nbr));


                        //insert the updated value with the new dist
                        dist[nbr] = distTillNow + currentEdge;
                        s.add(new Pair(dist[nbr],nbr));

                    }

                }

            }
            //Single Source Shortest Dist to all other nodes
            for(int i=0;i<V;i++){
                System.out.println("Node i "+i +" Dist "+dist[i]);
            }
            return dist[dest];

        }

    }
    public  static void main(String[] args){

        Graph g = new Graph(5);
        g.addEdge(0,1,1, true);
        g.addEdge(1,2,1, true);
        g.addEdge(0,2,4, true);
        g.addEdge(0,3,7, true);
        g.addEdge(3,2,2, true);
        g.addEdge(3,4,3, true);

        System.out.println( g.dijkstra(0,4));
    }
}

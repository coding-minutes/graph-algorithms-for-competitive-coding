package MinimumSpanningTree;

import java.util.*;

public class Prims {

    static class Pair{
        public int first, second;
        public Pair(int a, int b){
            first = a;
            second = b;
        }
    }

    static class Graph{

        //Adjacency. List
        ArrayList<Pair>[] l;
        int V;


        public Graph(int n){
            V = n;
            l = new ArrayList[n];
            for(int i = 0; i < n; i++){
                l[i] = new ArrayList<>();
            }
        }

        void addEdge(int x,int y,int w){
            l[x].add(new Pair(y,w));
            l[y].add(new Pair(x,w));
        }

        int prim_mst(){

            Queue<Pair> Q = new PriorityQueue<>(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o1.first - o2.first;
                }

            });

//

            //another array
            //visited array that denotes whether a node has been included in MST or Not
            boolean[] vis = new boolean[V];
            int ans = 0;

            //begin
            Q.add(new Pair(0,0)); // weight, node

            while(!Q.isEmpty()){
                //pick out the edge with min weight
                Pair best = Q.poll();

                int to = best.second;
                int weight = best.first;

                if(vis[to]){
                    //discard the edge, and continue
                    continue;
                }

                //otherwise take the current edge
                ans += weight;
                vis[to] = true;

                //add the new edges in the queue
                for(Pair x:l[to]){
                    if(!vis[x.first]){
                        Q.add(new Pair(x.second,x.first));
                    }
                }
            }
            return ans;
        }

    };

    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(),m = scn.nextInt();

        Graph g =  new Graph(n);
        for(int i=0;i<m;i++){
            int x = scn.nextInt(),y= scn.nextInt(),w= scn.nextInt();

            g.addEdge(x-1,y-1,w);
        }

        System.out.println(g.prim_mst());



    }

}

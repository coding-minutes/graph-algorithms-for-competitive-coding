package DisjointSetUnion;

import java.util.ArrayList;
import java.util.List;

public class DSU_1 {

    static class Pair{
        public int first, second;
        public Pair(int a, int b){
            first = a;
            second = b;
        }
    }


    static class Graph{
        int V;
        List<Pair> l;


        public Graph(int V){
            this.V = V;
            this.l = new ArrayList<>();
        }

        void addEdge(int u,int v){
            l.add(new Pair(u,v));
        }
        // Find
        int findSet(int i,int parent[]){
            //base case
            if(parent[i]==-1){
                return i;
            }

            return findSet(parent[i],parent);
        }


        //Union
        void union_set(int x,int y,int parent[]){
            int s1 = findSet(x,parent);
            int s2 = findSet(y,parent);

            if(s1!=s2){
                parent[s1] = s2;
            }
        }


        boolean contains_cycle(){
            //DSU Logic to check if graph contains cycle or not
            int parent[] = new int[V];
            for(int i=0;i<V;i++){
                parent[i] = -1;
            }

            //iterate over the edge list
            for(Pair edge : l){
                int i = edge.first;
                int j = edge.second;

                int s1 = findSet(i,parent);
                int s2 = findSet(j,parent);

                if(s1!=s2){
                    union_set(s1,s2,parent);
                }
                else{
                    System.out.println("Same parents"+s1+" and "+s2);
                    return true;
                }

            }
            return false;
        }
    }

    public static void main(String[] args){
        Graph g = new Graph(4);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(3,0);

        System.out.print(g.contains_cycle());
    }
}

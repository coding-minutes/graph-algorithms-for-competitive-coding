package DisjointSetUnion;

import java.util.ArrayList;
import java.util.List;

public class DSUrankPathCompression {

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
            if(parent[i]==-1){
                return i;
            }
            int p = findSet(parent[i],parent);
            //compress the path for next time
            parent[i] = p;
            return p;

        }


        //Union
        void union_set(int u,int v,int rank[],int parent[]){

            int p1 = findSet(u,parent);
            int p2 = findSet(v,parent);

            if(p1!=p2){
                if(rank[p1]<rank[p2]){
                    parent[p1] = p2;
                    rank[p2] += rank[p1];
                }
                else{
                    parent[p2] = p1;
                    rank[p1] += rank[p2];
                }
            }

            //print parent array after every union
            System.out.print("Parent");
            for(int k=0;k<V;k++){
                System.out.print(parent[k] + " ");
            }
            System.out.println();
            System.out.print("Rank");
            for(int k=0;k<V;k++){
                System.out.print(rank[k] + " ");
            }
            System.out.println();
        }

        boolean contains_cycle(){
            int[] parent = new int[V];
            int[] rank = new int[V];
            for(int i=0;i<V;i++){
                parent[i] = -1;
                rank[i] = 1;
            }

            for(Pair edge:l){
                int i = edge.first;
                int j = edge.second;

                int p1 = findSet(i,parent);
                int p2 = findSet(j,parent);
                System.out.println(i+"-"+p1+" and "+j+"-"+p2);

                if(p1!=p2){
                    union_set(p1,p2,rank,parent);

                }
                else{
                    //belong to same set
                    System.out.print("Parent");
                    for(int k=0;k<V;k++){
                        System.out.print(parent[k] + " ");
                    }


                    return true;
                }

            }
            return false;



        }
    }

    public static void main(String[] args){
        Graph g = new Graph(7);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(0,4);

        g.addEdge(5,6);
        g.addEdge(2,5);
        g.addEdge(2,6);



        System.out.print(g.contains_cycle());
    }
}

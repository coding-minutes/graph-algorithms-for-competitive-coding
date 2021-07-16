package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Kruskal {

    static class DSU{
        int[] parent, rank;

        public DSU(int n){
            parent = new int[n];
            rank = new int[n];

            //parent -1, rank = 1
            for(int i=0;i<n;i++){
                parent[i] = -1;
                rank[i] = 1;
            }
        }

        int find(int i){
            //base case
            if(parent[i]==-1){
                return i;
            }
            //otherwise
            return parent[i] = find(parent[i]);
        }

        void unite(int x,int y){
            int s1 = find(x);
            int s2 = find(y);

            if(s1!=s2){
                //union by rank
                if(rank[s1]<rank[s2]){
                    parent[s1] = s2;
                    rank[s2] += rank[s1];
                }
                else{
                    parent[s2] = s1;
                    rank[s1] += rank[s2];
                }
            }
        }


    }

    static class Graph{
        ArrayList<ArrayList<Integer>> edgeList;
        int V;

        public Graph(int V) {
            this.V = V;
            edgeList = new ArrayList<>();
        }

        void addEdge(int x,int y,int w){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(w);
            list.add(x);
            list.add(y);
            edgeList.add(list);
        }

        int kruskal_mst(){
            //Main Logic = Easy!!!
            //1. Sort all the edges based upon weight
            edgeList.sort(new Comparator<ArrayList<Integer>>() {
                @Override
                public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                    return o1.get(0) - o2.get(0);
                }
            });

            //Init a DSU
            DSU s = new DSU(V);

            int ans = 0;
            for(List<Integer> edge : edgeList){

                int w = edge.get(0);
                int x = edge.get(1);
                int y = edge.get(2);

                //take that edge in MST if it doesnt form a cycle
                if(s.find(x)!=s.find(y)){
                    s.unite(x,y);
                    ans += w;
                }

            }
            return ans;
        }


    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(),m = scn.nextInt();


        Graph g =  new Graph(n);
        for(int i=0;i<m;i++){
            int x = scn.nextInt(),y= scn.nextInt(),w= scn.nextInt();

            g.addEdge(x-1,y-1,w);
        }

        System.out.println(g.kruskal_mst());
    }

}


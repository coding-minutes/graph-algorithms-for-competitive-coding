package ShortestPath;

import java.util.Arrays;
import java.util.Scanner;

public class bellmanFord {

    static int[] bellman_ford(int V,int src,int[][] edges){

        //create a vector
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;

        //relax all edges v-1 times
        for(int i=0;i<V-1;i++){

            for(int[] edge : edges){
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if(dist[u]!=Integer.MAX_VALUE && dist[u] + wt < dist[v]){
                    dist[v] = dist[u] + wt;
                }
            }
        }
        // negative wt cycle
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if(dist[u]!=Integer.MAX_VALUE && dist[u] + wt < dist[v]){
                System.out.println("negative Wt cycle found");
                System.exit(0);
            }

        }
        return dist;
    }

    public static void main(String[]  args){



        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(),m = scn.nextInt();


        //edge list
        int[][] edges = new int[m][3];       // (a,b,3) (c,d,5) ....

        for(int i=0;i<m;i++){
            int u = scn.nextInt(),v = scn.nextInt(),wt = scn.nextInt();

            edges[i] = new int[]{u, v, wt};
        }
        //bellman algorithm

        int[] distances = bellman_ford(n,1,edges);

        for(int i=1;i<=n;i++){
            System.out.println("Node "+i+" is at dist "+distances[i]);
        }

    }
}







package CycleDetection;

import java.util.ArrayList;
import java.util.Scanner;

public class Bipartite {

    static boolean dfs_helper(ArrayList<Integer>[] graph, int node, int[] visited, int parent, int color){
        //come to node
        visited[node] = color; //1 or 2, both mean visited

        for(int nbr : graph[node]){

            if(visited[nbr]==0){
                boolean subProb = dfs_helper(graph,nbr,visited,node,3-color);

                if(!subProb){
                    return false;
                }

            }
            else if(nbr!=parent && color==visited[nbr]){
                return false;
            }

        }
        return true;
    }


    static boolean dfs(ArrayList<Integer>[] graph, int N){

        int[] visited = new int[N];  // 0- Not Visited, 1 - Visited Color is 1, 2 - Visted Color 2

        int color = 1;
        boolean ans = dfs_helper(graph,0,visited,-1,color);
        //later one

        //colors
        for(int i=0;i<N;i++){
            System.out.println(i + " - Color " + visited[i]);
        }

        return ans;
    }

    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt(), M = scn.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        while(M-- > 0){
            int x = scn.nextInt(), y = scn.nextInt();

            graph[x].add(y);
            graph[y].add(x);

        }

        //BFS or DFS, by coloring the nodes at each step (current node has color 1, nbr should have a color 2)
        if(dfs(graph,N)){
            System.out.println("Yes its bipartite");
        }
        else{
            System.out.println("Not bipartite");
        }


    }
}

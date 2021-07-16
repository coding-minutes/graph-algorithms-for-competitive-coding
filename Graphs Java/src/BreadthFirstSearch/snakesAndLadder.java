package BreadthFirstSearch;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
    private int first;
    private int second;

    public int getFirst(){
        return first;
    }

    public int getSecond(){
        return second;
    }

    public void setFirst(int num){
        first = num;
    }
    public void setSecond(int num) {
        second = num;
    }
}

public class snakesAndLadder {

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

        int minCostBFS(int src, int dest){
            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[V];
            int[] dist = new int[V];
            Arrays.fill(visited, false);

            q.add(src);
            visited[src] = true;
            dist[src] = 0;

            while(!q.isEmpty()){
                int f = q.poll();

                for(int nbr : list[f]){
                    if(!visited[nbr]){
                        q.add(nbr);
                        visited[nbr] = true;
                        dist[nbr] = dist[f] + 1;
                    }
                }
            }
            return dist[dest];
        }


    }

    public static int min_dice_throws(int n, ArrayList<Pair> snakes, ArrayList<Pair> ladders){
        int[] board = new int[n+1];

        //board to graph conversion
        for(Pair sp: snakes){
            int s = sp.getFirst();
            int e = sp.getSecond();
            board[s] = e - s;
        }

        for(Pair lp: ladders){
            int s = lp.getFirst();
            int e = lp.getSecond();
            board[s] = e - s;
        }

        //Graph
        Graph g = new Graph(n+1);
        for(int u=1;u<n;u++){
            for(int dice=1;dice<=6;dice++){
                int v = u + dice;
                v += board[v];
                if(v<=n){
                    g.addEdge(u,v);
                }
            }

        }
        return g.minCostBFS(1,n);
    }

}

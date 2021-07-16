package ShortestPath;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class ShortestPathGrid {

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            if(o.dist != this.dist)
                return this.dist - o.dist;
            if(o.x != this.x){
                return this.x - o.x;
            }

            return o.y - this.y;
        }
    }



    static int shortest_path(int[][] grid){
        //return the shortest path len

        int m = grid.length;
        int n = grid[0].length;

        int i = 0;
        int j = 0;

        //2D vector to denote of each cell
        int[][] dist = new int[m+1][n+1] ;
        for(int[] v: dist){
            Arrays.fill(v, Integer.MAX_VALUE);
        }

        dist[i][j] = grid[0][0];
        SortedSet<Node> s = new TreeSet<>();

        s.add(new Node(0,0,dist[0][0]));

        int dx[] = {0,0,1,-1};
        int dy[] = {1,-1,0,0};


        while(!s.isEmpty()){

            Node it = s.first();
            int cx = it.x;
            int cy = it.y;
            int cd = it.dist;
            s.remove(it);

            //update nbrs
            for(int k=0;k<4;k++){
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                if(nx>=0 && nx<m && ny>=0 && ny<n && cd + grid[nx][ny] < dist[nx][ny]){
                    //remove the old node if it exist
                    Node temp = new Node(nx,ny,dist[nx][ny]);
                    s.remove(temp);

                    //insert the new node in the set
                    int nd = grid[nx][ny] + cd;
                    dist[nx][ny] = nd;
                    s.add(new Node(nx,ny,nd));
                }

            }

        }
        return dist[m-1][n-1];

    }

    public static void main(String[] args){

        int[][] grid = {{1,2,3,4},
                        {2,1,3,4},
                        {1,1,1,1},
                        {1,3,4,7},
                        {1,2,3,4}};
        System.out.println(shortest_path(grid));

    }


}


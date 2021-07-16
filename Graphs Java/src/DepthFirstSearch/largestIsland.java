package DepthFirstSearch;


import java.util.Arrays;

public class largestIsland {

    static int dfs(int[][] matrix, boolean[][] visited, int i,int j,int m,int n){

        visited[i][j] = true;

        int cs = 1;

        int dx[] = {1,-1,0,0};
        int dy[] = {0,0,1,-1};

        for(int k=0;k<4;k++){
            int nx = i + dx[k];
            int ny = j + dy[k];

            if(nx>=0 && nx<m && ny>=0 && ny<n && matrix[nx][ny]==1 && !visited[nx][ny]){
                int subcomponent = dfs(matrix,visited,nx,ny,m,n);
                cs += subcomponent;
            }
        }
        return cs;
    }




    static int largest_island(int[][] matrix){
        //return the size of largest island in grid
        int m = matrix.length;
        int n = matrix[0].length;

        //visited matrix
        boolean[][] visited = new boolean[n][m];

        int largest = 0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j] && matrix[i][j]==1){

                    int size = dfs(matrix,visited,i,j,m,n);
                    if(size>largest){
                        largest = size;
                    }

                }

            }
        }
        return largest;
    }

    public static void main(String[] args){

        int[][] grid = {
                            {1, 0, 0, 1, 0},
                            {1, 0, 1, 0, 0},
                            {0, 0, 1, 0, 1},
                            {1, 0, 1, 1, 1},
                            {1, 0, 1, 1, 0}
                            };
        System.out.println(largest_island(grid));

    }
}


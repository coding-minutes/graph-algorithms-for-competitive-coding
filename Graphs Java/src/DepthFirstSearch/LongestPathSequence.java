package DepthFirstSearch;

public class LongestPathSequence {
    static void dfs(int[][] matrix,boolean[][] visited, int[][] cache, int i,int j,int m,int n){

        visited[i][j] = true;

        int dx[] = {-1,1,0,0};
        int dy[] = {0,0,1,-1};

        int cnt = 0;
        for(int k=0;k<4;k++){
            int nx = i + dx[k];
            int ny = j + dy[k];

            if(nx>=0 && ny>=0 && nx<m && ny<n && matrix[nx][ny]>matrix[i][j]){
                if(visited[nx][ny]){
                    cnt = Math.max(cnt,1+cache[nx][ny]);
                }
                else{
                    dfs(matrix,visited,cache,nx,ny,m,n);
                    cnt = Math.max(cnt,1+cache[nx][ny]);
                }
            }
        }
        cache[i][j] = cnt;
        return;
    }

    static int longestPathSequence(int[][] matrix) {
        // Write your code here.
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m+1][n+1];
        int[][] cache = new int[m+1][n+1];
        //do a dfs from every cell //and store the length of of maximum len seq starting from that cell
        int ans = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dfs(matrix,visited,cache,i,j,m,n);
                ans = Math.max(ans,cache[i][j]);
            }
        }
        return ans+1;
    };



}






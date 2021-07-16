package FloodFill;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class largestIsland {


    static final int N = 100;

    static int[][] a= new int[N][N];
    static int[][] vis = new int[N][N];
    static int[] col_cnt = new int[N];

    static int n, m;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void flood_fill(int x, int y, int col) {
        a[x][y] = col;
        col_cnt[col]++;
        vis[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (xx >= 0 && xx < n && yy >= 0 && yy < m && vis[xx][yy] == 0 && a[xx][yy] == 1) {
                flood_fill(xx, yy, col);
            }
        }
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int  n  = scn.nextInt() ,m = scn.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = scn.nextInt();
            }
        }

        int total_count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 1 && vis[i][j] == 0) {
                    total_count++;
                    flood_fill(i, j, total_count);
                }
            }
        }

        int largest_island = 0;

        for (int i = 1; i <= total_count; i++) {
            largest_island = Math.max(largest_island, col_cnt[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0) {
                    // can be converted
                    Set<Integer> St = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int ii = i + dx[k];
                        int jj = j + dy[k];
                        if (ii >= 0 && ii < n && jj >= 0 && jj < m) {
                            St.add(a[ii][jj]);
                        }
                    }
                    int ans = 1;
                    for (int x : St) {
                        ans += col_cnt[x];
                    }
                    largest_island = Math.max(largest_island, ans);
                }
            }
        }

        System.out.println(largest_island);

    }
}

package FloodFill;

import java.util.Scanner;

public class alphabetMatch {


    static final int N = 100;

    static int[][] a= new int[N][N];
    static int[][] vis = new int[N][N];

    static int n, m;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void flood_fill(int x, int y, int col) {
        vis[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (xx >= 0 && xx < n && yy >= 0 && yy < m && a[x][y] == a[xx][yy] && vis[xx][yy] == 0) {
                flood_fill(xx, yy, col);
            }
        }
        a[x][y] = col;
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int  n  = scn.nextInt() ,m = scn.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = scn.next().charAt(0);
                a[i][j] = ch - 'A' + 1;
            }
        }

        int col = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0) {
                    col++;
                    flood_fill(i, j, col);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(a[i][j] + " ");
            }
        }

    }
}

package FloodFill;

import java.util.Scanner;

public class totalConnectedComponent {


    static final int N = 100;

    static int[][] a= new int[N][N];
    static int[][] vis = new int[N][N];

    static int n, m;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};


    static void flood_fill(int x, int y) {
        a[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (xx >= 0 && xx < n && yy >= 0 && yy < m && a[xx][yy] == 1) {
                flood_fill(xx, yy);
            }
        }
    }

    public static void main(String[] args)
    {

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
                if (a[i][j] == 1) {
                    total_count++;
                    flood_fill(i, j);
                }
            }
        }

        System.out.println(total_count);

    }


}

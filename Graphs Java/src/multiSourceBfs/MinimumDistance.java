package multiSourceBfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MinimumDistance {

    static class Pair{
        int first, second;
        Pair(int a, int b){
            first = a;
            second = b;
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(), m = scn.nextInt();

        int[][] a = new int[n][m];
        int[][] dist = new int[n][m];
        Queue<Pair> Q = new LinkedList<>();

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = scn.nextInt();
                dist[i][j] = Integer.MAX_VALUE;
                if (a[i][j] != 0) {
                    Q.add(new Pair(i, j));
                    dist[i][j] = 0;
                }
            }
        }

        while (!Q.isEmpty()) {
            int x = Q.peek().first;
            ;
            int y = Q.peek().second;
            Q.remove();

            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];

                if (xx >= 0 && xx < n && yy >= 0 && yy < m && dist[xx][yy] == Integer.MAX_VALUE) {
                    Q.add(new Pair(xx, yy));
                    dist[xx][yy] = dist[x][y] + 1;
                }

            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (dist[i][j] == Integer.MAX_VALUE) System.out.print("# n");
                    else System.out.print(dist[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();


        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }






}

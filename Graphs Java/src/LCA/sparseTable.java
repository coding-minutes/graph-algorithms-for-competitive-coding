package LCA;

import java.util.ArrayList;
import java.util.Scanner;

public class sparseTable {

    static final int N = (int) 1e5, M = 20;

    static ArrayList<Integer>[] gr = new ArrayList[N];

    static {
        for(int i = 0; i < N; i++){
            gr[i] = new ArrayList<>();
        }
    }

    static int[] dep = new int[N];
    static int[][] Par = new int[N][M];


    // O(N*M)
    static void dfs(int cur, int par) {
        dep[cur] = dep[par] + 1;

        Par[cur][0] = par;
        for (int j = 1; j < M; j++) {
            Par[cur][j] = Par[Par[cur][j - 1]][j - 1];
        }

        for (int x : gr[cur]) {
            if (x != par) {
                dfs(x, cur);
            }
        }
    }

    // O(M) = logN
    static int LCA(int u, int v) {
        if (u == v) return u;
        if (dep[u] < dep[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = dep[u] - dep[v];
        for (int j = M - 1; j >= 0; j--) {
            if (((diff >> j) & 1) != 0) {
                // jth bit of diff is set
                u = Par[u][j];
            }
        }
        // u and v are on the same level
        for (int j = M - 1; j >= 0; j--) {
            if (Par[u][j] != Par[v][j]) {
                u = Par[u][j];
                v = Par[v][j];
            }
        }

        // Par[v][0]
        return Par[u][0];
    }

    // O(1)
    static int LengthFromUtoV(int u, int v) {
        int lca = LCA(u, v);
        return dep[u] + dep[v] - 2 * dep[lca];
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 0; i < n-1; i++) {
            int x = scn.nextInt(), y = scn.nextInt();
            gr[x].add(y);
            gr[y].add(x);
        }


        dfs(1, 0);


        System.out.print( LCA(4, 5) + "\n");
        System.out.print( LCA(4, 6) + "\n");
        System.out.print( LCA(4, 3) + "\n");
        System.out.print( LCA(2, 3) + "\n");
        System.out.print( LengthFromUtoV(2, 3) + "\n");

    }
}

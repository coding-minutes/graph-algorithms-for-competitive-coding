package DfsTreesAndBackEdges;

import java.util.ArrayList;
import java.util.Scanner;

public class DfsTreesAndBackEdges {


    static final int N = (int) (1e5 + 1);

    static ArrayList<Integer>[] gr = new ArrayList[N];
    static boolean[] vis = new boolean[N];
    static boolean cycle = false;

    static {
        for(int i = 0; i < gr.length; i++){
            gr[i] = new ArrayList<>();
        }
    }

    static void dfs(int cur, int par) {
        vis[cur] = true;
        for (int x : gr[cur]) {
            if (!vis[x]) {
                dfs(x, cur);
            }
            else if (x != par) {
                cycle = true;
            }
        }
    }

    public static void main(String[] args)
    {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(), m = scn.nextInt();

        for (int i = 0; i < m; i++) {
            int x = scn.nextInt(), y = scn.nextInt();
            gr[x].add(y);
            gr[y].add(x);

        }

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                dfs(i, 0);
            }
        }

        if (cycle) {
            System.out.print("Yes cycle found");
        }
        else {
            System.out.print("Not found");
        }


    }

}

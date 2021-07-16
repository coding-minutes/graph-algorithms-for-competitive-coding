package DfsTreesAndBackEdges;

import java.util.ArrayList;
import java.util.Scanner;

public class CycleInGraph {


    static final int N = (int) (1e5 + 1);

    static ArrayList<Integer>[] gr = new ArrayList[N];
    static int[] vis = new int[N];
    static int[] Par = new int[N];

    static {
        for(int i = 0 ; i < N ; i++){
            gr[i] = new ArrayList<>();
        }
    }


    static boolean cycle = false;

    static void dfs(int cur, int par) {
        // visited and in call stack
        vis[cur] = 1;
        Par[cur] = par;
        for (int x : gr[cur]) {
            if (vis[x] == 0) {
                dfs(x, cur);
            }
            else if (x != par && vis[x] == 1) {
                // backedge
                cycle = true;

                int u = cur, v = x;

                while (u != v) {
                    System.out.print(u + " ");
                    u = Par[u];
                }
                System.out.print(u + " ");
                System.exit(0);
            }
        }
        // visited and not in call stack
        vis[cur] = 2;
        return;
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
            if (vis[i] == 0) {
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

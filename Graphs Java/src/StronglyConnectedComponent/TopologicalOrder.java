package StronglyConnectedComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TopologicalOrder {

    static final int N = (int)(1e5 + 1);

    static ArrayList<Integer>[] gr = new ArrayList[N];
    static ArrayList<Integer> order;
    static {
        for(int i = 0 ; i < N ; i++){
            gr[i] = new ArrayList<>();
        }
    }

    static boolean[] vis = new boolean[N];

    static void dfs(int cur, int par) {
        vis[cur] = true;
        for (int x : gr[cur]) {
            if (!vis[x]) {
                dfs(x, cur);
            }
        }
        // I am here because I came back from the subtree
        order.add(cur);
        return;
    }


    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(), m = scn.nextInt();

        for (int i = 0; i < m; i++) {
            int x = scn.nextInt(), y = scn.nextInt();
            gr[x].add(y);

        }


        for (int i = 1; i <= n; i++) {
            if (!vis[i]) dfs(i, 0);
        }

        Collections.reverse(order);

        for (int x : order)
            System.out.println(x);

    }
}

package LCA;

import java.util.ArrayList;
import java.util.Scanner;
public class _2Pointer {

    static final int N = (int) (1e5 + 1);

    static ArrayList<Integer>[] gr = new ArrayList[N];

    static {
        for(int i = 0; i < N; i++){
            gr[i] = new ArrayList<>();
        }
    }


    static int[] dep = new int[N];
    static int[] Par = new int[N];

    static void dfs(int cur, int par) {
        Par[cur] = par;
        dep[cur] = dep[par] + 1;
        for (int x : gr[cur]) {
            if (x != par) {
                dfs(x, cur);
            }
        }
    }

    static int LCA(int u, int v) {
        if (u == v) return u;

        if (dep[u] < dep[v]){
            int temp = u;
            u = v;
            v = temp;
        }
        // depth of u is more than depth of v

        int diff = dep[u] - dep[v];

        // depth of both nodes same
        while (diff-- != 0) {
            u = Par[u];
        }

        // until they are equal nodes keep climbing
        while (u != v) {
            u = Par[u];
            v = Par[v];
        }

        return u;
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


        System.out.println(LCA(9,12));
        System.out.println(LCA(10,8));
        System.out.println(LCA(9,11));

    }
}

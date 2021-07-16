package ArticulationPointAndBridges;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ArticulationPointAndBridges {

    static class Pair{
        int first, second;

        Pair(int a, int b){
            first = a;
            second = b;
        }
    }

    static final int N = (int) (1e5 + 1);

    static ArrayList<Integer>[] gr = new ArrayList[N];
    static Set<Integer> arti_points = new HashSet<>();
    static {
        for(int i = 0 ; i < N ; i++){
            gr[i] = new ArrayList<>();
        }
    }

    static int tme = 1;
    static boolean[] vis = new boolean[N];
    static int[] disc = new int[N];
    static int[] low = new int[N];

    static ArrayList<Pair> bridges = new ArrayList<>();

    static void dfs(int cur, int par) {
        vis[cur] = true;
        disc[cur] = low[cur] = tme++;
        int child = 0;
        for (int x : gr[cur]) {

            if (!vis[x]) {
                dfs(x, cur);
                child++;
                // we know low and disc of x
                low[cur] = Math.min(low[cur], low[x]);

                // bridges
                if (low[x] > disc[cur]) {
                    bridges.add(new Pair(cur, x));
                }

                // articulation points
                if (par != 0 && low[x] >= disc[cur]) {
                    arti_points.add(cur);
                }

            }
            else if (x != par) {
                // backedge
                low[cur] = Math.min(low[cur], disc[x]);
            }
        }

        // root is an arti or not
        if (par == 0 && child > 1) {
            arti_points.add(cur);
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

        dfs(1, 0);

        for (int x : arti_points) System.out.println(x);

        for (Pair x : bridges) {
            System.out.println( x.first + " " + x.second );
        }

    }
}

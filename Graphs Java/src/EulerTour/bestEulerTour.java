package EulerTour;

import java.util.ArrayList;
import java.util.Scanner;

public class bestEulerTour {



    static final int N = (int) (1e5 + 1);

    static ArrayList<Integer>[] gr = new ArrayList[N];

    static {
        for(int i = 0; i < N; i++){
            gr[i] = new ArrayList<>();
        }
    }
    static int[] tin = new int[N];
    static int[] tout = new int[N];
    static int[] flat = new int[N];
    static int tme = 0;

    static void dfs1(int cur, int par) {
        tin[cur] = tme++;
        for (int x : gr[cur]) {
            if (x != par) {
                // x is child node
                dfs1(x, cur);
            }
        }
        tout[cur] = tme++;
    }

    static void dfs2(int cur, int par) {
        System.out.println(cur + " ");
        for (int x : gr[cur]) {
            if (x != par) {
                // x is child node
                dfs2(x, cur);
                System.out.println(cur + " ");
            }
        }
    }

    static void dfs3(int cur, int par) {
        tin[cur] = ++tme;
        for (int x : gr[cur]) {
            if (x != par) {
                // x is child node
                dfs3(x, cur);
            }
        }
        tout[cur] = tme;
    }

    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 0; i < n-1; i++) {
            int x = scn.nextInt(), y = scn.nextInt();
            gr[x].add(y);
            gr[y].add(x);
        }


        // tme = 1;
        // dfs1(1, 0);


        // dfs2(1, 0);

        tme = 0;
        dfs3(1, 0);

        for (int i = 1; i <= n; i++) {
            System.out.println(i + " " + tin[i] + " " + tout[i] );
        }

        for (int i = 1; i <= n; i++) {
            flat[tin[i]] = i;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(flat[i] + " ");
        }

    }

}

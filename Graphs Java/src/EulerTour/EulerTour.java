package EulerTour;

import java.util.ArrayList;
import java.util.Scanner;

public class EulerTour {

    static final int N = (int) (1e5 + 1);

    static ArrayList<Integer>[] gr = new ArrayList[N];

    static {
        for(int i = 0; i < N; i++){
            gr[i] = new ArrayList<>();
        }
    }


    static void dfs1(int cur, int par) {
        // time in
        System.out.print(cur + " ");
        for (int x : gr[cur]) {
            if (x != par) {
                // x is child node
                dfs1(x, cur);
            }
        }
        // time out
        System.out.print(cur + " ");
    }

    static void dfs2(int cur, int par) {
        System.out.print(cur + " ");
        for (int x : gr[cur]) {
            if (x != par) {
                // x is child node
                dfs2(x, cur);
                System.out.print(cur + " ");
            }
        }
    }

    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 0; i < n-1; i++) {
            int x = scn.nextInt(), y = scn.nextInt();
            gr[x].add(y);
            gr[y].add(x);

        }


        // dfs1(1, 0);

        dfs2(1, 0);

    }


}

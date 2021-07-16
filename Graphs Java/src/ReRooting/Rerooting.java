package ReRooting;

import java.util.ArrayList;
import java.util.Scanner;

public class Rerooting {



    public static int N = (int) 1e5;

    static ArrayList<Integer>[] gr = new ArrayList[N];

    static {
        for(int i = 0; i < N; i++){
            gr[i] = new ArrayList<>();
        }
    }

    static int[] sub = new int[N];
    static int[] dp = new int[N];

    // sum of subtree sizes of all the nodes
    static void dfs(int cur, int par) {
        sub[cur] = 1;
        dp[cur] = 0;

        for (int x : gr[cur]) {
            if (x != par) {
                dfs(x, cur);
                dp[cur] += dp[x];
                sub[cur] += sub[x];
            }
        }

        dp[cur] += sub[cur];
    }

    static int ans = 0;

    static void dfs1(int cur, int par) {
        // dp values are according to as cur is the root of the tree
        System.out.println(cur + " " + dp[cur]);
        ans = Math.max(ans, dp[cur]);

        for (int x : gr[cur]) {
            if (x != par) {
                // remove x from subtree of cur
                dp[cur] -= dp[x];
                dp[cur] -= sub[x];
                sub[cur] -= sub[x];
                // now add cur as the subtree of x
                sub[x] += sub[cur];
                dp[x] += dp[cur];
                dp[x] += sub[cur];

                dfs1(x, cur);

                // come back from x
                // rollback the changes as original tree
                dp[x] -= sub[cur];
                dp[x] -= dp[cur];
                sub[x] -= sub[cur];
                sub[cur] += sub[x];
                dp[cur] += sub[x];
                dp[cur] += dp[x];

            }
        }

    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int x = scn.nextInt(), y = scn.nextInt();
            gr[x].add(y);
            gr[y].add(x);
        }


        dfs(1, 0);

        // for (int i = 1; i <= n; i++) {
        // 	cout << dp[i] << " " << sub[i] << '\n';
        // }

        dfs1(1, 0);

        System.out.println(ans);
    }

}

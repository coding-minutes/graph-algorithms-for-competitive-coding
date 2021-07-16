package ReRooting;

import java.util.ArrayList;
import java.util.Scanner;

public class bruteForce {


    public static int N = (int) 1e5;

    static ArrayList<Integer>[] gr = new ArrayList[N];

    static {
        for(int i = 0; i < N; i++){
            gr[i] = new ArrayList<>();
        }
    }

    static int[] sub = new int[N];

    // sum of subtree sizes of all the nodes
    static int dfs(int cur, int par) {
        sub[cur] = 1;

        int sum = 0;

        for (int x : gr[cur]) {
            if (x != par) {

                sum += dfs(x, cur);
                sub[cur] += sub[x];

            }
        }

        sum += sub[cur];

        return sum;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int x = scn.nextInt(), y = scn.nextInt();
            gr[x].add(y);
            gr[y].add(x);
        }


        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dfs(i, 0));
        }

        // cout << dfs(3, 0) << '\n';
        // O(n*n)

        System.out.println(ans);
    }
}

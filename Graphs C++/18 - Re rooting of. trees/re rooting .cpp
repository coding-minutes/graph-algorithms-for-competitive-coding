#include<bits/stdc++.h>

using namespace std;

const int N = 1e5, M = 20;

vector<int> gr[N];
int sub[N], dp[N];

// sum of subtree sizes of all the nodes
void dfs(int cur, int par) {
	sub[cur] = 1;
	dp[cur] = 0;

	for (auto x : gr[cur]) {
		if (x != par) {
			dfs(x, cur);
			dp[cur] += dp[x];
			sub[cur] += sub[x];
		}
	}

	dp[cur] += sub[cur];
	return ;
}

int ans = 0;

void dfs1(int cur, int par) {
	// dp values are according to as cur is the root of the tree
	cout << cur << " " << dp[cur] << '\n';
	ans = max(ans, dp[cur]);

	for (auto x : gr[cur]) {
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

int main()
{
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	int n;
	cin >> n;

	for (int i = 1; i < n; i++) {
		int x, y;
		cin >> x >> y;
		gr[x].push_back(y);
		gr[y].push_back(x);
	}

	dfs(1, 0);

	// for (int i = 1; i <= n; i++) {
	// 	cout << dp[i] << " " << sub[i] << '\n';
	// }

	dfs1(1, 0);

	cout << ans << '\n';

	return 0;
}
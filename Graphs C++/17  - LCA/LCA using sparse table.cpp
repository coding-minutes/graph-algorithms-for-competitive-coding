#include<bits/stdc++.h>

using namespace std;

const int N = 1e5, M = 20;

vector<int> gr[N];
int dep[N], Par[N][M];

// O(N*M)
void dfs(int cur, int par) {
	dep[cur] = dep[par] + 1;

	Par[cur][0] = par;
	for (int j = 1; j < M; j++) {
		Par[cur][j] = Par[Par[cur][j - 1]][j - 1];
	}

	for (auto x : gr[cur]) {
		if (x != par) {
			dfs(x, cur);
		}
	}
}

// O(M) = logN
int LCA(int u, int v) {
	if (u == v) return u;
	if (dep[u] < dep[v]) swap(u, v);
	int diff = dep[u] - dep[v];
	for (int j = M - 1; j >= 0; j--) {
		if ((diff >> j) & 1) {
			// jth bit of diff is set
			u = Par[u][j];
		}
	}
	// u and v are on the same level
	for (int j = M - 1; j >= 0; j--) {
		if (Par[u][j] != Par[v][j]) {
			u = Par[u][j];
			v = Par[v][j];
		}
	}

	// Par[v][0]
	return Par[u][0];
}

// O(1)
int LengthFromUtoV(int u, int v) {
	int lca = LCA(u, v);
	return dep[u] + dep[v] - 2 * dep[lca];
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

	// for (int i = 1; i <= 12; i++) {
	// 	cout << i << "-> ";
	// 	for (int j = 0; j < 4; j++) {
	// 		cout << Par[i][j] << " ";
	// 	} cout << '\n';
	// }

	// for (int i = 1; i <= n; i++) {
	// 	cout << i << " " << dep[i] << '\n';
	// }

	// cout << LCA(9, 12) << '\n';
	// cout << LCA(10, 8) << '\n';
	// cout << LCA(9, 11) << '\n';

	cout << LengthFromUtoV(9, 12) << '\n';
	cout << LengthFromUtoV(10, 8) << '\n';

	return 0;
}
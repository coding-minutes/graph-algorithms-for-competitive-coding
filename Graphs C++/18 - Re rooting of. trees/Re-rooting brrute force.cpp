#include<bits/stdc++.h>

using namespace std;

const int N = 1e5, M = 20;

vector<int> gr[N];
int sub[N];

// sum of subtree sizes of all the nodes
int dfs(int cur, int par) {
	sub[cur] = 1;

	int sum = 0;

	for (auto x : gr[cur]) {
		if (x != par) {

			sum += dfs(x, cur);
			sub[cur] += sub[x];

		}
	}

	sum += sub[cur];

	return sum;
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

	int ans = 0;
	for (int i = 1; i <= n; i++) {
		ans = max(ans, dfs(i, 0));
	}

	// cout << dfs(3, 0) << '\n';
	// O(n*n)

	cout << ans;

	return 0;
}
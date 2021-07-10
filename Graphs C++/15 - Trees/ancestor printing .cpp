#include<bits/stdc++.h>

using namespace std;

const int N = 1e5;

vector<int> gr[N];
int Par[N];

void dfs(int cur, int par) {
	Par[cur] = par;
	for (auto x : gr[cur]) {
		if (x != par) {
			// x is child node
			dfs(x, cur);
		}
	}
}

int main()
{
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	int n;
	cin >> n;
	for (int i = 0; i < n - 1; i++) {
		int x, y;
		cin >> x >> y;
		gr[x].push_back(y);
		gr[y].push_back(x);
	}

	dfs(10, 0);

	int x = 6;
	// print all ancestors of 5

	while (x) {
		cout << x << '\n';
		x = Par[x];
	}














	return 0;
}
#include<bits/stdc++.h>

using namespace std;

const int N = 1e5;

vector<int> gr[N];

void dfs1(int cur, int par) {
	// time in
	cout << cur << " ";
	for (auto x : gr[cur]) {
		if (x != par) {
			// x is child node
			dfs1(x, cur);
		}
	}
	// time out
	cout << cur << " ";
}

void dfs2(int cur, int par) {
	cout << cur << " ";
	for (auto x : gr[cur]) {
		if (x != par) {
			// x is child node
			dfs2(x, cur);
			cout << cur << " ";
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

	// dfs1(1, 0);

	dfs2(1, 0);



	return 0;
}
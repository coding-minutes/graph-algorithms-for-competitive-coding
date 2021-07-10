#include<bits/stdc++.h>

using namespace std;

const int N = 1e5 + 1;

vector<int> gr[N];
int vis[N];
bool cycle = false;

void dfs(int cur, int par) {
	vis[cur] = 1;
	for (auto x : gr[cur]) {
		if (!vis[x]) {
			dfs(x, cur);
		}
		else if (x != par) {
			cycle = true;
		}
	}
}

int main()
{
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	int n, m;
	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		int x, y;
		cin >> x >> y;
		gr[x].push_back(y);
		gr[y].push_back(x);

	}

	for (int i = 1; i <= n; i++) {
		if (!vis[i]) {
			dfs(i, 0);
		}
	}

	if (cycle) {
		cout << "Yes cycle found";
	}
	else {
		cout << "Not found";
	}


	return 0;
}
#include<bits/stdc++.h>

using namespace std;

const int N = 100;

int a[N][N], vis[N][N];
int col_cnt[N];

int n, m;

int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};

void flood_fill(int x, int y, int col) {
	a[x][y] = col;
	col_cnt[col]++;
	vis[x][y] = 1;
	for (int i = 0; i < 4; i++) {
		int xx = x + dx[i];
		int yy = y + dy[i];
		if (xx >= 0 && xx < n && yy >= 0 && yy < m && vis[xx][yy] == 0 && a[xx][yy] == 1) {
			flood_fill(xx, yy, col);
		}
	}
}

int main()
{
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> a[i][j];
		}
	}

	int total_count = 0;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (a[i][j] == 1 && vis[i][j] == 0) {
				total_count++;
				flood_fill(i, j, total_count);
			}
		}
	}

	int largest_island = 0;

	for (int i = 1; i <= total_count; i++) {
		largest_island = max(largest_island, col_cnt[i]);
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (a[i][j] == 0) {
				// can be converted
				set<int> St;
				for (int k = 0; k < 4; k++) {
					int ii = i + dx[k];
					int jj = j + dy[k];
					if (ii >= 0 && ii < n && jj >= 0 && jj < m) {
						St.insert(a[ii][jj]);
					}
				}
				int ans = 1;
				for (auto x : St) {
					ans += col_cnt[x];
				}
				largest_island = max(largest_island, ans);
			}
		}
	}

	cout << largest_island;

	return 0;
}
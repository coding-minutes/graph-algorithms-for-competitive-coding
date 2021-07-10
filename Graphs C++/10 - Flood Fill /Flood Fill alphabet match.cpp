#include<bits/stdc++.h>

using namespace std;

const int N = 100;

int a[N][N], vis[N][N];

int n, m;

int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};

void flood_fill(int x, int y, int col) {
	vis[x][y] = 1;
	for (int i = 0; i < 4; i++) {
		int xx = x + dx[i];
		int yy = y + dy[i];
		if (xx >= 0 && xx < n && yy >= 0 && yy < m && a[x][y] == a[xx][yy] && !vis[xx][yy]) {
			flood_fill(xx, yy, col);
		}
	}
	a[x][y] = col;
}

int main()
{
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			char ch;
			cin >> ch;
			a[i][j] = ch - 'A' + 1;
		}
	}

	int col = 0;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (!vis[i][j]) {
				col++;
				flood_fill(i, j, col);
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << a[i][j] << " ";
		} cout << '\n';
	}

	return 0;
}
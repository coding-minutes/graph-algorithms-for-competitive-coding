#include<bits/stdc++.h>

using namespace std;

struct Dinic {
	// from x to y with residual capacity
	struct edge {
		int x, y, w;
	};

	vector<vector<int>> gr;
	vector<edge> edges;
	vector<int> level;
	vector<int> edge_sz;

	int n, source, sink, ec = 0;
	Dinic(int n, int source, int sink) {
		this->n = n;
		this->source = source;
		this->sink = sink;
		gr.resize(n);
		edge_sz.resize(n);
	}

	void add_edge(int x, int y, int w) {
		edges.push_back({x, y, w});
		gr[x].push_back(ec++);
		edges.push_back({y, x, 0});
		gr[y].push_back(ec++);
	}

	bool level_graph() {
		level.clear(); level.resize(n, -1);

		queue<int> Q;
		Q.push(source);
		level[source] = -1;

		while (!Q.empty()) {
			int cur = Q.front();
			Q.pop();

			for (auto xx : gr[cur]) {
				int to = edges[xx].y;
				int w = edges[xx].w;

				if (w && level[to] == -1) {
					level[to] = level[cur] + 1;
					Q.push(to);
				}

			}
		}

		return level[sink] != -1;
	}

	int augment(int cur, int flow) {
		if (cur == sink) return flow;

		for (int &i = edge_sz[cur]; i >= 0; i--) {
			int edge_index = gr[cur][i];
			int w = edges[edge_index].w;
			int y = edges[edge_index].y;

			if (w && level[y] == level[cur] + 1) {
				int bottleneck_flow = augment(y, min(w, flow));

				if (bottleneck_flow) {
					// forward edge according to current augmented path
					edges[edge_index].w -= bottleneck_flow;
					// backward edge
					edges[edge_index ^ 1].w += bottleneck_flow;

					return bottleneck_flow;
				}

			}
		}
		return 0;
	}

	int max_flow() {
		int total_flow = 0;

		while (level_graph()) {
			// find augmenting paths and update in residual network
			for (int i = 0; i < n; i++) edge_sz[i] = gr[i].size() - 1;
			while (int flow = augment(0, 1e9)) {
				total_flow += flow;
			}
		}

		return total_flow;
	}

};

int main()
{
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	Dinic G(4, 0, 3);

	G.add_edge(0, 1, 5);
	G.add_edge(1, 2, 10);
	G.add_edge(2, 3, 3);

	cout << G.max_flow();

	return 0;
}
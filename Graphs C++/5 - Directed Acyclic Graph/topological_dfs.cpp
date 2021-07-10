#include<iostream>
#include<map>
#include<list>
#include<queue>
#include<cstring>
#include<vector>
using namespace std;

class Graph {

	list<int> *l;
	int V;

public:
	Graph(int V) {
		this->V = V;
		l = new list<int>[V];
	}

	void addEdge(int x, int y) {
		//directed graph
		l[x].push_back(y);
	}
	void dfs(int node, vector<bool> &visited, list<int> &ordering){

		visited[node] = true;

		for(int nbr : l[node]){
			if(!visited[nbr]){
				dfs(nbr,visited,ordering);
			}
		}
		//at this point 
		ordering.push_front(node);
		return;

	}

	//Complete this method
	void dfs_topological_sort() {

		vector<bool> visited(V,false);
		list<int> ordering;

		//we call dfs from every node if it not visited
		for(int i=0;i<V;i++){
			if(!visited[i]){
				dfs(i,visited,ordering);
			}
		}

		//finaly print the ordeirng
		for(auto node:ordering){
			cout<<node<<" ";
		}
		cout<<endl;
		

	}

};


int main() {

	Graph g(6);
	g.addEdge(0, 2);
	g.addEdge(2, 3);
	g.addEdge(3, 5);
	g.addEdge(4, 5);
	g.addEdge(1, 4);
	g.addEdge(1, 2);

	g.dfs_topological_sort();

	return 0;
}
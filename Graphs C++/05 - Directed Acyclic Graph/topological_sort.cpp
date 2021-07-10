#include<iostream>
#include<list>
#include<queue>
using namespace std;


class Graph{
	int V;
public:
	list<int> *l;

	Graph(int v){
		V = v;
		l = new list<int>[V];
	}

	void addEdge(int u,int v,bool bidir=true){
		l[u].push_back(v);
		if(bidir){
			l[v].push_back(u);
		}
	}
	//Iterate using a loop - Breadth First Search
	void bfs(int s){
		queue<int> q;
		q.push(s);


		int *dist = new int[V];
		//Init all nodes with inf dist
		for(int i=0;i<V;i++){
			dist[i] = INT_MAX;
		}
		dist[s] = 0;

		while(!q.empty()){
			int node  = q.front();
			q.pop();

			for(auto child:l[node]){
				if(dist[child]==INT_MAX){
					q.push(child);
					dist[child] = dist[node] + 1;
				}	
			}
		}

		//Print the dist of all nodes
		for(int i=0;i<V;i++){
			cout<<"dist["<<i<<"]"<<"="<<dist[i]<<endl;
		}

	}

	void topologicalSort(){
		int *indegree  = new int[V]{0};

		//Init All Indegrees
		for(int i=0;i<V;i++){
			for(int child:l[i]){
				indegree[child]++;
			}
		}

		//Find all nodes with 0 indegrees
		queue<int> q;
		for(int i=0;i<V;i++){
			if(indegree[i]==0)
				q.push(i);
		}

		//Start removing elements from the queue
		while(!q.empty()){
			int node = q.front();
			cout<<node<<" ";
			q.pop();

			for(int child:l[node]){
				indegree[child]--;
				if(indegree[child]==0){
					q.push(child);
				}
			}
		}
	}
};

int main(){

	Graph g(7);
	g.addEdge(0,2,false);
	g.addEdge(1,6,false);
	g.addEdge(2,6,false);
	g.addEdge(2,3,false);
	g.addEdge(3,5,false);
	g.addEdge(6,5,false);

	g.topologicalSort(); //DAG 

	return 0;
}
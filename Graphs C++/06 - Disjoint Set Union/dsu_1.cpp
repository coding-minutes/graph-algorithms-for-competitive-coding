#include<iostream>
#include<list>
using namespace std;

//Cycle Detection in a undirected graph using DSU

class Graph{
	int V;
	list<pair<int,int> > l;

public:
	Graph(int V){
		this->V = V;
	}

	int addEdge(int u,int v){
		l.push_back(make_pair(u,v));
	}
	// Find
	int findSet(int i,int parent[]){
		//base case
		if(parent[i]==-1){
			return i;
		}

		return findSet(parent[i],parent);
	}


	//Union 
	void union_set(int x,int y,int parent[]){
		int s1 = findSet(x,parent);
		int s2 = findSet(y,parent);

		if(s1!=s2){
			parent[s1] = s2;
		}
	}


	bool contains_cycle(){
		//DSU Logic to check if graph contains cycle or not
		int *parent = new int[V];
		for(int i=0;i<V;i++){
			parent[i] = -1;
		}

		//iterate over the edge list
		for(auto edge : l){
			int i = edge.first;
			int j = edge.second;

			int s1 = findSet(i,parent);
			int s2 = findSet(j,parent);

			if(s1!=s2){
				union_set(s1,s2,parent);
			}
			else{
				cout<<"Same parents"<<s1<<" and "<<s2<<endl;
				return true;
			}

		}

	
	delete [] parent;
	return false;
	}
	
};


int main(){
	Graph g(4);
	g.addEdge(0,1);
	g.addEdge(1,2);
	g.addEdge(2,3);
	//g.addEdge(3,0);

	cout<<g.contains_cycle()<<endl;



}










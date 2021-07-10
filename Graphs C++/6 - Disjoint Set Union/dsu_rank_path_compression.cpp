#include<iostream>
#include<list>
using namespace std;



class Graph{

	list<pair<int,int> > edge_list;
	int V;


public:
	Graph(int V){
		this->V = V;
	}	

	void addEdge(int u,int v){
		edge_list.push_back(make_pair(u,v));
	}

	int findSet(int i,int parent[]){
		if(parent[i]==-1){
			return i;
		}
		int p = findSet(parent[i],parent);
		//compress the path for next time
		parent[i] = p;
		return p;

	}
	void union_set(int u,int v,int rank[],int parent[]){

		int p1 = findSet(u,parent);
		int p2 = findSet(v,parent);

		if(p1!=p2){
			if(rank[p1]<rank[p2]){
				parent[p1] = p2;
				rank[p2] += rank[p1];
			}
			else{
				parent[p2] = p1;
				rank[p1] += rank[p2];
			}
		}

		//print parent array after every union
		cout<<"Parent";
		for(int i=0;i<V;i++){
			cout<<parent[i]<<" ";
		}
		cout<<endl;
		cout<<"Rank";
		for(int j=0;j<V;j++){
			cout<<rank[j]<<" ";
		}
		cout<<endl;

	}

	bool contains_cycle(){
		int *parent = new int[V];
		int *rank = new int[V];
		for(int i=0;i<V;i++){
			parent[i] = -1;
			rank[i] = 1;
		}

		for(auto edge:edge_list){
			int i = edge.first;
			int j = edge.second;

			int p1 = findSet(i,parent);
			int p2 = findSet(j,parent);
			cout<<i<<"-"<<p1<<" and "<<j<<"-"<<p2<<endl;
			
			if(p1!=p2){
				union_set(p1,p2,rank,parent);

			}
			else{
				//belong to same set 
				cout<<"Parent";
				for(int i=0;i<V;i++){
					cout<<parent[i]<<" ";
				}
				

				return true;
			}

		}
		return false;


		
		delete [] parent;

	}
};

int main(){

	Graph g(7);
	g.addEdge(0,1);
	g.addEdge(1,2);
	g.addEdge(2,3);
	g.addEdge(0,4);

	g.addEdge(5,6);
	g.addEdge(2,5);
	g.addEdge(2,6);
	

	cout<<g.contains_cycle()<<endl;

	return 0;

}
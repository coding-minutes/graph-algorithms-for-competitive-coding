#include<iostream>
#include<vector>
using namespace std;


vector<int> bellman_ford(int V,int src,vector<vector<int> > edges){

	//create a vector 
	vector<int> dist(V+1,INT_MAX);
	dist[src] = 0;

	//relax all edges v-1 times
	for(int i=0;i<V-1;i++){

		for(auto edge : edges){
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];

			if(dist[u]!=INT_MAX and dist[u] + wt < dist[v]){
				dist[v] = dist[u] + wt;
			}
		}
	}
	// negative wt cycle 
	for(auto edge : edges){
		int u = edge[0];
		int v = edge[1];
		int wt = edge[2];
		if(dist[u]!=INT_MAX and dist[u] + wt < dist[v]){
			cout<<"negative Wt cycle found";
			exit(0);
		}

	}
	return dist;
}



int main(){


	int n,m;
	cin>>n>>m;



	//edge list 
	vector<vector<int> >  edges;       // (a,b,3) (c,d,5) ....

	for(int i=0;i<m;i++){
		int u,v,wt;
		cin>>u>>v>>wt;
		edges.push_back({u,v,wt});
	}
	//bellman algorithm

	vector<int> distances = bellman_ford(n,1,edges);

	for(int i=1;i<=n;i++){
		cout<<"Node "<<i<<" is at dist "<<distances[i]<<endl;
	}

	return 0;

}








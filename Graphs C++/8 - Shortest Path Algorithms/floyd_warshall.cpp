#include<iostream>
#include<vector>
using namespace std;

#define INF 10000

vector<vector<int> > floyd_warshall(vector<vector<int> > graph){

	vector<vector<int> > dist(graph);

	int V = graph.size();

	//Phases, in kth phase we included vertices (1,2,...k) as intermediate vertices
	for(int k=0;k<V;k++){
		//Iterate over entire 2D Matrix
		for(int i=0;i<V;i++){
			for(int j=0;j<V;j++){

				//if vertex k is included, and can we minimise the dist ?
				if(dist[i][j] > dist[i][k] + dist[k][j]){
					dist[i][j] = dist[i][k] + dist[k][j];
				}
			}

		}
	}
	return dist;
}


int main(){


	// 4 Vertices (4 X 4 Matrix)
	vector<vector<int> > graph = {
								 {0,INF,-2,INF},
								 {4,0,3,INF},
								 {INF,INF,0,2},
								 {INF,-1,INF,0}
								};


	auto result = floyd_warshall(graph);
	for(int i=0;i<result.size();i++){
		for(int j=0;j<result.size();j++){
			cout<<result[i][j]<<" ";
		}
		cout<<endl;
	}

	return 0;
}


#include<iostream>
#include<list>
#include<queue>
using namespace std;

class Graph{
    list<int> *l;
    int V;
    
public:
    Graph(int V){
        this->V = V;
        l = new list<int>[V+1];
    }
    void addEdge(int u,int v){
        l[u].push_back(v);
    }
    
    int minCostBFS(int src,int dest){
        queue<int> q;
        vector<bool> visited(V,false);
        vector<int> dist(V,0);
        
        q.push(src);
        visited[src] = true;
        dist[src] = 0;
        
        while(!q.empty()){
            int f = q.front();
            q.pop();
            
            for(int nbr : l[f]){
                if(!visited[nbr]){
                    q.push(nbr);
                    visited[nbr] = true;
                    dist[nbr] = dist[f] + 1;
                }
            }
        }
        return dist[dest];
    }

};



int min_dice_throws(int n,vector<pair<int,int> > snakes, vector<pair<int,int> > ladders){
    vector<int> board(n+1,0);
    
    //board to graph conversion
    for(auto sp: snakes){
        int s = sp.first;
        int e = sp.second;
        board[s] = e - s;
    }
    
    for(auto lp: ladders){
        int s = lp.first;
        int e = lp.second;
        board[s] = e - s;
    }
    
    //Graph
    Graph g(n+1);
    for(int u=1;u<n;u++){
        for(int dice=1;dice<=6;dice++){
            int v = u + dice;
            v += board[v];
            if(v<=n){
                g.addEdge(u,v);
            }
        }

    }
    return g.minCostBFS(1,n);
}

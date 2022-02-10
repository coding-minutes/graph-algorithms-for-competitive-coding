import java.util.Scanner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

 class Graph {
    public HashMap<Integer,HashSet<Integer>> map;
    public ArrayList<Integer> path;
    public int[] parent;
    int n;
    public Graph(int n){
        this.n=n;
        parent=new int[n+1];
        Arrays.fill(parent,-1);
        path=new ArrayList<>();
        map=new HashMap<>();
        for(int i=1;i<=n;i++)
        {
            map.put(i,new HashSet<Integer>());
        }
    }


    public void printPath(){
        int i=n;
        while(parent[i]!=-1){
           path.add(0,i);
           i=parent[i];
        }
        System.out.print(1+" ");
        for(i=0;i<path.size();i++){
            int c=path.get(i);
            System.out.print(c+" ");
        }
        return;
    }

    public int bfs(int src,int des)
    {   
        boolean[] visited=new boolean[des+1];
        Queue<Integer> q=new LinkedList<>();
        q.offer(src);
        visited[src]=true;
           
        int moves=0;

        while(!q.isEmpty())
        {
            int size=q.size();
            while(size-->0){

                int val=q.poll();

                if(val==des){
                    return moves;
                }

                for(int neighbour : map.get(val)){
                    if(!visited[neighbour]){
                        visited[neighbour]=true;
                        parent[neighbour]=val;
                        q.offer(neighbour);
                    }
                }
               
            }
            moves++;
        }
        return -1;
    }
}

public class MessageRoutes {

    String url="https://cses.fi/problemset/task/1667/";
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int numberOfComputers=sc.nextInt();
            int numberOfConnections=sc.nextInt();
            //1 to numberOfComputers
            int a;int b;
            
            Graph g=new Graph(numberOfComputers);

            for(int i=0;i<numberOfConnections;i++)
            {
                
                a=sc.nextInt();
                b=sc.nextInt();
                g.map.get(a).add(b);
                g.map.get(b).add(a);
            }
            
            //Since it is a connection
            //So It is undirected
     int ans=1+g.bfs(1,numberOfComputers);
     if(ans==0){
         System.out.println("IMPOSSIBLE");return;
     }
      System.out.println(ans);
      g.printPath();
        }
        

    }
}

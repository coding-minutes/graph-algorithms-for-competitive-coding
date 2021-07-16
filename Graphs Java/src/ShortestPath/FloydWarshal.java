package ShortestPath;


public class FloydWarshal {

    final static int INF = 10000;

    static int[][] floyd_warshall(int[][] graph){

        int[][] dist = graph;

        int V = graph.length;

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


    public static void main(String[]  args){



        int[][] graph = {
                {0,INF,-2,INF},
                {4,0,3,INF},
                {INF,INF,0,2},
                {INF,-1,INF,0}
        };


        int[][] result = floyd_warshall(graph);
        for(int i=0;i<result.length;i++){
            for(int j=0;j<result.length;j++){
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }

    }
}







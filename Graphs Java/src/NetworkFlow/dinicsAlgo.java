package NetworkFlow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class dinicsAlgo {


    static class Dinic {
        // from x to y with residual capacity
        class edge {
            int x, y, w;

            edge(int a, int b, int c){
                x = a;
                y = b;
                w = c;
            }
        };

        ArrayList<ArrayList<Integer>> gr = new ArrayList<>();
        ArrayList<edge> edges = new ArrayList<>();
        int[] level;
        int[] edge_sz;

        int n, source, sink, ec = 0;
        Dinic(int n, int source, int sink) {
            this.n = n;
            this.source = source;
            this.sink = sink;

            for(int i = 0; i < n; i++){
                gr.add(new ArrayList<>());

            }
            edge_sz = new int[n];
        }

        void add_edge(int x, int y, int w) {
            edges.add(new edge(x, y, w));
            gr.get(x).add(ec++);
            edges.add(new edge(y, x, 0));
            gr.get(y).add(ec++);
        }

        boolean level_graph() {
            level = new int[n];
            Arrays.fill(level, -1);

            Queue<Integer> Q = new LinkedList<>();
            Q.add(source);
            level[source] = -1;

            while (!Q.isEmpty()) {
                int cur = Q.poll();


                for (int xx : gr.get(cur)) {
                    int to = edges.get(xx).y;
                    int w = edges.get(xx).w;

                    if (w != 0 && level[to] == -1) {
                        level[to] = level[cur] + 1;
                        Q.add(to);
                    }

                }
            }

            return level[sink] != -1;
        }

        int augment(int cur, int flow) {
            if (cur == sink) return flow;

            for (int i = edge_sz[cur]; i >= 0; i--) {
                int edge_index = gr.get(cur).get(i);
                int w = edges.get(edge_index).w;
                int y = edges.get(edge_index).y;

                if (w != 0 && level[y] == level[cur] + 1) {
                    int bottleneck_flow = augment(y, Math.min(w, flow));

                    if (bottleneck_flow != 0) {
                        // forward edge according to current augmented path
                        edges.get(edge_index).w -= bottleneck_flow;
                        // backward edge
                        edges.get(edge_index ^ 1).w += bottleneck_flow;

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
                for (int i = 0; i < n; i++) edge_sz[i] = gr.get(i).size() - 1;
                int flow = augment(0, (int) 1e9) ;
                while ( flow != 0) {
                    total_flow += flow;
                    flow = augment(0, (int) 1e9);
                }
            }

            return total_flow;
        }

    };

    public static void main(String[] args) {


        Dinic G = new Dinic(4, 0, 3);

        G.add_edge(0, 1, 5);
        G.add_edge(1, 2, 10);
        G.add_edge(2, 3, 3);

        System.out.println(G.max_flow());

    }
}

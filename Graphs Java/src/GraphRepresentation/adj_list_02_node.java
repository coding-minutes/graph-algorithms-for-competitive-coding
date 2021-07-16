package GraphRepresentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adj_list_02_node {

    static class Node{
        String name;
        ArrayList<String> nbrs;

        Node(String name){
            this.name = name;
            nbrs = new ArrayList<>();
        }
    }

    static class Graph{

        HashMap<String, Node> mp;

        public Graph(ArrayList<String> cities){
            mp = new HashMap<>();
            for(String city: cities){
                mp.put(city, new Node(city));
            }
        }

        public void addEdge(String x, String y, boolean unDirected){
            mp.get(x).nbrs.add(y);
            if(unDirected){
                mp.get(y).nbrs.add(x);
            }
        }

        public void printAdjList(){
            for(Map.Entry<String, Node> cityPair: mp.entrySet()){
                System.out.print(cityPair.getKey() + " --> ");
                for(String nbrs: cityPair.getValue().nbrs){
                    System.out.print(nbrs + ", ");
                }
                System.out.println();
            }
        }


    }

    public static void main(String[] args){

        ArrayList<String> cities = new ArrayList<>();
        cities.add("Delhi");
        cities.add("London");
        cities.add("Paris");
        cities.add( "New York");

        Graph g = new Graph(cities);
        g.addEdge("Delhi", "London" , true);
        g.addEdge("New York","London", true);
        g.addEdge("Delhi","Paris" , true);
        g.addEdge("Paris","New York" , true);

        g.printAdjList();

    }
}


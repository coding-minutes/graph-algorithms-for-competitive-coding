package FloodFill;

import java.util.ArrayList;
import java.util.Scanner;

public class graphInput {

    final static int N = (int) (1e5 + 1);

    static ArrayList<Integer>[] gr = new ArrayList[N];

    static {
        for(int i = 0; i < gr.length; i++){
            gr[i] = new ArrayList<>();
        }
    }

    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();

        for(int i = 0; i < m; i++){
            int x = scn.nextInt();
            int y = scn.nextInt();

            gr[x].add(y);
            gr[y].add(x);

        }
    }


}

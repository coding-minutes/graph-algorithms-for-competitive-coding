package Bonus;

import java.util.HashMap;
import java.util.HashSet;

public class BoardGame {

    static final int M= 3, N = 4;


    static class Node{

        char s;
        HashMap<Character,Node> children;
        String word;
        boolean isTerminal;

        Node(char s){
            this.s = s;
            isTerminal = false;
            children = new HashMap<>();
            word = "";
        }
    };

    static class Trie{

        Node root;

        Trie(){
            root = new Node('\0');
        }

        void addWord(String word){

            Node temp = root;
            for(int i = 0; i < word.length(); i++ ){
                char ch = word.charAt(i);
                if(!temp.children.containsKey(ch)){
                    temp.children.put(ch, new Node(ch));
                }
                temp = temp.children.get(ch);
            }
            //last node
            temp.isTerminal = true;
            temp.word = word;
        }
    };


//main algorithm (8-way dfs + trie guided search)

    static void dfs(char[][] board,Node node,int i,int j,boolean[][] visited, HashSet<String> output){

        //base case
        char ch = board[i][j];
        if(!node.children.containsKey(ch)){
            return;
        }

        //otherwise trie contains this node
        visited[i][j] = true;
        node = node.children.get(ch);


        // if it is a terminal node in the trie
        if(node.isTerminal){
            output.add(node.word);
        }

        //explore the neigbours
        int dx[] = {0, 1 , 1 ,1, 0, -1, -1,-1};
        int dy[] = {-1,-1, 0, 1, 1, 1, 0, -1};

        for(int k=0;k<8;k++){

            int ni = i + dx[k];
            int nj = j + dy[k];

            //if it is in bounds and is not visited
            if(ni>=0 && nj>=0 && ni < M && nj < N && !visited[ni][nj]){
                dfs(board,node,ni,nj,visited,output);
            }
        }
        //last step (backtracking)
        visited[i][j] = false;
        return;
    }


    public static void main(String[] args) {



        String words[] = {"SNAKE", "FOR", "QUEZ", "SNACK", "SNACKS", "GO","TUNES","CAT" };

        char[][] board = { { 'S', 'E', 'R' ,'T'},
            { 'U', 'N', 'K' ,'S'},
            { 'T', 'C', 'A' ,'T'} };

        //1. Trie

        Trie t = new Trie();
        for(String w : words){
            t.addWord(w);
        }

        //2. Take a container to store words that are found in dfs search
        HashSet<String> output = new HashSet<>();


        //3. Step (8-DFS Search from every cell)

        boolean[][] visited = new boolean[M][N] ;


        for(int i=0; i<M;i++){
            for(int j=0;j<N;j++){
                dfs(board,t.root,i,j,visited,output);
                //reset the visited array after every call (while backtracking)
            }
        }

        //4. Print the Output

        for(String word: output){
            System.out.println(word);
        }


    }
}

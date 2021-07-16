package TravellingSalesMan;

import java.util.Arrays;

public class travellingSalesMan {



    static int tsp(int[][] dist, int setOfCities, int city,int n,int[][] dp){

        //base case
        if(setOfCities == (1<<n) - 1){
            //return the cost from the city to the original
            return dist[city][0];
        }
        if(dp[setOfCities][city]!=-1){
            return dp[setOfCities][city];
        }

        //otherwise try all possible options
        int ans = Integer.MAX_VALUE;

        for(int choice=0;choice<n;choice++){
            //need to check if city is visited or not
            if((setOfCities & (1<<choice))==0){

                int subProb = dist[city][choice] + tsp(dist, setOfCities|(1<<choice),choice,n,dp);
                ans = Math.min(ans,subProb);
            }
        }
        dp[setOfCities][city] = ans;
        return ans;
    }



    public static void main(String[] args){

        int[][] dist = {
                {0,20,42,25},
                {20,0,30,34},
                {42,30,0,10},
                {25,34,10,0}
        };
        int n = 4;

        int[][] dp = new int[1 << n][n];
        for(int[] v: dp){
            Arrays.fill(v, -1);
        }

        System.out.println(tsp(dist,1,0,n,dp));


    }
}


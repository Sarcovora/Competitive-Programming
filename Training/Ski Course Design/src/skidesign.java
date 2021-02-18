/*
ID: evan_ku1
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.StringTokenizer;

class skidesign {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] hillHeight = new int[N+1]; // 1 indexed
        int minCost=999999999;
        int maxHeight = 0;
        int minHeight = 999;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            hillHeight[i] = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(hillHeight[i], maxHeight);
            minHeight = Math.min(hillHeight[i], minHeight);
        }

        for (int i = minHeight; i < maxHeight; i++) { // interval
            int lowerRange = i;
            int upperRange = i+17;
            int tempCost = 0;
            for (int j = 1; j <= N; j++) {
                if (!(hillHeight[j] > lowerRange && hillHeight[j] < upperRange)) {
                    tempCost+=Math.pow(Math.min(Math.abs(hillHeight[j]-lowerRange), Math.abs(hillHeight[j]-upperRange)), 2);
                }
            }
            minCost = Math.min(tempCost, minCost);
        }
        
        out.println(minCost);
        System.out.println(minCost);
        out.close();
        in.close();
    }
}

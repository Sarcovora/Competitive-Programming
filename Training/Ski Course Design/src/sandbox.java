public class sandbox {
    
}
/*

ID: evan_ku1
LANG: JAVA
TASK: skidesign

import java.io.*;
import java.util.StringTokenizer;

class skidesign {
    static int costChange (int N, int[] hillHeight, int minReq, int maxReq) {
        // find which hills don't meet the requirement and change them. Output Cost
        int[] changes = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int myH = hillHeight[i];
            if (myH > maxReq || myH < minReq) {
                // I need to be changed
                changes[i] = Math.min(Math.abs(myH - minReq), Math.abs(myH - maxReq));
            }
        }
        int cost = 0;
        for (int i = 1; i <= N; i++) {
            cost += changes[i] * changes[i];
        }
        return cost;
    }
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] hillHeight = new int[N+1]; // 1 indexed
        int totalCost;
        int idxMax = 0;
        int idxMin = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            hillHeight[i] = Integer.parseInt(st.nextToken());
            if (idxMax == 0) {
                idxMax = i;
            } else if (hillHeight[i] > hillHeight[idxMax]) {
                idxMax = i;
            }
            if (idxMin == 0) {
                idxMin = i;
            } else if (hillHeight[i] < hillHeight[idxMin]) {
                idxMin = i;
            }
        }
        int minReq, maxReq;
        int diff = hillHeight[idxMax] - hillHeight[idxMin];
        diff = diff-17;
        if (diff <= 0) {
            totalCost = 0;
        } else if (diff % 2 == 0) {
            // even
            minReq = hillHeight[idxMin] + (diff/2);
            maxReq = hillHeight[idxMax] - (diff/2);
            totalCost = costChange(N, hillHeight, minReq, maxReq);
        } else {
            minReq = hillHeight[idxMin] + ( (diff-1)/2);
            maxReq = hillHeight[idxMax] - ( (diff+1)/2);
            totalCost = costChange(N, hillHeight, minReq, maxReq);

            minReq = hillHeight[idxMin] + ( (diff+1)/2);
            maxReq = hillHeight[idxMax] - ( (diff-1)/2);
            totalCost = Math.min(totalCost, costChange(N, hillHeight, minReq, maxReq));
        }
        
        out.println(totalCost);
        System.out.println(totalCost);
        out.close();
        in.close();
    }
}

*/

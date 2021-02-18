/*
ID: evan_ku1
LANG: JAVA
TASK: milk2
*/

import java.io.*;
import java.util.*;

public class herding {
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("herding.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] cows = new int[3];
        for (int i = 0; i < 3; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);
        int a = cows[0];
        int b = cows[1];
        int c = cows[2];

        int minmove;
        int maxmove;

        if ( (b - a) == 1 && (c - b) == 1) {
            minmove = 0;
        } else if ( (b - a) == 2 || (c - b) == 2) {
            minmove = 1;
        } else {
            minmove = 2;
        }

        if ( (b - a) > (c - b) ) {
            maxmove = b - a - 1;
        } else {
            maxmove = c - b - 1;
        }

        out.println(minmove);
        out.println(maxmove);
        out.close();
    }
}

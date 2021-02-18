/*
ID: evan_ku1
LANG: JAVA
TASK: milk2
*/

import java.io.*;
import java.util.*;

public class milk2 {
    private static class farmer implements Comparable<farmer> {
        int startTime;
        int endTime;
        private farmer (int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        @Override
        public int compareTo(farmer f) {
            return (Integer.compare(this.startTime, f.startTime));
        }
    }
    public static boolean isOverlapping (int s2, int e1) {
        if (e1 >= s2) {
            return true;
        }
        return false;
    }
    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        int N = Integer.parseInt(st.nextToken());
        int[] startTimes = new int[N];
        int[] endTimes = new int[N];
        farmer[] farmers = new farmer[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            startTimes[i] = Integer.parseInt(st.nextToken());
            endTimes[i] = Integer.parseInt(st.nextToken());
            farmers[i] = new farmer(startTimes[i], endTimes[i]);
        }
        Arrays.sort(farmers);
        for (int i = 0; i < N; i++) {
            startTimes[i] = farmers[i].startTime;
            endTimes[i] = farmers[i].endTime;
            //System.out.printf("%d %d\n",startTimes[i],endTimes[i]);
        }

        int maxtimemilk = endTimes[0] - startTimes[0];
        int maxgap = 0;

        ArrayList<farmer> farmers2 = new ArrayList<farmer>();
        farmers2.add(new farmer(startTimes[0], endTimes[0]));

        for (int i = 1; i < N; i++) {

            if (isOverlapping(farmers[i].startTime, farmers2.get(farmers2.size() - 1).endTime) ) {
                if (farmers[i].endTime > farmers2.get(farmers2.size() - 1).endTime) {
                    farmers2.get(farmers2.size() - 1).setEndTime(farmers[i].endTime);
                }
            }
            else {
                //System.out.println("should not be here!");
                int endTime = farmers2.get(farmers2.size() - 1).endTime;
                int length = endTime - farmers2.get(farmers2.size() - 1).startTime;
                if (length > maxtimemilk) {
                    maxtimemilk = length;
                }
                farmers2.add(new farmer(startTimes[i], endTimes[i]));
                int gap = farmers2.get(farmers2.size() - 1).startTime - endTime;
                if (gap > maxgap) {
                    maxgap = gap;
                }
            }
        }
        out.println(maxtimemilk + " " + maxgap);
        out.close();
    }
}


/*
ID: evan_ku1
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class barn1 {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int maxBoards = Integer.parseInt(st.nextToken());
        int numStalls = Integer.parseInt(st.nextToken());
        int numCows = Integer.parseInt(st.nextToken());
        int[] stalls = new int[numStalls+1];
        int stallCnt;

        for (int i = 0; i < numCows; i++) {
            st = new StringTokenizer(in.readLine());
            stalls[Integer.parseInt(st.nextToken())] = 1;
        }

        int leftmostCow = 0;
        int rightmostCow = 0;
        for (int i = 1; i < stalls.length; i++) {
            if (leftmostCow == 0 && stalls[i] == 1) {
                leftmostCow = i;
            }
            if (stalls[i] == 1) {
                rightmostCow = i;
            }
        }
        stallCnt = rightmostCow - leftmostCow + 1;

        List<Integer> gaps = new ArrayList<>();
        List<Integer> gapvals = new ArrayList<>();
        List<Integer> Gapidxs1 = new ArrayList<>();
        List<Integer> Gapidxs2 = new ArrayList<>();
        for (int i = leftmostCow; i < rightmostCow; i++) {
            if (stalls[i] == 1) continue;
            for (int j = i+1; j <= rightmostCow; j++) {
                if (stalls[j] == 1) {
                    gaps.add(j-i);
                    gapvals.add(j-i);
                    Gapidxs1.add(i);
                    Gapidxs2.add(j);
                    i = j;
                    break;
                }
            }
        }
        Collections.sort(gaps);
        Collections.reverse(gaps);
        if (maxBoards >= numCows) {
            out.println(numCows);
        } else {
            for (int i = 0; i < maxBoards - 1; i++) {
                stallCnt -= gaps.get(i);
            }
            out.println(stallCnt);
        }
        out.close();
    }
}
/*
ID: evan_ku1
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

class combo {
    static int[] overlap (int N, int i, int k) { // possible combos, overlap
        HashSet<Integer> pos1 = new HashSet<>();
        HashSet<Integer> pos2 = new HashSet<>();
        HashSet<Integer> pos3 = new HashSet<>();
        for (int j = i-2; j <= i+2; j++) {
            if (j < 1) {
                pos1.add(N+j);
            } else if (j > N) {
                pos1.add(j-N);
            } else {
                pos1.add(j);
            }
        }
        for (int j = k-2; j <= k+2; j++) {
            if (j < 1) {
                pos2.add(N+j);
            } else if (j > N) {
                pos2.add(j-N);
            } else {
                pos2.add(j);
            }
        }
        int possCombos = pos1.size() * 2;
        pos3.addAll(pos1);
        pos3.addAll(pos2);
        return new int[] {pos1.size(), possCombos-pos3.size()};
    }
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        String[] line1 = in.readLine().split(" ");
        String[] line2 = in.readLine().split(" ");
        int[] combo = new int[6]; // combo 1: 0,1,2 combo 2: 3,4,5
        int overlap;
        for (int i = 0; i < 3; i++) {
            combo[i] = Integer.parseInt(line1[i]);
            combo[i+3] = Integer.parseInt(line2[i]);
        }
        int[] o1 = overlap(N, combo[0], combo[3]);
        int[] o2 = overlap(N, combo[1], combo[4]);
        int[] o3 = overlap(N, combo[2], combo[5]);
        overlap = o1[1] * o2[1] * o3[1];
        System.out.println(o1[1] + ", " + o2[1] + ", " + o3[1]);

        out.println(N == 1? 1 : (o1[0] * o2[0] * o3[0] * 2)-overlap);
        out.close();
    }
}

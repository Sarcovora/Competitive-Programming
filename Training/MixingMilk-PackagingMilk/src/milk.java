/*
ID: evan_ku1
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class milk {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        farmer[] farmers = new farmer[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            farmers[i] = new farmer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(farmers, new farmerSortByCent());

        int amtSpent = 0;
        int milkCollected = 0;

        for (int i = 0; i < M; i++) {
            if (milkCollected == N) break;
            if ((N - milkCollected) - farmers[i].amt >= 0) {
                amtSpent += farmers[i].cent*farmers[i].amt;
                milkCollected += farmers[i].amt;
            } else {
                amtSpent += farmers[i].cent * (N-milkCollected);
                milkCollected += (N-milkCollected);
            }
        }
        out.println(amtSpent);
        out.close();
    }
    static class farmer {
        int cent, amt;
        public farmer(int cent, int amt) {
            this.cent = cent;
            this.amt = amt;
        }
    }
    static class farmerSortByCent implements Comparator<farmer> {
        @Override
        public int compare(farmer o1, farmer o2) {
            return o1.cent - o2.cent;
        }
    }
}
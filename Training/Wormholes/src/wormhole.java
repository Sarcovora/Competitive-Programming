/*
ID: evan_ku1
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.StringTokenizer;

class wormhole {
    static boolean isCycle (int N, int[] X, int[] Y, int[] partner, int[] right) {
        // go through all possible start positions
        /*
        for (int i = 1; i <= N; i++) {
            System.out.println(i + " : " + partner[i] + " : " + right[i]);
        }
        System.out.println("-------------------------");
         */
        for (int start = 1; start <= N; start++) {
            // is there a cycle is if I start here?
            int pos = start;
            for (int steps = 0; steps < N; steps++) {
                pos = right[partner[pos]];
            }
            if (pos != 0) {
                return true;
            }
        }
        return false;
    }
    static int solve (int N, int[] X, int[] Y, int[] partner, int[] right) {
        int cnt = 0;
        int i;
        for (i = 1; i<=N; i++) // first unpaired wormhole
            if (partner[i] == 0) break;
        // is everyone paired?
        if (i>N) {
            return ( isCycle(N, X, Y, partner, right) ? 1 : 0);
        }

        // pair all i with another hole
        for (int j = i+1; j <= N; j++) {
            if (partner[j] != 0) continue;
            partner[i] = j;
            partner[j] = i;
            cnt += solve(N, X, Y, partner, right);
            partner[i] = 0;
            partner[j] = 0;
        }
        return cnt;
    }
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] X = new int[N+1];
        int[] Y = new int[N+1];
        int[] partner = new int[N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            X[i+1] = Integer.parseInt(st.nextToken());
            Y[i+1] = Integer.parseInt(st.nextToken());
        }
        int[] right = new int[N+1];
        // code for array to the right
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (X[i] == X[j] && Y[i] == Y[j]) continue;
                if (Y[i] == Y[j] && X[i] < X[j]) {
                    if (right[i] == 0) {
                        right[i] = j;
                    } else if ( (X[j] - X[i]) < (X[right[i]] - X[i]) ) {
                        right[i] = j;
                    }
                }
            }
        }
        out.println(solve(N, X, Y, partner, right));
        out.close();
    }
}
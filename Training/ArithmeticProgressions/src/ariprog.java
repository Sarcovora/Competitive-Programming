/*
ID: evan_ku1
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class ariprog {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken()); //Length of sequence
        st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken()); //Limit of bisquares
        List<Integer> starts = new ArrayList<>();
        List<Integer> diffs = new ArrayList<>();
        //ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        int max = M*M*2;
        boolean[] numbers = new boolean[max+1];
        for (int p = 0; p <= M; p++) {
            for (int q = 0; q <= M; q++) {
                numbers[p * p + q * q] = true;
            }
        }
        double a = N-1;
        for (int diff = 1; diff <= ( Math.ceil(max / a )); diff++) {
            for (int start = 0; start <= max-(diff*(N-1)); start++) {
                if (start+((N-1)*diff)>max) break;
                if (!numbers[start]) continue;
                boolean isBad = false;
                for (int eCnt = 0; eCnt < N; eCnt++) {
                    int element = start+(eCnt*diff);
                    if (!numbers[element]) {
                        isBad = true;
                        break;
                    }
                }
                if (!isBad) {
                   starts.add(start);
                   diffs.add(diff);
                   //ArrayList<Integer> temp = new ArrayList<>();
                   //temp.add(start);
                   //temp.add(diff);
                   //results.add(temp);
                }
                // if (!isBad) {
                //    ArrayList<Integer> temp = new ArrayList<>();
                //    temp.add(start);
                //    temp.add(diff);
                //    results.add(temp);
                // }
            }
        }
        if (starts.size() == 0) {
            out.println("NONE");
            //System.out.println("NONE");
        } else {
            for (int i = 0; i < starts.size(); i++) {
                int start = starts.get(i);
                int diff = diffs.get(i);
                out.println(start + " " + diff);
                //System.out.println(start + " " + diff);
            }
        }
        // if (results.size() == 0) {
        //     out.println("NONE");
        // } else {
        //     for (ArrayList<Integer> t : results) {
        //         out.println(t.get(0) + " " + t.get(1));
        //     }
        // }
        out.close();
        in.close();
    }
}

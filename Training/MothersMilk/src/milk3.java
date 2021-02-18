/*
ID: evan_ku1
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

class milk3 {
    static int[] buckets = new int[3]; // current milk in buckets
    static int[] cap = new int[3]; // max capacity
    static int idA = 0;
    static int idB = 1;
    static int idC = 2;
    static HashSet<Integer> outputs = new HashSet<Integer>();
    static HashSet<Integer> states = new HashSet<Integer>();

    static void pour (int idxfrom, int idxto) {
        if (buckets[idxfrom] == 0) {
            return;
        }
        if (buckets[idxfrom]+buckets[idxto] > cap[idxto]) {
            int temp = buckets[idxto];
            buckets[idxto] = cap[idxto];
            buckets[idxfrom] -= cap[idxto]-temp;
        } else {
            buckets[idxto] += buckets[idxfrom];
            buckets[idxfrom] = 0;
        }
//        if (buckets[0] + buckets[1] + buckets[2] != 10) {
//            System.out.println("------------                ------------");
//        }
    }
    static void reset (int mA, int mB, int mC) {
        buckets[0] = mA;
        buckets[1] = mB;
        buckets[2] = mC;
    }
    static void solve (int from, int to) {
        //System.out.println("------------------from: " + from + " to: " + to + "      A: " + buckets[0] + "    B: " + buckets[1] + "    C: "  + buckets[2]);
//        if (from == 0 && to == 1 && buckets[1] == 8 && buckets[2] == 2) {
//            System.out.println("Here");
//
//        }
        // pour
        if (from != -1 && to != -1) {
            pour(from, to);
        }

        int milkA = buckets[0];
        int milkB = buckets[1];
        int milkC = buckets[2];
        //System.out.println("Pour from: " + from + " to: " + to + "      A: " + milkA + "    B: " + milkB + "    C: "  + milkC);

//        if (milkA + milkB + milkC != 10) {
//            System.out.println("---------------------------------------");
//        }
        //assert (milkA + milkB + milkC == 10);

        // base case: if we run into a case we had before
        boolean bool = states.add(milkA*441 + milkB*21 + milkC);
        if (!bool) {
            return;
        }
        if (milkA == 0) {
            boolean isUnique = outputs.add(milkC);
            if (!isUnique) {
                return;
            }
        }

        // recursive case: pour all 6 possible pours
        solve(idA, idB);
        reset(milkA, milkB, milkC);

        solve(idA, idC);
        reset(milkA, milkB, milkC);   

        solve(idB, idA);
        reset(milkA, milkB, milkC);

        solve(idB, idC);
        reset(milkA, milkB, milkC);

        solve(idC, idA);
        reset(milkA, milkB, milkC);

        solve(idC, idB);
        reset(milkA, milkB, milkC);
    }
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        cap[0] = Integer.parseInt(st.nextToken());
        cap[1] = Integer.parseInt(st.nextToken());
        cap[2] = Integer.parseInt(st.nextToken());
        buckets[2] = cap[2];
        solve(-1, -1);
        List<Integer> temp = new ArrayList<Integer>(outputs);
        Collections.sort(temp);
        String output = String.valueOf(temp.get(0));
        for (int i = 1; i < temp.size(); i++) {
            output += " " + temp.get(i);
        }
        //System.out.println(output);
        out.println(output);
        out.close();
        in.close();
    }
//    class state {
//        int A, B, C;
//
//        public state(int a, int b, int c) {
//            A = a;
//            B = b;
//            C = c;
//        }
//        @Override
//        public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + A;
//            result = prime * result + B;
//            result = prime * result + C;
//            return result;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj)
//                return true;
//            if (obj == null)
//                return false;
//            if (getClass() != obj.getClass())
//                return false;
//            state other = (state) obj;
//            if (move1 != other.state)
//                return false;
//            if (move2 != other.move2)
//                return false;
//            return true;
//        }
//    }
}
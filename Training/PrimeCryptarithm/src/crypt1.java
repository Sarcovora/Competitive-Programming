/*
ID: evan_ku1
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.StringTokenizer;

class crypt1 {
    static boolean contains (int[] ary, String a) {
        for (int i : ary)
            if (i == Integer.parseInt(a)) return true;
        return false;
    }
    static boolean valid (int[] digits, String str) {
        String[] num = str.split("");
        for (String s : num) {
            if (!contains(digits, s)) {
                return false;
            }
        }
        return true;
    }
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        String[] line = in.readLine().split(" ");
        int[] digits = new int[N];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            digits[i] = Integer.parseInt(line[i]);
        }
        for (int a = 100; a <= 999; a++) {
            if (!valid(digits, Integer.toString(a))) continue;
            for (int b = 10; b < 99; b++) {
                String[] numB = Integer.toString(b).split("");
                if (!valid(digits, Integer.toString(b))) continue;
                String pp1 = Integer.toString(a * Integer.parseInt(numB[1]));
                String pp2 = Integer.toString(a * Integer.parseInt(numB[0]));
                String val = Integer.toString(a*b);
                if (pp1.length() > 3 || pp2.length() > 3) continue;
                if (!valid(digits, pp1) || !valid(digits, pp2) || !valid(digits, val)) continue;
                //System.out.println("A: " + a + " B: " + b + " pp1: " + pp1 + " pp2: " + pp2 + " val: " + val);
                cnt++;
            }
        }
        out.println(cnt);
        out.close();
    }
}

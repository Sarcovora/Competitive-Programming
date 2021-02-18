/*
ID: evan_ku1
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

class dualpal {
    static String convertInteger(String theValue, int finalBase) {
        return new BigInteger(theValue, 10).toString(finalBase).toUpperCase();
    }
    static boolean isPalindrome (String num) {
        for (int i = 0; i < num.length(); i++) {
            if (!(num.charAt(i) == num.charAt((num.length() - 1) - i))) {
                return false;
            }
        }
        return true;
    }
    static boolean palInTwoOrMoreBases (int num) {
        int cnt = 0;
        for (int base = 2; base <= 10; base++) {
            if (isPalindrome(convertInteger(Integer.toString(num), base))) cnt++;
            if (cnt == 2) return true;
        }
        return false;
    }
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int foundCnt = 0;
        int i = 1;
        while (foundCnt < N) {
            if (palInTwoOrMoreBases(S + i)) {
                foundCnt++;
                out.println(S+i);
            }
            i++;
        }

        out.close();
    }
}
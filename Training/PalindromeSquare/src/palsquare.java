/*
ID: evan_ku1
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.math.BigInteger;

class palsquare {
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
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        int base = Integer.parseInt(in.readLine());

        for (int i = 1; i <= 300; i++) {
            int squared = (int)Math.pow(i, 2);
            if (isPalindrome(convertInteger(Integer.toString(squared), base))) {
                out.println(convertInteger(Integer.toString(i), base) + " " +
                                   convertInteger(Integer.toString(squared), base));
            }
        }
        out.close();
    }
}
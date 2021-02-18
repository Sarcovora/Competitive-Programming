/*
ID: evan_ku1
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

class transform {
    static String[][] rotate90 (int N, String[][] given_pattern) {
        String[][] rotated_pattern = new String[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated_pattern[i][j] = given_pattern[N-1-j][i];
            }
        }
        return rotated_pattern;
    }
    static String[][] reflect (int N, String[][] given_pattern) {
        String[][] reflected_pattern = new String[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                reflected_pattern[i][j] = given_pattern[i][N-j-1];
            }
        }
        return reflected_pattern;
    }
    static boolean same (int N, String[][] ary1, String[][] ary2) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (!ary1[row][col].equals(ary2[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        String[][] before = new String[N][N];
        String[][] after = new String[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(f.readLine());
            before[row] = st.nextToken().split("");
        }
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(f.readLine());
            after[row] = st.nextToken().split("");
        }
        String[][] combo90 = rotate90(N, reflect(N, before));
        String[][] combo180 = rotate90(N, rotate90(N, reflect(N, before)));
        String[][] combo270 = rotate90(N, rotate90(N, rotate90(N, reflect(N, before))));
        if (same(N, rotate90(N, before), after))
            out.println(1);
        else if (same(N, rotate90(N,rotate90(N, before)), after))
            out.println(2);
        else if (same(N, rotate90(N, rotate90(N,rotate90(N, before))), after))
            out.println(3);
        else if (same(N, reflect(N, before), after))
            out.println(4);
        else if (same(N, combo90, after) || same(N, combo180, after) || same(N, combo270, after))
            out.println(5);
        else if (same(N, before, after))
            out.println(6);
        else
            out.println(7);
        out.close();
    }
}
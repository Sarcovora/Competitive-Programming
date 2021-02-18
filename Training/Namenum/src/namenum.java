/*
ID: evan_ku1
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

/*
Bring in needed TTKeys
Go through dict and check if:
    -Same digits
    -Check if digits correspond to TTKeys that are available.
 */
class namenum {
    public static void main (String [] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
        BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        String num = in.readLine();
        String[] TTKey = new String[]{"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PRS", "TUV", "WXY"};
        String[] NeededTTKeys = new String[num.length()];
        List<String> names = new ArrayList<>();
        for (int i = 0; i < num.length(); i++) {
            NeededTTKeys[i] = TTKey[Integer.parseInt(String.valueOf(num.charAt(i)))];
        }
        String name;
        while ((name = dict.readLine()) != null) {
            boolean valid = true;
            if (name.length() == num.length()) {
                for (int i = 0; i < name.length(); i++) {
                    if (NeededTTKeys[i].indexOf(name.charAt(i)) == -1) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    names.add(name);
                }
            }
        }
        if (names.size() == 0) {
            System.out.println("NONE");
            out.println("NONE");
        }
        for (String str : names) {
            System.out.println(str);
            out.println(str);
        }
        out.close();
    }
}
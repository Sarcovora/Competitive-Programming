import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class LittleJhool {
    public static void main (String[] args) throws IOException {
        Scan in = new Scan();
        String[] input = in.scanString().split("");
        int consecutive1 = 0;
        int consecutive0 = 0;
        boolean finished = false;
        for (String s : input) {
            if (consecutive0 == 6 || consecutive1 == 6) {
                finished = true;
                System.out.println("Sorry, sorry!");
                break;
            }
            if (consecutive0 == 0) {
                if (Integer.parseInt(s) == 1) {
                    consecutive1++;
                } else {
                    consecutive1 = 0;
                    consecutive0++;
                }
            } else {
                if (Integer.parseInt(s) == 0) {
                    consecutive0++;
                } else {
                    consecutive0 = 0;
                    consecutive1++;
                }
            }
        }
        if (!finished) {
            System.out.println("Good luck!");
        }
    }
}

class Scan {
    private InputStream in;
    private byte[] buf = new byte[1024 * 1024];
    private int index;
    private int total;

    public Scan() {
        in = System.in;
    }

    public int scan() throws IOException {
        if (total < 0)
            throw new InputMismatchException();
        if (index >= total) {
            index = 0;
            total = in.read(buf);
            if (total <= 0)
                return -1;
        }
        return buf[index++];
    }

    public int scanInt() throws IOException {
        int integer = 0;
        int n = scan();
        while (isWhiteSpace(n))
            n = scan();
        int neg = 1;
        if (n == '-') {
            neg = -1;
            n = scan();
        }
        while (!isWhiteSpace(n)) {
            if (n >= '0' && n <= '9') {
                integer *= 10;
                integer += n - '0';
                n = scan();
            } else
                throw new InputMismatchException();
        }
        return neg * integer;
    }

    public long scanLong() throws IOException {
        long integer = 0;
        int n = scan();
        while (isWhiteSpace(n))
            n = scan();
        int neg = 1;
        if (n == '-') {
            neg = -1;
            n = scan();
        }
        while (!isWhiteSpace(n)) {
            if (n >= '0' && n <= '9') {
                integer *= 10;
                integer += n - '0';
                n = scan();
            } else
                throw new InputMismatchException();
        }
        return neg * integer;
    }

    public double scanDouble() throws IOException {
        double doub = 0;
        int n = scan();
        while (isWhiteSpace(n))
            n = scan();
        int neg = 1;
        if (n == '-') {
            neg = -1;
            n = scan();
        }
        while (!isWhiteSpace(n) && n != '.') {
            if (n >= '0' && n <= '9') {
                doub *= 10;
                doub += n - '0';
                n = scan();
            } else
                throw new InputMismatchException();
        }
        if (n == '.') {
            n = scan();
            double temp = 1;
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    temp /= 10;
                    doub += (n - '0') * temp;
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
        }
        return doub * neg;
    }

    public String scanString() throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = scan();
        while (isWhiteSpace(n))
            n = scan();
        while (!isWhiteSpace(n)) {
            sb.append((char) n);
            n = scan();
        }
        return sb.toString();
    }

    private boolean isWhiteSpace(int n) {
        if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
            return true;
        return false;
    }
}
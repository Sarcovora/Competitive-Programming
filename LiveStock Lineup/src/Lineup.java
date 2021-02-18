import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Lineup {
    static void orderCows (ArrayList<String> cows) {
        cows.add("");
        cows.add("Beatrice");
        cows.add("Belinda");
        cows.add("Bella");
        cows.add("Bessie");
        cows.add("Betsy");
        cows.add("Blue");
        cows.add("Buttercup");
        cows.add("Sue");
    }
    static boolean checkRepeat (int[] order) {
        HashSet<Integer> temp = new HashSet<>();
        for (int i : order) {
            if (!temp.add(i)) {
                return false;
            }
        }
        return true;
    }
    static boolean check (int[] order, int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < order.length-1; j++) {
                if ( !(order[j] == a[i] && order[j+1] == b[i]) || !(order[j] == b[i] && order[j+1] == a[i]) )
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader("1.in");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
        int N = in.nextInt();
        ArrayList<String> cows = new ArrayList<>(9);
        orderCows(cows);
        int[] a = new int[N];
        int[] b = new int[N];

        for (int i = 0; i < N; i++) {
            String line = in.readLine();
            line = line.replace("\n", "");
            line = line.replace("\r", "");
            String[] ary = line.split(" ");
            a[i] = cows.indexOf(ary[0]);
            b[i] = cows.indexOf(ary[5]);
        }
        boolean end = false; // hi i am here
        int[] order = new int[8];
        for (int i = 1; i < 9; i++) { // first slot
            order = new int[8];
            order[0] = i;
            for (int j = 1; j < 9; j++) { // second slot
                order[1] = j;
                for (int k = 1; k < 9; k++) { // third slot
                    order[2] = k;
                    for (int l = 1; l < 9; l++) { // fourth slot
                        order[3] = l;
                        for (int m = 1; m < 9; m++) { // fifth slot
                            order[4] = m;
                            for (int n = 1; n < 9; n++) { // sixth slot
                                order[5] = n;
                                for (int o = 1; o < 9; o++) { // seventh slot
                                    order[6] = o;
                                    for (int p = 1; p < 9; p++) { // eighth slot
                                        order[7] = p;
                                        if (checkRepeat(order)) {
                                            continue;
                                        }
                                        if (check(order, a, b)) {
                                            end = true;
                                            break;
                                        }
                                    }
                                    if (end) {
                                        break;
                                    }
                                }
                                if (end) {
                                    break;
                                }
                            }
                            if (end) {
                                break;
                            }
                        }
                        if (end) {
                            break;
                        }
                    }
                    if (end) {
                        break;
                    }
                }
                if (end) {
                    break;
                }
            }
            if (end) {
                break;
            }
        }
        System.out.println(N);
        out.println(N);
        out.close();
        in.close();
    }
    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length was 64 BUG WITH LARGE LINES
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}

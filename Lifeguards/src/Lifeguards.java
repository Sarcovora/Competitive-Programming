// Evan Kuo December 13, 2020
// USACO 2018 January Contest, Bronze Problem 2. Lifeguards
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Lifeguards {
    static int calcTime (int remIdx, Lifeguard[] l) {
        int time = 0;
        int prevEnd = 0;
        for (int i = 0; i < l.length; i++) {
            if (i == remIdx) continue;
            if (prevEnd > l[i].start) { // Overlap
                time += l[i].end - prevEnd;
            } else {
                time += l[i].end - l[i].start;
            }
            prevEnd = l[i].end;
        }
        return time;
    }
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader("lifeguards.in");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
        int N = in.nextInt();
        Lifeguard[] guards = new Lifeguard[N];
        int maxTime = 0;

        for (int i = 0; i < N; i++) {
            guards[i] = new Lifeguard(in.nextInt(), in.nextInt());
        }
        Arrays.sort(guards, new sortGuards());
        for (int i = 0; i < N; i++) {
            //System.out.println(calcTime(i, guards));
            maxTime = Math.max(calcTime(i, guards), maxTime);
        }

        System.out.println(maxTime);
        out.println(maxTime);
        out.close();
        in.close();
    }

    static class Lifeguard {
        int start, end;

        public Lifeguard (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class sortGuards implements Comparator<Lifeguard> {
        @Override
        public int compare(Lifeguard o1, Lifeguard o2) {
            return o1.start - o2.start;
        }
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

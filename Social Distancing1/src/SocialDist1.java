import java.io.*;

public class SocialDist1 {
    static int findD (int[] stalls) {
        int max = stalls.length;
        int currCnt = 0;
        for (int stall : stalls) {
            if (stall == 0) {
                currCnt += 1;
            } else {
                if (currCnt != 0) {
                    max = Math.min(max, currCnt);
                    currCnt = 0;
                }
            }
        }
        return max;
    }
    static int findDalt (int[] stalls) {
        int D = stalls.length;
        // min value of d
        int first1 = -1;
        for (int i = 0; i < stalls.length; i++) {
            if (stalls[i] == 1) {
                if (first1 == -1) {
                    first1 = i;
                } else {
                    D = Math.min(D, i - first1);
                    first1 = -1;
                }
            }
        }
        if (first1 != -1) {
            D = Math.min(D, stalls.length - first1);
        }
        return D;
    }

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader("socdist1.in");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist1.out")));
        int maxD = 0;

        int N = in.nextInt();
        String[] str = in.readLine().split("");
        int[] stalls = new int[N];
        for (int i = 0; i < N; i++) {
            stalls[i] = Integer.parseInt(str[i]);
        }

        //double for loop with cow placements
        for (int i = 0; i < N; i++) {
            if (stalls[i] == 1) {
                continue;
            }

            int[] stallsCopy = new int[N];
            System.arraycopy(stalls, 0, stallsCopy, 0, N);

            stallsCopy[i] = 1;
            for (int j = 0; j < N; j++) {
                if (stallsCopy[j] == 1) {
                    continue;
                }

                int[] stallsCopy2 = new int[N];
                System.arraycopy(stalls, 0, stallsCopy2, 0, N);

                stallsCopy2[j] = 1;
                maxD = Math.max(maxD, findD(stallsCopy2));
            }
        }

        System.out.println(maxD);
        out.println(maxD);
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
            byte[] buf = new byte[100000]; // line length was 64 BUG WITH LARGE LINES
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

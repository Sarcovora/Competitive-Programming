// Evan Kuo July 7, 2020
// USACO 2017 December Contest, Bronze Problem 2. The Bovine Shuffle

import java.io.*;

public class Shuffle {
    static int[] shuffle (int[] shuffleOrder, int[] initOrder) {
        int[] output1 = new int[initOrder.length];
        int[] output2 = new int[initOrder.length];
        int[] output3 = new int[initOrder.length];
        for (int i = 0; i < initOrder.length; i++) {
            output1[shuffleOrder[i]-1] = initOrder[i];
        }
        for (int i = 0; i < output1.length; i++) {
            output2[shuffleOrder[i]-1] = output1[i];
        }
        for (int i = 0; i < output2.length; i++) {
            output3[shuffleOrder[i]-1] = output2[i];
        }
        return output3;
    }
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader("shuffle.in");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
        int N = in.nextInt();
        int[] shuffleOrder = new int[N];
        for (int i = 0; i < N; i++) shuffleOrder[i] = in.nextInt();
        int[] initOrder = new int[N];
        for (int i = 0; i < N; i++) initOrder[i] = in.nextInt();

        int[] backwardsShuffleOrder = new int[N];
        for (int i = 0; i < N; i++) {
            backwardsShuffleOrder[shuffleOrder[i]-1] = i+1; // +1 and -1 are for indexing since we start at 0
                                                            // this works by undoing whatever is done when you shuffle
        }
        int[] output3 = shuffle(backwardsShuffleOrder, initOrder);
        for (int value : output3) {
            out.println(value);
        }
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

import java.io.*;

public class MixMilk {

    static int[] pour (int cap2, int milk1, int milk2) {
        int amtleft2 = (cap2-milk2);
        if ( amtleft2 < milk1 ) {
            milk1-=amtleft2;
            milk2 = cap2;
        }
        else {
            milk2 += milk1;
            milk1 = 0;
        }
        return (new int[]{milk1, milk2});
    }

    public static void main (String[] args) throws IOException {
        FastReader s;
        s = new FastReader("mixmilk.in");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
        int cap1 = s.nextInt();
        int milk1 = s.nextInt();
        int cap2 = s.nextInt();
        int milk2 = s.nextInt();
        int cap3 = s.nextInt();
        int milk3 = s.nextInt();
        int[] result;
        for (int i = 0; i < 33; i++) {
            result = pour(cap2, milk1, milk2);
            milk1 = result[0];
            milk2 = result[1];

            result = pour(cap3, milk2, milk3);
            milk2 = result[0];
            milk3 = result[1];

            result = pour(cap1, milk3, milk1);
            milk3 = result[0];
            milk1 = result[1];
        }
        result = pour(cap2, milk1, milk2);
        milk1 = result[0];
        milk2 = result[1];

        out.println(milk1);
        out.println(milk2);
        out.println(milk3);
        out.close();
        s.close();
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
            byte[] buf = new byte[64]; // line length
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

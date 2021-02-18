import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Photo {
    static boolean isBad (int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int e : array) {
            if (e < 1)
                return true;
            set.add(e);
        }
        if (array.length != set.size()) {
            return true;
        }
        return false;
    }
    public static void main (String[] args) throws IOException {
        FastReader s = new FastReader("photo.in");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));
        int N = s.nextInt();
        int[] a = new int[N];
        int[] b = new int[N];
        for (int i = 1; i < N; i++) { //Initialize b
            b[i] = s.nextInt();
        }
        for (int i = 1; i < b[1]; i++) { // going through each possible case
            a[0] = i;
            for (int j = 1; j < N; j++) { // seeing if i works as a starter
                a[j] = b[j] - a[j-1];
            }
            if (isBad(a)) {
                continue;
            }
            break;
        }
        String str = "";
        for (int i : a) {
            str += i + " ";
        }
        out.println(str.substring(0, str.length()-1));
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

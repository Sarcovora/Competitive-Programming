import java.io.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class Measurement {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader("3.in");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
        int N = in.nextInt();
        int BessieMilk = 7;
        int ElsieMilk = 7;
        int MildredMilk = 7;
        PriorityQueue<day> entries = new PriorityQueue<>(N, new dayComparator());
        for (int i = 0; i < N; i++) {
            String line = in.readLine();
            line = line.replace("\n", "");
            line = line.replace("\r", "");
            String[] temp = line.split(" ");

            int day = Integer.parseInt(temp[0]);
            String name = temp[1];
            int outputChange = (temp[2].startsWith("+") ? Integer.parseInt(temp[2].substring(1)) : -Integer.parseInt(temp[2].substring(1)));

            entries.add(new day(day, name, outputChange));
        }
        String maxName = "init";
        int maxOutput = 7;
        int count = 0;
        for (int i = 0; i < N; i++) {
            day current = Objects.requireNonNull(entries.poll());
            switch (current.name) {
                case "Bessie":
                    BessieMilk += current.change;
                    if (maxName.equals("Bessie"))
                        maxOutput = BessieMilk;
                    break;
                case "Elsie":
                    ElsieMilk += current.change;
                    if (maxName.equals("Elsie"))
                        maxOutput = ElsieMilk;
                    break;
                case "Mildred":
                    MildredMilk += current.change;
                    if (maxName.equals("Mildred"))
                        maxOutput = MildredMilk;
                    break;
            }
            boolean tied = false;
            if (BessieMilk >= maxOutput) {
                if (!maxName.equals("Bessie") && BessieMilk != maxOutput) {
                    maxOutput = BessieMilk;
                    count++;
                    maxName = "Bessie";
                    continue;
                }
                if (!maxName.equals("Bessie") && !maxName.equals("init")) {
                    tied = true;
                    count++;
                }
            }
            if (ElsieMilk >= maxOutput && !tied) {
                if (!maxName.equals("Elsie") && ElsieMilk != maxOutput) {
                    maxOutput = ElsieMilk;
                    count++;
                    maxName = "Elsie";
                }
                if (!maxName.equals("Elsie") && !maxName.equals("init")) {
                    tied = true;
                    count++;
                }
            }
            if (MildredMilk >= maxOutput && !tied) {
                maxOutput = MildredMilk;
                if (!maxName.equals("Mildred")) {
                    count++;
                    maxName = "Mildred";
                }
            }
        }
        //System.out.println(count);
        out.println(count);
        out.close();
        in.close();
    }

    static class day {
        int day, change;
        String name;
        public day (int day, String name, int change) {
            this.day = day;
            this.change = change;
            this.name = name;
        }
    }
    static class dayComparator implements Comparator<day> {
        @Override
        public int compare(day o1, day o2) {
            return o1.day - o2.day;
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

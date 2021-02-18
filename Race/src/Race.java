import java.io.*;

public class Race {
    static int run (int speed, int x, int dist, int traveled, int time, String status) {
        if (traveled >= dist || time > dist) { // finished the race or run out of time
            if (speed > x)
                return Integer.MAX_VALUE;
//            if (time < 7)
//                System.out.println(status);
            return time;
        }
        //System.out.printf("(Inc sp: %d tr: %d ti: %d) ", speed+1, traveled+speed+1, time+1);
        int increaseSpeed = run(speed+1, x, dist, traveled+speed+1, time+1, status + String.format("(Inc sp: %d tr: %d ti: %d) ", speed+1, traveled+speed+1, time+1) );
        int keepSpeed = run(speed, x, dist, traveled+speed, time+1, status + String.format("(kee sp: %d tr: %d ti: %d) ", speed, traveled+speed, time+1) );
        int minusSpeed;
        if (speed <= 0) {
            minusSpeed = Integer.MAX_VALUE;
        } else {
            minusSpeed = run(speed - 1, x, dist, traveled+(speed-1), time + 1, status + String.format("(minus sp: %d tr: %d ti: %d) ", speed-1, traveled+speed, time+1) );
        }

        return Math.min(Math.min(keepSpeed, increaseSpeed), minusSpeed);
    }

    static int neededDist (int currSpeed, int x) {
        int diffinspeed = currSpeed - x;
        int distNeeded = 0;
        for (int i = 0; i < diffinspeed; i++) {
            currSpeed--;
            distNeeded+=currSpeed;
        }
        return distNeeded;
    }

    static int solve (int k, int x) {
        int leftDist = 0;
        int rightDist = 0;
        int time = 0;
        for (int speed = 1;; speed++) {
            leftDist += speed;
            time++;
            if (leftDist + rightDist >= k)
                return time;
            if (speed >= x) {
                rightDist += speed;
                time++;
                if (leftDist + rightDist >= k)
                    return time;
            }
        }
    }
    //Go up to max speed and either plateau or go up then down. Once at plateau figure
    //out distance left then calculate from there.

    public static void main (String[] args) throws IOException {
        FastReader s = new FastReader("race.in");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race.out")));
        int K = s.nextInt();
        int N = s.nextInt();
        for (int i = 0; i < N; i++) {
            int x = s.nextInt();
            out.println(solve(K, x));
        }

        //Below was in forloop for recursive function
        //int finishSpeed = xs[i];
        //out.println(run(0, finishSpeed, K, 0, 0, ""));

//        int currSpeed = 0;
//        int traveled = 0;
//        int time = 0;
//        for (int j = 0; j < x; j++) { // getting up to speed
//            //increase speed
//            if (traveled >= K) {
//                break;
//            }
//            currSpeed++;
//            time++;
//            traveled+=currSpeed;
//        }
//        while (traveled <= K) {
//            int distLeft = K-traveled;
//            if (distLeft >= neededDist(currSpeed, x)) { //bug need to fix
//                currSpeed++;
//            }
//            else {
//                if (currSpeed > x) {
//                    if (distLeft >= neededDist(currSpeed, x)) { //bug need to fix
//                        currSpeed++;
//                    }
//                    else {
//                        currSpeed--;
//                    }
//                } else {
//                    currSpeed--;
//                }
//            }
//            time++;
//            traveled+=currSpeed;
//        }
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

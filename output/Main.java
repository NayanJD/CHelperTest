import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author NayanJ
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        FastWriter out = new FastWriter(outputStream);
        OddEvenSubarrays solver = new OddEvenSubarrays();
        solver.solve(1, in, out);
        out.close();
    }

    static class OddEvenSubarrays {
        public void solve(int testNumber, InputReader in, FastWriter out) {

            int N = in.nextInt();

            int[] arr = new int[N + 1];
            arr[0] = 0;

            long count = 0;

            int[] pos = new int[N];
            pos[0] = 1;

            int[] neg = new int[N];

            try {
                for (int i = 0; i < N; i++) {
                    if (in.nextInt() % 2 == 0) {
                        arr[i + 1] = arr[i] - 1;
                    } else {
                        arr[i + 1] = arr[i] + 1;
                    }

                    if (arr[i + 1] >= 0) {
                        count += pos[arr[i + 1]];
                        pos[arr[i + 1]]++;
                    } else {
                        count += neg[-arr[i + 1]];
                        neg[-arr[i + 1]]++;
                    }
                }
            } catch (Exception e) {
                count = 0;
            }
            out.printLine(count);
            //out.printLine(scount);
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[8192];
        private int curChar;
        private int snumChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class FastWriter {
        private final PrintWriter writer;

        public FastWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public FastWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}


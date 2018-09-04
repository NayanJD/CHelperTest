import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.Writer;
import java.util.LinkedList;
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
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        RemoveFriends solver = new RemoveFriends();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class RemoveFriends {
        public void solve(int testNumber, Scanner in, PrintWriter out) {

            java.util.LinkedList<Integer> li = new java.util.LinkedList<>();

            for (int i = 0; i < 24000000; i++) {
                li.add(i);
            }
            //out.println("Hi There");
            //LOGGER.log(Level.FINE,"hiiii");
            //debug.println("Hi There");
            //int T=in.nextInt();

            int N = 0;
            int K = 0;
            int[] arr = null;

            //for(int i=0;i<T;i++){
            N = in.nextInt();
            K = in.nextInt();

            arr = new int[N];

            for (int j = 0; j < N; j++) {
                arr[j] = in.nextInt();
            }

            solver(arr, N, K, out);
            //}
        }

        public static void solver(int[] arr, int N, int K, PrintWriter out) {

            nj_util_LinkedList list = new nj_util_LinkedList();

            for (int i = 0; i < N; i++) {
                list.add(arr[i]);
                //out.print(arr[i]+" ");
            }

            nj_util_LinkedList.Node first = list.head.next;
            nj_util_LinkedList.Node second = list.head;

//        list.delete(list.head);
//        first=list.head;
//        while(first!=null){
//            out.print(first.value+" ");
//            first=first.next;
//        }
//        out.println();

            int del = 0;

            while (del < K && first != null) {
                if (second.value < first.value) {
                    list.delete(second);
                    del++;
                    if (first.prev != null)
                        second = first.prev;
                    else {
                        second = first;
                        first = first.next;
                    }
                } else {
                    second = first;
                    first = first.next;
                }
            }

            if (del < K) {
                list.delete(list.tail);
            }

            first = list.head;
            while (first != null) {
                out.print(first.value + " ");
                first = first.next;
            }
            out.println();


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

        public String readString() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class nj_util_LinkedList {
        public nj_util_LinkedList.Node head = null;
        public nj_util_LinkedList.Node tail = null;

        public void add(int value) {
            nj_util_LinkedList.Node curr = null;

            if (tail == null) {
                curr = new nj_util_LinkedList.Node(value, null, null);
                head = curr;
                tail = curr;
            } else {
                curr = new nj_util_LinkedList.Node(value, tail, null);
                tail.next = curr;
                tail = curr;
            }
        }

        public void delete(nj_util_LinkedList.Node node) {

            if (node == null)
                return;
            if (head == tail) {
                head = null;
                tail = null;
            }
            if (node == head) {
                head = node.next;
                head.prev = null;
            } else if (node == tail) {
                tail = node.prev;
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }

        static public class Node {
            public int value;
            public nj_util_LinkedList.Node prev;
            public nj_util_LinkedList.Node next;

            public Node(int value, nj_util_LinkedList.Node prev, nj_util_LinkedList.Node next) {
                this.value = value;
                this.prev = prev;
                this.next = next;
            }

        }

    }

    static class OutputWriter {
        private DataOutputStream w;
        private PrintWriter writer;
        final private static int SIZE = 1 << 16;
        final private byte[] buff = new byte[SIZE];
        final private char[] cbuff = new char[SIZE];
        private int curr = 0;
        private boolean iswriter;

        public OutputWriter(OutputStream out) {
            this.w = new DataOutputStream(out);
            iswriter = false;
        }

        public OutputWriter(Writer out) {
            this.writer = (PrintWriter) out;
            iswriter = true;
        }

        public void flush() {
            try {
                if (!iswriter)
                    w.write(buff, 0, curr);
                else
                    writer.write(cbuff, 0, curr);
            } catch (Exception e) {

            }
        }

        public void close() {
            //System.out.println("closed");
            try {
                flush();
                writer.close();
            } catch (Exception e) {

            }
        }

    }
}


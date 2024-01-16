import java.io.*;
import java.util.*;

public class lab4_F {
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        int k = in.nextInt();
        int n = in.nextInt();
        int[] A = new int[n];
        int[] dec = new int[n];
        int[] inc = new int[n];
        int[] decdex = new int[n];
        int[] incdex = new int[n];
        int max = 1;
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        dec[0] = A[0];
        inc[0] = A[0];
        int R = 0;
        int L = 0;
        int front1 = 0;
        int front2 = 0;
        int rear1 = 0;
        int rear2 = 0;
        while (R < n) {
            while (rear1 > front1 && A[R] <= inc[rear1 - 1]) {
                inc[--rear1] = 0;
            }
            inc[rear1] = A[R];
            incdex[rear1] = R;
            rear1++;
            while (rear2 > front2 && A[R] >= dec[rear2 - 1]) {
                dec[--rear2] = 0;
            }
            dec[rear2] = A[R];
            decdex[rear2] = R;
            rear2++;

            if (dec[front2] - inc[front1] <= k) {
                max = Math.max(max, R - L + 1);
                R++;
            } else {
                while (dec[front2] - inc[front1]> k) {
                    L++;
                    if (L > incdex[front1]) {
                        inc[front1] = 0;
                        front1++;
                    }
                    if (L > decdex[front2]) {
                        dec[front2] = 0;
                        front2++;
                    }
                }
            }
        }
        out.println(max);
        out.close();
    }
}

class QReader5 {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter5 implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}

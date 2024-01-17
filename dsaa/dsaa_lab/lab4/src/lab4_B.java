import java.io.*;
import java.util.*;

public class lab4_B {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int[] A = new int[p+1];
        int[] B = new int[q+1];
        int[] time = new int[n+1];
        for(int i=0;i<p;i++){
            A[i] = in.nextInt();
        }
        for (int i=0;i<q;i++){
            B[i] = in.nextInt();
        }
        int count = 1;
        int a=0;
        int b=0;
        while(a!=p || b!=q) {
            while (time[A[a]] != 0  || time[B[b]] != 0) {
                if (time[A[a]]!=0) {
                    if(a!=p) {
                        a++;
                    }
                }
                if (time[B[b]]!=0 ) {
                    if(b!=q) {
                        b++;
                    }
                }
            }
            if(A[a]!=0) {
                time[A[a]] = count;
                if(a!=p) {
                    a++;
                }
            }
            if(B[b]!=0) {
                time[B[b]] = count;
                if(b!=q) {
                    b++;
                }
            }
            count++;
        }
        for(int i=1;i<n+1;i++){
            out.print(time[i]+" ");
        }
        out.close();
    }
}
class QReader1 {
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

class QWriter1 implements Closeable {
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

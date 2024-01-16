import java.io.*;
import java.util.*;
public class lab2_A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for(int t=0;t<T;t++){
            int n = in.nextInt();
            int m = in.nextInt();
            long[] B = new long[n];
            for(int b=0;b<n;b++){
                B[b] = in.nextLong();
            }
            long[] C = new long[m];
            for(int c=0;c<m;c++){
                C[c] = in.nextLong();
            }
            long[] A = new long[n+m];
            int i=0;
            int j=0;
            for(int k=0;k<n+m;k++){
                if(i<n && (j>=m || B[i]<=C[j])){
                    A[k] = B[i];
                    i++;
                }else{
                    A[k] = C[j];
                    j++;
                }
                out.print(A[k]+" ");
            }
            out.println(" ");
        }
        out.close();
    }
}
class QReader {
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

class QWriter implements Closeable {
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









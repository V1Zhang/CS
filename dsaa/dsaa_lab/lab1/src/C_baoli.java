import java.io.*;
import java.util.StringTokenizer;

public class C_baoli {
    public static void main(String[] args) {
        QReader2_ in = new QReader2_();
        QWriter2_ out = new QWriter2_();
        int N = in.nextInt();
        int Q = in.nextInt();
        long[] A = new long[N+1];
        for(int i=1;i<N+1;i++) {
            A[i] = in.nextLong();
        }
        for(int i=0;i<Q;i++){
            long x = in.nextLong();
            long y = in.nextLong();
            int num = 0;
            for(int j=1;j<N+1;j++) {
                if(A[j]>x && A[j]<y){
                    num++;
                }
            }
            if(num>0){
                out.println("YES "+num);
            }
            else{
                out.println("NO");
            }
        }
        out.close();
    }
}
class QReader2_ {
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

class QWriter2_ implements Closeable {
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










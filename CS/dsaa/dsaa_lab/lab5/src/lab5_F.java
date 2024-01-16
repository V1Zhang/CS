import java.io.*;
import java.util.*;
public class lab5_F {
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        int[] encrypt = new int[26];
        for(int i=0;i<26;i++){
            encrypt[i] = in.next().charAt(0);
        }
        String S1 = in.next();
        int len = 2*S1.length()+1;
        int[] p = new int[len];
        p[S1.length()] = '*';
        for(int i=0;i<S1.length();i++){
            p[i] = S1.charAt(i);
            p[i+S1.length()+1] = encrypt[S1.charAt(i)-'a'];
        }
        int[] next = new int[len];
        next[0] = 0;
        int i = 1;
        int j = 0;
        while (i < len) {
            if (p[i] == p[j]) {
                next[i] = j + 1;
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = next[j - 1];
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }
        int max = next[len-1];
        int ans = S1.length()-max;
        out.println(ans);
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









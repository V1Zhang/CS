import java.io.*;
import java.util.*;
public class lab5_D {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int n = in.nextInt();
        for(int t=0;t<n;t++) {
            String S = in.next();
            int len = S.length();
            int[] p = new int[len];
            for (int k = 0; k < len; k++) {
                p[k] = S.charAt(k);
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
            int ans = 0;
            int node = len-next[len-1];
            if(node!=len && len % node==0){
                ans = 0;
            }else {
                while(node==len+ans || (len+ans) % node!=0){
                    ans++;
                }
            }
            out.println(ans);
        }
        out.close();
    }
}
class QReader3 {
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

class QWriter3 implements Closeable {
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









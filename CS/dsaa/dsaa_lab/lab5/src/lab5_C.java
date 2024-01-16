import java.io.*;
import java.util.*;
public class lab5_C {
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        String S = in.next();
        int n = S.length();
        int[] p = new int[n];
        for (int k = 0; k < n; k++) {
            p[k] = S.charAt(k);
        }
        int[] next = new int[n];
        next[0] = 0;
        int i = 1;
        int j = 0;
        while (i<n) {
            if (p[i] == p[j]) {
                next[i] = j+1;
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
        int[][] tran = new int[n][26];
        for(int y=0;y<26;y++){
            if(p[0] == 'a'+y) {
                tran[0][y] = 1;
            }else {
                tran[0][y] = 0;
            }
        }
        for(int x=1;x<n;x++){
            for(int y=0;y<26;y++){
                if(p[x]=='a'+y){
                    tran[x][y] = x+1;
                }else{
                    tran[x][y] = tran[next[x-1]][y];
                }
            }
        }

        for(int x=0;x<n;x++) {
            for (int y = 0; y < 26; y++) {
                out.print(tran[x][y]+" ");
            }
            out.println("");
        }
        out.close();
    }
}
class QReader2 {
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

class QWriter2 implements Closeable {
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









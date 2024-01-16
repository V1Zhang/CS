import java.io.*;
import java.util.*;

public class lab4_A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for(int i=0;i<T;i++){
            boolean match = true;
            int n = in.nextInt();
            String s = in.next();
            char[] stack = new char[30000];
            int index = 0;
            for(int j=0;j<n;j++){
                char c = s.charAt(j);
                if(c=='(' || c=='[' || c=='{'){
                    stack[index] = c;
                    index++;
                } else {
                    if(index == 0) {
                        match = false;
                        break;
                    }else{
                        index--;
                        char top = stack[index];

                        if (c == ']' && top != '[') {
                            match = false;
                            break;
                        }
                        if (c == '}' && top != '{') {
                            match = false;
                            break;
                        }
                        if (c == ')' && top!='(') {
                            match = false;
                            break;
                        }
                    }
                }
            }
            if(index!=0){
                match = false;
            }
            if(match){
                out.println("YES");
            }else{
                out.println("NO");
            }

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

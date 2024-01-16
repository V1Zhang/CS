import java.io.*;
import java.util.*;

public class lab4_D {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int T = in.nextInt();
        for(int i=0;i<T;i++){
            int n = in.nextInt();
            int[] stack = new int[n];
            int[] already = new int[n+1];
            int[] ans = new int[n];
            int count = 1;
            int top = 0;
            int[] temp = new int[n];
            int temp_point = 0;
            int ans_point = 0;
            for(int j = 0;j<n;j++) {
                temp[j] = in.nextInt();
            }
            while(temp_point<n) {
                while (already[count] == 1) {
                    count++;
                }
                if(top!=0 && stack[top-1]<count){
                    count = stack[top-1];
                    ans[ans_point] = stack[top-1];
                    ans_point++;
                    top--;
                }
                if (already[count] != 1) {
                    while (temp[temp_point] != count) {
                        stack[top] = temp[temp_point];
                        top++;
                        already[temp[temp_point]] = 1;
                        temp_point++;
                    }
                    ans[ans_point] = temp[temp_point];
                    count++;
                    ans_point++;
                    temp_point++;
                }
            }
            for(int j=top-1;j>=0;j--){
                ans[ans_point] = stack[j];
                ans_point++;
            }
            for(int j=0;j<n;j++){
                out.print(ans[j]+" ");
            }
            out.println("");
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

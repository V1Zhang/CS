import java.io.*;
import java.util.*;
public class lab8_F {
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        int T = in.nextInt();
        for(int t=0;t<T;t++){
            int n = in.nextInt();
            int m = in.nextInt();
            nodeF[] nodes = new nodeF[n+1];
            for (int i = 1; i < n + 1; i++) {
                nodeF a = new nodeF();
                nodes[i] = a;
                a.val = i;
            }
            int[] a = new int[n+1];
            int[] b = new int[n+1];
            for(int i=1;i<n+1;i++){
               a[i] = in.nextInt();
               b[i] = in.nextInt();
            }
            for(int i=0;i<m;i++){
                int u = in.nextInt();
                int v = in.nextInt();
                nodes[u].children.add(nodes[v]);
                nodes[v].in++;
            }
            nodeF[] queue = new nodeF[n];
            int front = 0;
            int rear = 0;
            for(int i=1;i<n+1;i++){
                if(nodes[i].in==0){
                    queue[rear] = nodes[i];
                    rear++;
                }
            }
            while (front != rear) {
                for (int k = 0; k < queue[front].children.size(); k++) {
                    queue[front].children.get(k).in--;
                    queue[front].children.get(k).F+= (long) ((queue[front].F + a[queue[front].val])%(1e9+7));
                    if(queue[front].children.get(k).in==0){
                        queue[rear] = queue[front].children.get(k);
                        rear++;
                    }
                }
                front++;
            }
            long ans = 0;
            for(int i=1;i<n+1;i++){
                ans+= (long) ((nodes[i].F * b[i]) % (1e9+7));
                ans = (long) (ans%(1e9+7));
            }
            out.println(ans);
        }
        out.close();
    }





}
class nodeF{
    int val;
    ArrayList<nodeF> children = new ArrayList<>();
    int in = 0;
    long F = 0;
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








